package br.com.traumfabrik.compraoq.entities;

import java.io.Serializable;

import br.com.traumfabrik.compraoq.dto.ItemDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="item",schema="compraoq")
public class Item implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long    id;
	private String  descricao;
	private Integer local;
	private Integer pendente;
	
	public Item(ItemDto itemDto) {
		this.descricao = itemDto.descricao();
		this.local	   = itemDto.local();
		this.pendente  = itemDto.pendente()!=null?itemDto.pendente():0;
	}

}
