package br.com.traumfabrik.compraoq.infra;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Retorno<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String  titulo;
    private Integer status;
    private List<T> dados = new ArrayList<>();
}
