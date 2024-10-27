package br.com.traumfabrik.compraoq.services;

import java.util.List;

import br.com.traumfabrik.compraoq.component.Retorno;
import br.com.traumfabrik.compraoq.infra.exception.ExceptionOfNotFound;
import br.com.traumfabrik.compraoq.validation.ValidationRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.traumfabrik.compraoq.dto.ItemDto;
import br.com.traumfabrik.compraoq.entities.Item;
import br.com.traumfabrik.compraoq.repositories.ItemRepository;
import jakarta.validation.Valid;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;

	private final List<ValidationRule<Item>> validador;

	@Autowired
	public ItemService(List<ValidationRule<Item>> validador) {
		this.validador = validador;
	}

	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	public List<Item> findByList(Integer local) {
		return itemRepository.findByLocal(local);
	}

	public Retorno<Item> save(@Valid ItemDto itemDto) {
		Retorno<Item> retorno = new Retorno<Item>();
		Item item 			  = itemRepository.save(itemDto.toItem());
		retorno.getDados().add(item);
		retorno.setMensagem("Item salvo com sucesso");
		return retorno;
	}

	public void delete(Long id) {
		itemRepository.findById(id).orElseThrow(()-> new ExceptionOfNotFound("Item não encontrado"));
		itemRepository.deleteById(id);
	}
	
	public Item findById(Long id) {
		Item item = itemRepository.findById(id).orElseThrow(()-> new ExceptionOfNotFound("Item não foi encontrado"));
		return item;
	}

	public Item update(Long id, @Valid ItemDto itemDto) {
		Item item = this.getItem(id);
		item.setDescricao(itemDto.descricao());
		item.setLocal(itemDto.local());
		item.setPendente(item.getPendente()!=null?item.getPendente():0);
		return item;
	}

	public Item updateItem(Long id, Integer pendente){
		Item item = this.getItem(id);
		item.setPendente(pendente);
		validador.forEach(v->v.validate(item));
		return itemRepository.save(item);
	}

	public Item getItem(Long id) {
		return itemRepository.findById(id)
				.orElseThrow(()->new ExceptionOfNotFound("Item não encontrado"));
	}
}
