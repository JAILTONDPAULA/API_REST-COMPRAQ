package br.com.traumfabrik.compraoq.services;

import br.com.traumfabrik.compraoq.dto.UserAccountDto;
import br.com.traumfabrik.compraoq.entities.UserEntity;
import br.com.traumfabrik.compraoq.repositories.UserRepository;
import br.com.traumfabrik.compraoq.settings.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UseAccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public String login(UserAccountDto userAccountDto) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(userAccountDto.email(), userAccountDto.password()));
        UserEntity user    = userRepository.findByEmail(userAccountDto.email());
        return tokenService.generateToken(user);
    }

}
