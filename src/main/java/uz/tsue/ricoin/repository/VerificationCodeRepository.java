package uz.tsue.ricoin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tsue.ricoin.entity.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
}
