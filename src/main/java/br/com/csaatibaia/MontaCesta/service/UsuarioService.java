package br.com.csaatibaia.MontaCesta.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.csaatibaia.MontaCesta.dto.UsuarioDTO;
import br.com.csaatibaia.MontaCesta.model.Usuario;
import br.com.csaatibaia.MontaCesta.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepo;
    
    public ResponseEntity<String> cadastrar(UsuarioDTO usuarioDTO){

        if(usuarioRepo.findByEmail(usuarioDTO.getEmail()).isPresent()){
            return new ResponseEntity<>("Email indisponível!", HttpStatus.CONFLICT);
        }

        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setPerfil(usuarioDTO.getPerfil());
        usuario.setTipoCesta(usuarioDTO.getTipoCesta());

        usuarioRepo.save(usuario);

        return new ResponseEntity<>("Usuário cadastrado!", HttpStatus.CREATED);
    }
     
    public ResponseEntity<List<UsuarioDTO>> buscarTodos(){

        List<Usuario> usuarios = usuarioRepo.findAll();

        if(usuarios.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UsuarioDTO> coagrisDTO = usuarios.stream()
            .map(usuario -> new UsuarioDTO(usuario))
            .collect(Collectors.toList());

        return new ResponseEntity<>(coagrisDTO, HttpStatus.OK);
    }

    public ResponseEntity<UsuarioDTO> buscarPorId(Long id) {

        Optional<Usuario> usuario = usuarioRepo.findById(id);

        if(usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.get());
       
        return new ResponseEntity<>(usuarioDTO, HttpStatus.FOUND);
    }
    
    public ResponseEntity<UsuarioDTO> buscarPorEmail(String email){

        Optional<Usuario> usuario = usuarioRepo.findByEmail(email);

        if(usuario.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.get());

        return new ResponseEntity<>(usuarioDTO, HttpStatus.FOUND);
    }

    public ResponseEntity<String> alterar(Long id, UsuarioDTO usuarioDTO){

        Optional<Usuario> usuario = usuarioRepo.findById(id);

        if(usuario.isEmpty()) {
            return new ResponseEntity<>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
        }

        Usuario usuarioAtualizado = usuario.get();

        usuarioAtualizado.setEmail(usuarioDTO.getEmail());
        usuarioAtualizado.setSenha(usuarioDTO.getSenha());
        usuarioAtualizado.setNome(usuarioDTO.getNome());
        usuarioAtualizado.setPerfil(usuarioDTO.getPerfil());
        usuarioAtualizado.setTipoCesta(usuarioDTO.getTipoCesta());
        
        usuarioRepo.save(usuarioAtualizado);

        return new ResponseEntity<>("Usuário alterado!", HttpStatus.OK);        
    }

    public ResponseEntity<String> excluir(Long id) {

        Optional<Usuario> usuario = usuarioRepo.findById(id);

        if(usuario.isEmpty()) {
            return new ResponseEntity<>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
        }

        usuarioRepo.deleteById(id);

        return new ResponseEntity<>("Usuário excluído!", HttpStatus.OK);
    }

}
