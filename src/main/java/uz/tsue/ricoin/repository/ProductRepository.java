package uz.tsue.ricoin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.tsue.ricoin.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
