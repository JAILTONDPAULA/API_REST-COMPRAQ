package br.com.traumfabrik.compraoq.component;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Retorno<T> implements Serializable {
    private static final long serialVersionUID = 1L; // Recomenda-se sempre adicionar um UID
    private String  mensagem;
    private List<T> dados = new ArrayList<>();
}
