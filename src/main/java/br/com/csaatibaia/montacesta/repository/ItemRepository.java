package br.com.csaatibaia.montacesta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.csaatibaia.montacesta.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

    Optional<Item> findByNome(String nome);
    
}
