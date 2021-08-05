package br.com.csaatibaia.MontaCesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.csaatibaia.MontaCesta.model.Usuario;

public interface CoagriRepository extends JpaRepository<Usuario, Long>{

    Usuario findCoagriByEmail(String email);
    
    boolean existsCoagriByEmail(String email);
}
