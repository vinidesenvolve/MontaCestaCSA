package br.com.csaatibaia.montacesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.csaatibaia.montacesta.model.Cesta;

public interface CestaRepository extends JpaRepository<Cesta, Long>{
    
}
