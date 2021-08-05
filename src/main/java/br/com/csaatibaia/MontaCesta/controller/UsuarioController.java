package br.com.csaatibaia.MontaCesta.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.csaatibaia.MontaCesta.dto.UsuarioDTO;
import br.com.csaatibaia.MontaCesta.service.UsuarioService;

@Controller
@RequestMapping(path="/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<String> cadastrarUsuario (@RequestBody @Valid UsuarioDTO usuarioDTO){
        return usuarioService.cadastrar(usuarioDTO);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarUsuarios(){
        return usuarioService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Long id){
        return usuarioService.buscarPorId(id);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorEmail(@PathVariable String email){
        return usuarioService.buscarPorEmail(email);
    }

    //Get por nome

    @PutMapping("/{id}")
    public ResponseEntity<String> alterarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO){
            
        return usuarioService.alterar(id, usuarioDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirUsuario(@PathVariable Long id){
        
        return usuarioService.excluir(id);
    }
    
}
