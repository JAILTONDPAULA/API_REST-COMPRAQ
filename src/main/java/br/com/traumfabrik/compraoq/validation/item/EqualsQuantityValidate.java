package br.com.traumfabrik.compraoq.validation.item;

import br.com.traumfabrik.compraoq.entities.ItemEntity;
import br.com.traumfabrik.compraoq.repositories.ItemRepository;
import br.com.traumfabrik.compraoq.settings.exceptions.NotFoundException;
import br.com.traumfabrik.compraoq.validation.ValidateInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EqualsQuantityValidate implements ValidateInterface<ItemEntity> {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void validate(ItemEntity itemAtualizado) {
        ItemEntity item = itemRepository.findById(itemAtualizado.getId())
                                  .orElseThrow(()->new NotFoundException("Item não encontrado"));
        if(item.getPendente().equals(itemAtualizado.getPendente())) {
            throw new IllegalArgumentException("A quantidade informada deve ser diferente da atual");
        }
    }
}
