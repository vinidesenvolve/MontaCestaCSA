package br.com.csaatibaia.MontaCesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.csaatibaia.MontaCesta.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

    Item findItemByNome(String nome);

    boolean existsItemByNome(String nome);
    
}
