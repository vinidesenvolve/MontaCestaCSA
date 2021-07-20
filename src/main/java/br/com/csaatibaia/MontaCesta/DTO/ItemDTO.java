package br.com.csaatibaia.MontaCesta.DTO;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.csaatibaia.MontaCesta.Model.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ItemDTO {
    
    private Long id;

    @NotBlank(message = "Informe o nome do item")
    private String nome;

    @NotBlank(message = "Informe a descrição do item")
    private String descricao;

    @NotNull(message = "Informe a quantidade do item")
    @Positive(message = "Quantidade deve ser maior que zero")
    private int quantidade;
    
    @NotBlank(message = "Informe a origem do item")
    private String origem;

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.nome = item.getNome();
        this.descricao = item.getDescricao();
        this.quantidade =item.getQuantidade();
        this.origem = item.getOrigem();
    }

    public ItemDTO(Optional<Item> item) {
    }
}