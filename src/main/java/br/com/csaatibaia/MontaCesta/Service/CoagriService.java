package br.com.csaatibaia.MontaCesta.Service;

import java.util.List;
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

        if(coagriRepo.existsCoagriEmail(coagriDTO.getEmail())){
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
     
    public List<CoagriDTO> buscarTodos(){

        return coagriRepo.findAll()
            .stream()
            .map(e -> new CoagriDTO(e))
            .collect(Collectors.toList());
    }

    public CoagriDTO buscarPorEmail(String email){

        Coagri coagri = coagriRepo.findCoagriByEmail(email);

        return new CoagriDTO(coagri);
    }

    public CoagriDTO buscarPorId(Long id) {

        Coagri coagri = coagriRepo.findById(id).get();
       
        return new CoagriDTO(coagri);
    }

    public String alterar(Long id, CoagriDTO coagriDTO){

        Coagri coagri = coagriRepo.findById(id).get();

        coagri.setEmail(coagriDTO.getEmail());
        coagri.setSenha(coagriDTO.getSenha());
        coagri.setNome(coagriDTO.getNome());
        coagri.setTipoCesta(coagriDTO.getTipoCesta());
        
        coagriRepo.save(coagri);

        return "Coagri alterado!";        
    }

    public String excluir(Long id) {

        coagriRepo.deleteById(id);

        return "Coagri excluído!";
    }

}
