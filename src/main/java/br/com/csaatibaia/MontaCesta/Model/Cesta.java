package br.com.csaatibaia.MontaCesta.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Cesta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(nullable = false)
    int quantidadeItens;

    @Column(nullable = false)
    boolean isMontada;

    @OneToOne
    @JoinColumn(name = "coagri_id")
    Coagri coagri;

    @OneToMany(mappedBy = "cesta", cascade = CascadeType.ALL)
    Item item;
    
}
