package br.com.traumfabrik.compraoq.services;

import br.com.traumfabrik.compraoq.entities.ItemEntity;
import br.com.traumfabrik.compraoq.repositories.ItemRepository;
import br.com.traumfabrik.compraoq.validation.item.EqualsQuantityValidate;
import br.com.traumfabrik.compraoq.validation.item.IllegalQuantityValidate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes Item [Regras de negócio]")
class ValidateItemTest {

    @InjectMocks
    private EqualsQuantityValidate mesmaQuantidadeValidate;

    @InjectMocks
    private IllegalQuantityValidate quantidadesNaoPermitidaValidate;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemEntity item;

    @Test
    @DisplayName("Não permite atualizar quantidade com valor atual")
    public void ataulizarQuantidadeDeItemComMesmoValor() {

        BDDMockito.given(item.getPendente()).willReturn(1);
        BDDMockito.given(item.getId()).willReturn(1L); // Supondo que o ID do item seja 1
        BDDMockito.given(itemRepository.findById(item.getId())).willReturn(Optional.of(item));

        ItemEntity item2 = new ItemEntity(1L,"Teste",2,1);
        Assertions.assertThrows(IllegalArgumentException.class,()->mesmaQuantidadeValidate.validate(item2));
    }

    @Test
    @DisplayName("Não permite atualizar item com quantidade não liberadas")
    public void naoPermiteAtualizarQuantidadesNaoLiberadas() {
        BDDMockito.given(item.getPendente()).willReturn(2);
        Assertions.assertThrows(IllegalArgumentException.class,()->quantidadesNaoPermitidaValidate.validate(item));
    }

}