package uz.tsue.ricoin.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.tsue.ricoin.entity.User;
import uz.tsue.ricoin.dto.response.UserResponseDto;
import uz.tsue.ricoin.service.interfaces.UserService;
import uz.tsue.ricoin.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final NotificationService notificationService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> authenticatedUser(@AuthenticationPrincipal User user) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(userService.getCurrentUser(user));
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> allUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/add-balance")
    public ResponseEntity<?> addBalance(@PathVariable Long id, @RequestParam int balance, HttpServletRequest request) {
        userService.addBalance(userService.findById(id), balance);
        return ResponseEntity.ok(notificationService.generateNotificationMessage("application.notification.Successfully", request));
    }

}
