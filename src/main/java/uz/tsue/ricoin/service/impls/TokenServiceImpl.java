package uz.tsue.ricoin.service.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tsue.ricoin.entity.Token;
import uz.tsue.ricoin.repository.TokenRepository;
import uz.tsue.ricoin.service.interfaces.TokenService;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;


    @Override
    public void save(Token token) {
        tokenRepository.save(token);
    }
}
