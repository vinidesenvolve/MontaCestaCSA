package br.com.csaatibaia.MontaCesta.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.csaatibaia.MontaCesta.model.Cesta;
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

    @NotNull
    int tipo;

    public CestaDTO(Cesta cesta) {
        this.id = cesta.getId();
        this.data = cesta.getData();
        this.tipo = cesta.getTipo();
    }
}
