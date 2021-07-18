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
    private Long id;

    @Column(nullable = false)
    private int tipo;

    @Column(nullable = false)
    private String data;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coagri_id")
    private Coagri coagri;

    @ManyToMany
    @JoinTable(
        name = "cesta_item",
        joinColumns = @JoinColumn(name = "cesta_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> itens;
    
}
