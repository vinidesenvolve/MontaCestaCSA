package br.com.csaatibaia.MontaCesta.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Coagri {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false, unique = true)
    String email;
    
    @Column(nullable = false, unique = true)
    String senha;

    @Column(nullable = false)
    String nome;

    @OneToOne(mappedBy= "coagri", cascade = CascadeType.ALL)
    Cesta cesta;

}
