package br.com.traumfabrik.compraoq.validation.item;

import br.com.traumfabrik.compraoq.entities.Item;
import br.com.traumfabrik.compraoq.infra.exception.ExceptionOfNotFound;
import br.com.traumfabrik.compraoq.infra.exception.ExceptionOfValidate;
import br.com.traumfabrik.compraoq.repositories.ItemRepository;
import br.com.traumfabrik.compraoq.validation.ValidationRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MesmaQuantidadeValidate implements ValidationRule<Item> {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void validate(Item itemAtualizado) {
        Item item = itemRepository.findById(itemAtualizado.getId())
                                  .orElseThrow(()->new ExceptionOfNotFound("Item não encontrado"));
        if(item.getPendente().equals(itemAtualizado.getPendente())) {
            throw new ExceptionOfValidate("A quantidade informada deve ser diferente da atual");
        }
    }
}
