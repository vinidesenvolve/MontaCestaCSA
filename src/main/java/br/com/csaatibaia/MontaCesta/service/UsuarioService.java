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
    UsuarioRepository coagriRepo;
    
    public ResponseEntity<String> cadastrar(UsuarioDTO coagriDTO){

        if(coagriRepo.existsCoagriByEmail(coagriDTO.getEmail())){
            return new ResponseEntity<>("Email indisponível", HttpStatus.CONFLICT);
        }

        Usuario coagri = new Usuario();

        coagri.setEmail(coagriDTO.getEmail());
        coagri.setSenha(coagriDTO.getSenha());
        coagri.setNome(coagriDTO.getNome());
        coagri.setTipoCesta(coagriDTO.getTipoCesta());

        coagriRepo.save(coagri);

        return new ResponseEntity<>("Coagri cadastrado!", HttpStatus.CREATED);
    }
     
    public ResponseEntity<List<UsuarioDTO>> buscarTodos(){

        List<Usuario> coagris = coagriRepo.findAll();

        if(coagris.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UsuarioDTO> coagrisDTO = coagris.stream()
            .map(coagri -> new UsuarioDTO(coagri))
            .collect(Collectors.toList());

        return new ResponseEntity<>(coagrisDTO, HttpStatus.FOUND);
    }

    public ResponseEntity<UsuarioDTO> buscarPorId(Long id) {

        if(!isIdValid(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Usuario coagri = coagriRepo.findById(id).get();

        UsuarioDTO coagriDTO = new UsuarioDTO(coagri);
       
        return new ResponseEntity<>(coagriDTO, HttpStatus.FOUND);
    }
    
    public ResponseEntity<UsuarioDTO> buscarPorEmail(String email){

        if(!coagriRepo.existsCoagriByEmail(email)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Usuario coagri = coagriRepo.findCoagriByEmail(email);

        UsuarioDTO coagriDTO = new UsuarioDTO(coagri);

        return new ResponseEntity<>(coagriDTO, HttpStatus.FOUND);
    }

    public ResponseEntity<String> alterar(Long id, UsuarioDTO coagriDTO){

        if(!isIdValid(id)){
            return new ResponseEntity<>(
                "Coagri inexistente ou não encontrado, verifique se o id é válido.",
                HttpStatus.NOT_FOUND);
        }

        Usuario coagri = coagriRepo.findById(id).get();

        coagri.setEmail(coagriDTO.getEmail());
        coagri.setSenha(coagriDTO.getSenha());
        coagri.setNome(coagriDTO.getNome());
        coagri.setTipoCesta(coagriDTO.getTipoCesta());
        
        coagriRepo.save(coagri);

        return new ResponseEntity<>("Coagri alterado!", HttpStatus.OK);        
    }

    public ResponseEntity<String> excluir(Long id) {

        if(!isIdValid(id)){
            return new ResponseEntity<>(
                "Coagri inexistente ou não encontrado, verifique se o id é válido.",
                HttpStatus.NOT_FOUND);
        }

        coagriRepo.deleteById(id);

        return new ResponseEntity<>("Coagri excluído!", HttpStatus.OK);
    }

    private Boolean isIdValid(Long id){

        if(id <= 0){
            return false;
        }

        Optional<Usuario> coagri = coagriRepo.findById(id);
    
        if(coagri.isPresent()){
            return true; 
        }
        
        return false;
    }

}
