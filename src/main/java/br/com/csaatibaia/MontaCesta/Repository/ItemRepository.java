package br.com.csaatibaia.MontaCesta.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.csaatibaia.MontaCesta.Model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

    Item findItemByNome(String nome);

    boolean existsItemByNome(String nome);
    
}
