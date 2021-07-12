package br.com.csaatibaia.MontaCesta.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.csaatibaia.MontaCesta.Model.Cesta;

public interface CestaRepository extends JpaRepository<Cesta, Long>{
    
}
