package br.com.csaatibaia.MontaCesta.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.csaatibaia.MontaCesta.Model.Coagri;

public interface CoagriRepository extends JpaRepository<Coagri, Long>{

    Coagri findCoagriByEmail(String email);
    
    boolean existsCoagriByEmail(String email);
}
