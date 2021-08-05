package br.com.csaatibaia.MontaCesta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.csaatibaia.MontaCesta.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

    Optional<Item> findByNome(String nome);
    
}
