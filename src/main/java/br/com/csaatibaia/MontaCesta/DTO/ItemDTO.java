package br.com.csaatibaia.MontaCesta.DTO;

import br.com.csaatibaia.MontaCesta.Model.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ItemDTO {
    
    private Long id;

    private String nome;

    private String descricao;

    private int quantidade;

    private String origem;

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.nome = item.getNome();
        this.descricao = item.getDescricao();
        this.quantidade =item.getQuantidade();
        this.origem = item.getOrigem();
    }
}