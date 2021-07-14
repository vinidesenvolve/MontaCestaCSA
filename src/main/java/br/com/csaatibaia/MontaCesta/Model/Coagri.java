package br.com.csaatibaia.MontaCesta.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Coagri {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Email(message = "Formato inválido")
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String senha;

    @NotEmpty
    private String nome;

    @NotNull
    private Integer tipoCesta;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cestaId", referencedColumnName = "id")
    private Cesta cesta;

}
