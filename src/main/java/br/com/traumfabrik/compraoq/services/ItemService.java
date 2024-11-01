package br.com.traumfabrik.compraoq.services;

import java.util.List;

import br.com.traumfabrik.compraoq.entities.ItemEntity;
import br.com.traumfabrik.compraoq.settings.exceptions.NotFoundException;
import br.com.traumfabrik.compraoq.validation.ValidateInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.traumfabrik.compraoq.dto.ItemDto;
import br.com.traumfabrik.compraoq.repositories.ItemRepository;
import jakarta.validation.Valid;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private List<ValidateInterface<ItemEntity>> validador;

	public List<ItemEntity> findAll() {
		return itemRepository.findAll();
	}

	public List<ItemEntity> findByList(Integer local) {
		return itemRepository.findByLocalOrderByDescription(local);
	}

	public ItemEntity findById(Long id) {
        return itemRepository.findById(id).orElseThrow(()-> new NotFoundException("Item não foi encontrado"));
	}

	public ItemEntity save(@Valid ItemDto itemDto) {
		ItemEntity item = itemRepository.save(itemDto.toItem());
		return item;
	}

	public void delete(Long id) {
		itemRepository.findById(id).orElseThrow(()-> new NotFoundException("Item não encontrado"));
		itemRepository.deleteById(id);
	}
	


	public ItemEntity update(Long id, @Valid ItemDto itemDto) {
		ItemEntity item = this.getItem(id);
		item.setDescription(itemDto.descricao());
		item.setList(itemDto.lista());
		item.setPendente(item.getPendente()!=null?item.getPendente():0);
		return item;
	}

	public ItemEntity updateItem(Long id, Integer pendente){
		ItemEntity item = this.getItem(id);
		item.setPendente(pendente);
		validador.forEach(v->v.validate(item));
		return itemRepository.save(item);
	}

	public ItemEntity getItem(Long id) {
		return itemRepository.findById(id)
				.orElseThrow(()->new NotFoundException("Item não encontrado"));
	}
}
