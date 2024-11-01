package br.com.traumfabrik.compraoq.validation.item;

import br.com.traumfabrik.compraoq.entities.ItemEntity;
import br.com.traumfabrik.compraoq.validation.ValidateInterface;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class IllegalQuantityValidate implements ValidateInterface<ItemEntity> {
    @Override
    public void validate(ItemEntity item) {
        if(!Arrays.asList(0,1).contains(item.getPendente())){
            throw new IllegalArgumentException("Quantidade de item permitido é 0 ou 1");
        }
    }
}
