package br.com.csaatibaia.MontaCesta.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Coagri {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    private String senha;

    private String nome;

    private Integer tipoCesta;

    @OneToMany(mappedBy = "coagri", cascade = CascadeType.ALL)
    private List<Cesta> cestas;

}
