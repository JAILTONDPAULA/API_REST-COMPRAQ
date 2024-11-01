package br.com.traumfabrik.compraoq.dto;

import br.com.traumfabrik.compraoq.entities.ItemEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemDto(@NotBlank(message = "Informe o nome do item da lista")    String  descricao ,
					  @NotNull(message = "Informe a lista que será adicionado") Integer lista     ,
					  															Integer quantidade) {
	
	public ItemEntity toItem() {
		return new ItemEntity(this);
	}
			
}
