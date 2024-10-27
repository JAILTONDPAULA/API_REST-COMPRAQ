package br.com.traumfabrik.compraoq.validation.item;

import br.com.traumfabrik.compraoq.entities.Item;
import br.com.traumfabrik.compraoq.infra.exception.ExceptionOfValidate;
import br.com.traumfabrik.compraoq.validation.ValidationRule;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class QuantidadesNaoPermitidaValidate implements ValidationRule<Item> {
    @Override
    public void validate(Item item) {
        if(!Arrays.asList(0,1).contains(item.getPendente())){
            throw new ExceptionOfValidate("Quantidade de item permitido é 0 ou 1");
        }
    }
}
