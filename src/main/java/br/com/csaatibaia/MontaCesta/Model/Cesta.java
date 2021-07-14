package br.com.csaatibaia.MontaCesta.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cesta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    int quantidadeItens;

    @Column(nullable = false)
    String data;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cesta_id")
    Coagri coagri;

    @OneToMany(mappedBy = "cesta", cascade = CascadeType.ALL)
    List<Item> itens;
    
}
