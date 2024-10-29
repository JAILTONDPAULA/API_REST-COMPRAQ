package br.com.traumfabrik.compraoq.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.traumfabrik.compraoq.entities.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>{

    List<Item> findByLocal(Integer local);

}
