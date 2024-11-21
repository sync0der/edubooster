package uz.tsue.ricoin.service.impls;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.RequestContextUtils;
import uz.tsue.ricoin.dto.response.OrderDto;
import uz.tsue.ricoin.entity.Order;
import uz.tsue.ricoin.entity.Product;
import uz.tsue.ricoin.entity.User;
import uz.tsue.ricoin.entity.enums.OrderStatus;
import uz.tsue.ricoin.exceptions.InsufficientBalanceException;
import uz.tsue.ricoin.exceptions.InsufficientStockException;
import uz.tsue.ricoin.exceptions.InvalidRequestException;
import uz.tsue.ricoin.mapper.OrderMapper;
import uz.tsue.ricoin.repository.OrderRepository;
import uz.tsue.ricoin.service.interfaces.OrderService;
import uz.tsue.ricoin.service.interfaces.ProductService;
import uz.tsue.ricoin.service.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;
    private final MessageSource messageSource;
    private final OrderMapper orderMapper;

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public OrderDto get(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderDto> getAll(Long userId) {
        List<Order> list = userService.findById(userId).getOrders();
        List<OrderDto> orders = new ArrayList<>();
        for (Order order : list) {
            orders.add(orderMapper.toDto(order));
        }
        return orders;
    }

    @Override
    public Order find(Long id) {
        return orderRepository.findById(id)
                .orElseThrow();

        //todo implement exception handling
    }

    @Override
    public OrderDto makeOrder(User user, Long id, int quantity) {
        Product product = productService.findById(id);

        if (productService.isStockAvailable(product, quantity)) {

            Order order = Order.builder()
                    .product(product)
                    .quantity(quantity)
                    .price(product.getPrice() * quantity)
                    .user(user)
                    .status(OrderStatus.PENDING)
                    .build();

            if (userService.hasEnoughBalance(order.getPrice(), user)) {
                userService.debitBalance(user, order.getPrice());
                save(order);

                user.getOrders().add(order);
                userService.save(user);
                return orderMapper.toDto(order);
            } else {
                throw new InsufficientBalanceException();
            }
        } else {
            throw new InsufficientStockException();
        }

    }

    @Override
    public void cancelOrder(Long orderId, HttpServletRequest request) {
        Order order = find(orderId);
        if (!order.getStatus().equals(OrderStatus.CANCELED)) {
            order.setStatus(OrderStatus.CANCELED);
            orderRepository.save(order);

            User user = order.getUser();
            userService.addBalance(user, calculateRefunding(order, 20));
            userService.save(user);
        } else
            throw new InvalidRequestException(
                    messageSource.getMessage("application.exception.notification.NotFound", null, RequestContextUtils.getLocale(request)));

    }

    @Override
    public void approveOrder(Long orderId, HttpServletRequest request) {
        Order order = find(orderId);
        order.setStatus(OrderStatus.APPROVED);
        User user = order.getUser();
        user.setOrders(List.of(order));
        save(order);
        userService.save(user);
    }

    private int calculateRefunding(Order order, int commissionPercentage) {
        int commissionFee = Math.round(order.getPrice() * (commissionPercentage / 100F));
        return order.getPrice() - commissionFee;
    }
}
