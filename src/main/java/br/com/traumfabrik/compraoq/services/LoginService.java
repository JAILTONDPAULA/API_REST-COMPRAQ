package br.com.traumfabrik.compraoq.services;

import br.com.traumfabrik.compraoq.dto.LoginDto;
import br.com.traumfabrik.compraoq.entities.Usuario;
import br.com.traumfabrik.compraoq.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    public String login(LoginDto loginDto) {
        var token = new UsernamePasswordAuthenticationToken(loginDto.email(),loginDto.password());
        var authentication = manager.authenticate(token);
        Usuario user = usuarioRepository.findByEmail(loginDto.email());
        return tokenService.generateToken(user);
    }

}
