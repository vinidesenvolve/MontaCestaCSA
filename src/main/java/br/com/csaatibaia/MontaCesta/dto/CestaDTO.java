package br.com.csaatibaia.MontaCesta.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import br.com.csaatibaia.MontaCesta.model.Cesta;
import br.com.csaatibaia.MontaCesta.model.Item;
import br.com.csaatibaia.MontaCesta.model.Usuario;
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
    Usuario usuario;

    @NotBlank
    List<Item> itens;

    public CestaDTO(Cesta cesta) {
        this.id = cesta.getId();
        this.data = cesta.getData();
        this.usuario = cesta.getUsuario();
        this.itens = cesta.getItens();
    }
}
