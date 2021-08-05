package br.com.csaatibaia.montacesta.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.csaatibaia.montacesta.model.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO {
    
    private Long id;

    @NotBlank
    @Email(message = "Formato de e-mail inválido.")
    private String email;
    
    @NotBlank(message = "Defina uma senha.")
    @Size(min = 6, message = "Senha deve conter no mínimo 6 caracteres.")
    private String senha;

    @NotBlank(message = "Informe o nome.")
    private String nome;

    @NotBlank(message = "Informe o perfil.")
    private String perfil;

    @NotNull(message = "Informe a quantidade de itens da cesta.")
    private Integer tipoCesta;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.nome = usuario.getNome();
        this.perfil = usuario.getPerfil();
        this.tipoCesta = usuario.getTipoCesta();
    }
}
