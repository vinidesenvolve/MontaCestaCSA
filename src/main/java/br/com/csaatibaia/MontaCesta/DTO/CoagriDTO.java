package br.com.csaatibaia.MontaCesta.DTO;

import br.com.csaatibaia.MontaCesta.Model.Coagri;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CoagriDTO {
    
    private Long id;

    private String email;
    
    private String senha;

    private String nome;

    private Integer tipoCesta;

    public CoagriDTO(Coagri coagri) {
        this.id = coagri.getId();
        this.email = coagri.getEmail();
        this.senha = coagri.getSenha();
        this.nome = coagri.getNome();
        this.tipoCesta = coagri.getTipoCesta();
    }
}
