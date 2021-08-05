package br.com.csaatibaia.montacesta.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import br.com.csaatibaia.montacesta.model.Cesta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CestaDTO {
    
    Long id;
    
    @NotBlank
    String data;

    @NotBlank
    UsuarioDTO usuario;

    @NotBlank
    List<ItemDTO> itens;

    public CestaDTO(Cesta cesta) {
        this.id = cesta.getId();
        this.data = cesta.getData();
        this.usuario = new UsuarioDTO(cesta.getUsuario());
        this.itens = cesta.getItens()
            .stream()
            .map(item -> new ItemDTO(item))
            .collect(Collectors.toList());
    }
}
