package br.com.csaatibaia.MontaCesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.csaatibaia.MontaCesta.model.Cesta;

public interface CestaRepository extends JpaRepository<Cesta, Long>{
    
}
