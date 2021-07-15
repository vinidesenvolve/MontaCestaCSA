package br.com.csaatibaia.MontaCesta.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    int tipo;

    @Column(nullable = false)
    String data;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cesta_id")
    Coagri coagri;

    @ManyToMany
    @JoinTable(
        name = "cesta_item",
        joinColumns = @JoinColumn(name = "cesta_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    List<Item> itens;
    
}
