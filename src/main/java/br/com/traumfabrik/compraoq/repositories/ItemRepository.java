package br.com.traumfabrik.compraoq.repositories;

import br.com.traumfabrik.compraoq.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long>{

    List<ItemEntity> findByLocalOrderByDescription(Integer local);

}
