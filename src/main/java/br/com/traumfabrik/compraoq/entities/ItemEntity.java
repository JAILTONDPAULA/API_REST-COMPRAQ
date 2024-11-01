package br.com.traumfabrik.compraoq.entities;

import java.io.Serializable;

import br.com.traumfabrik.compraoq.dto.ItemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema="compraoq", name="item")
public class ItemEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long    id;
	@Column(name = "descricao")
	private String description;
	@Column(name = "lista")
	private Integer list;
	@Column(name = "quantidade")
	private Integer pendente;
	
	public ItemEntity(ItemDto itemDto) {
		this.description = itemDto.descricao();
		this.list 		 = itemDto.lista();
		this.pendente  	 = itemDto.quantidade()!=null?itemDto.quantidade():0;
	}

}
