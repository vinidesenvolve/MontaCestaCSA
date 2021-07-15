package br.com.csaatibaia.MontaCesta.DTO;

import br.com.csaatibaia.MontaCesta.Model.Cesta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CestaDTO {
    
    Long id;

    String data;

    int tipo;

    public CestaDTO(Cesta cesta) {
        this.id = cesta.getId();
        this.data = cesta.getData();
        this.tipo = cesta.getTipo();
    }
}
