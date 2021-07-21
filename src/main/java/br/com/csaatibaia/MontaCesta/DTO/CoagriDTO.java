package br.com.csaatibaia.MontaCesta.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.csaatibaia.MontaCesta.Model.Coagri;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CoagriDTO {
    
    private Long id;

    @NotBlank
    @Email(message = "Formato de e-mail inválido.")
    private String email;
    
    @NotBlank(message = "Defina uma senha.")
    @Size(min = 6, message = "Senha deve conter no mínimo 6 caracteres.")
    private String senha;

    @NotBlank(message = "Informe o nome.")
    private String nome;

    @NotNull(message = "Informe a quantidade de itens da cesta.")
    private Integer tipoCesta;

    public CoagriDTO(Coagri coagri) {
        this.id = coagri.getId();
        this.email = coagri.getEmail();
        this.senha = coagri.getSenha();
        this.nome = coagri.getNome();
        this.tipoCesta = coagri.getTipoCesta();
    }
}
