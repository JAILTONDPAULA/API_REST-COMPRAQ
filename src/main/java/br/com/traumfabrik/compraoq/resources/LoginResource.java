package br.com.traumfabrik.compraoq.resources;

import br.com.traumfabrik.compraoq.dto.LoginDto;
import br.com.traumfabrik.compraoq.dto.TokenDto;
import br.com.traumfabrik.compraoq.services.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "LOGIN")
public class LoginResource {

    @Autowired
    private LoginService loginService;

    @PostMapping(
            value = "/v1",
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(description = "autenticar usuário com login e senha")
    @ApiResponse(
            responseCode = "200",
            description = "Retorna um token de acesso"
    )
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto login) {
        return ResponseEntity.ok(new TokenDto(loginService.login(login)));
    }
}
