package br.com.traumfabrik.compraoq.infra.exception;

import br.com.traumfabrik.compraoq.infra.Retorno;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
@Controller
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Retorno<String> retorno = new Retorno<>();
        retorno.setTitulo("Erro de validação");
        retorno.setStatus(400);
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            retorno.getDados().add(fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(retorno);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest wr) {
        HttpStatusCode httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        if(ex instanceof MethodArgumentNotValidException) {
            return handleMethodArgumentNotValid((MethodArgumentNotValidException) ex, null, HttpStatus.BAD_REQUEST, null);
        }else if(ex instanceof ExceptionOfNotFound) {
            httpStatusCode = HttpStatus.NOT_FOUND;
        }else if(ex instanceof ExceptionOfValidate) {
            httpStatusCode =HttpStatus.BAD_REQUEST;
        }else if(ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
            httpStatusCode = HttpStatus.UNAUTHORIZED;
        }
        return ResponseEntity.status(httpStatusCode).body(new ExceptionResponse(new Date(),ex.getMessage(),wr.getDescription(false)));
    }

}
