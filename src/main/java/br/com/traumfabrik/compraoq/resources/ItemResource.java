package br.com.traumfabrik.compraoq.resources;

import br.com.traumfabrik.compraoq.component.Retorno;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.traumfabrik.compraoq.dto.ItemDto;
import br.com.traumfabrik.compraoq.entities.Item;
import br.com.traumfabrik.compraoq.services.ItemService;
import jakarta.validation.Valid;

import java.util.List;

@Tag(name="Item")
@RestController
@RequestMapping("/itens")
@SecurityRequirement(name = "BearerAuth")
public class ItemResource {
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping(
			value = "/v1",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
	)
	@Operation(description = "Buscar todos itens cadastrado, independente da lista")
	@ApiResponse(
			description  = "Retorno com sucesso",
			responseCode = "200",
			content = @Content(
					schema = @Schema(implementation = Item.class)
			)
	)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<Item>> findAll() {
		return ResponseEntity.ok(itemService.findAll());
	}

	@GetMapping(
			value="/v1/lista/{lista}",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
	)
	@Operation(description = "Buscar todos os itens de uma lista especifica")
	public ResponseEntity<List<Item>> findByList(@PathVariable Integer lista) {
		return ResponseEntity.ok(itemService.findByList(lista));
	}

	@GetMapping(
			value="/v1/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
	)
	@Operation(description = "Obter detalhes do item por seu id")
	public ResponseEntity<Item> findById(@PathVariable Long id) {
		return ResponseEntity.ok(itemService.findById(id));
	}

	@PostMapping(
			value    = "/v1",
			consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
	)
	@Operation(description = "Salvar novo item na lista")
	@ApiResponse(
			description  = "Retorna o item salvo",
			responseCode = "201",
			content = @Content(
					schema = @Schema(implementation = Item.class)
			)
	)
	public ResponseEntity<Retorno<Item>> save(@RequestBody @Valid ItemDto itemDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(itemDto));
	}
	
	@DeleteMapping(
			value    ="/v1/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
	)
	@Operation(description = "Apagar item de uma lista")
	@ApiResponse(
			description = "Item excluído",
			responseCode = "204"
	)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		itemService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();	
	}
	
	@PutMapping(
			value    = "/v1/{id}",
			consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
	)
	@Operation(description = "Atualizar informações do item")
	public ResponseEntity<Item> update(@PathVariable Long id,@RequestBody @Valid ItemDto itemDto) {
		return ResponseEntity.status(HttpStatus.OK).body(itemService.update(id,itemDto));
	}

	@PutMapping(
			value = "/v1/atualizar/{id}/{pendente}",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
	)
	@Operation(description = "Atualiza a quantidade do item, se está pendente compra ou não")
	public ResponseEntity<Item> updateItem(@PathVariable Long id,@PathVariable Integer pendente) {
		return ResponseEntity.ok(itemService.updateItem(id,pendente));
	}

}
