package br.com.csaatibaia.MontaCesta.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(nullable = false)
    String nome;

    @Column(nullable = false)
    int quantidade;

    @Column(nullable = false)
    boolean disponibilidade;

    @Column
    String produtor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cesta_id")
    Cesta cesta;    
}
