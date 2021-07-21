package br.com.csaatibaia.MontaCesta.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.csaatibaia.MontaCesta.DTO.CoagriDTO;
import br.com.csaatibaia.MontaCesta.Model.Coagri;
import br.com.csaatibaia.MontaCesta.Repository.CoagriRepository;

@Service
public class CoagriService {
    
    @Autowired
    CoagriRepository coagriRepo;
    
    public ResponseEntity<String> cadastrar(CoagriDTO coagriDTO){

        if(coagriRepo.existsCoagriByEmail(coagriDTO.getEmail())){
            return new ResponseEntity<>("Email indisponível", HttpStatus.CONFLICT);
        }

        Coagri coagri = new Coagri();

        coagri.setEmail(coagriDTO.getEmail());
        coagri.setSenha(coagriDTO.getSenha());
        coagri.setNome(coagriDTO.getNome());
        coagri.setTipoCesta(coagriDTO.getTipoCesta());

        coagriRepo.save(coagri);

        return new ResponseEntity<>("Coagri cadastrado!", HttpStatus.CREATED);
    }
     
    public ResponseEntity<List<CoagriDTO>> buscarTodos(){

        List<Coagri> coagris = coagriRepo.findAll();

        if(coagris.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<CoagriDTO> coagrisDTO = coagris.stream()
            .map(coagri -> new CoagriDTO(coagri))
            .collect(Collectors.toList());

        return new ResponseEntity<>(coagrisDTO, HttpStatus.FOUND);
    }

    public ResponseEntity<CoagriDTO> buscarPorId(Long id) {

        if(!isIdValid(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Coagri coagri = coagriRepo.findById(id).get();

        CoagriDTO coagriDTO = new CoagriDTO(coagri);
       
        return new ResponseEntity<>(coagriDTO, HttpStatus.FOUND);
    }
    
    public ResponseEntity<CoagriDTO> buscarPorEmail(String email){

        if(!coagriRepo.existsCoagriByEmail(email)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Coagri coagri = coagriRepo.findCoagriByEmail(email);

        CoagriDTO coagriDTO = new CoagriDTO(coagri);

        return new ResponseEntity<>(coagriDTO, HttpStatus.FOUND);
    }

    public ResponseEntity<String> alterar(Long id, CoagriDTO coagriDTO){

        if(!isIdValid(id)){
            return new ResponseEntity<>(
                "Coagri inexistente ou não encontrado, verifique se o id é válido.",
                HttpStatus.NOT_FOUND);
        }

        Coagri coagri = coagriRepo.findById(id).get();

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

        Optional<Coagri> coagri = coagriRepo.findById(id);
    
        if(coagri.isPresent()){
            return true; 
        }
        
        return false;
    }

}
