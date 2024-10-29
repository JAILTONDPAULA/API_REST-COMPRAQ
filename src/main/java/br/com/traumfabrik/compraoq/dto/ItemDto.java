package br.com.traumfabrik.compraoq.dto;

import br.com.traumfabrik.compraoq.entities.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemDto(@NotBlank(message = "Informe o nome do item da lista")    String  descricao,
					  @NotNull(message = "Informe a lista que será adicionado") Integer local    ,
					  															Integer pendente ) {
	
	public Item toItem() {
		return new Item(this);
	}
			
}
