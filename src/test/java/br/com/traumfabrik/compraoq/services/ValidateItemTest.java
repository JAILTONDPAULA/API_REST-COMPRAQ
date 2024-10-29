package br.com.traumfabrik.compraoq.services;

import br.com.traumfabrik.compraoq.entities.Item;
import br.com.traumfabrik.compraoq.infra.exception.ExceptionOfValidate;
import br.com.traumfabrik.compraoq.repositories.ItemRepository;
import br.com.traumfabrik.compraoq.validation.item.MesmaQuantidadeValidate;
import br.com.traumfabrik.compraoq.validation.item.QuantidadesNaoPermitidaValidate;
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
    private MesmaQuantidadeValidate mesmaQuantidadeValidate;

    @InjectMocks
    private QuantidadesNaoPermitidaValidate quantidadesNaoPermitidaValidate;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private Item item;

    @Test
    @DisplayName("Não permite atualizar quantidade com valor atual")
    public void ataulizarQuantidadeDeItemComMesmoValor() {

        BDDMockito.given(item.getPendente()).willReturn(1);
        BDDMockito.given(item.getId()).willReturn(1L); // Supondo que o ID do item seja 1
        BDDMockito.given(itemRepository.findById(item.getId())).willReturn(Optional.of(item));

        Item item2 = new Item(1L,"Teste",2,1);
        Assertions.assertThrows(ExceptionOfValidate.class,()->mesmaQuantidadeValidate.validate(item2));
    }

    @Test
    @DisplayName("Não permite atualizar item com quantidade não liberadas")
    public void naoPermiteAtualizarQuantidadesNaoLiberadas() {
        BDDMockito.given(item.getPendente()).willReturn(2);
        Assertions.assertThrows(ExceptionOfValidate.class,()->quantidadesNaoPermitidaValidate.validate(item));
    }

}