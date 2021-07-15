package br.com.csaatibaia.MontaCesta.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

        Coagri coagri = new Coagri();

        coagri.setEmail(coagriDTO.getEmail());
        coagri.setSenha(coagriDTO.getSenha());
        coagri.setNome(coagriDTO.getNome());
        coagri.setTipoCesta(coagriDTO.getTipoCesta());
        
        coagriRepo.save(coagri);

        return ResponseEntity.ok("Coagri cadastrado!");
    }
     
    public List<CoagriDTO> buscarTodos(){

        return coagriRepo.findAll()
            .stream()
            .map(e -> converteParaCoagriDTO(e))
            .collect(Collectors.toList());
    }

    public CoagriDTO buscarPorEmail(String email){

        Coagri coagri = coagriRepo.findByEmail(email);

        return converteParaCoagriDTO(coagri);
    }

    public CoagriDTO buscarPorId(Long id) {

        Coagri coagri = coagriRepo.findById(id).get();
       
        return converteParaCoagriDTO(coagri);
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

    private CoagriDTO converteParaCoagriDTO(Coagri coagri) {

        CoagriDTO coagriDTO = new CoagriDTO();
        
        coagriDTO.setId(coagri.getId());
        coagriDTO.setEmail(coagri.getEmail());
        coagriDTO.setSenha(coagri.getSenha());
        coagriDTO.setNome(coagri.getNome());
        coagriDTO.setTipoCesta(coagri.getTipoCesta());

        return coagriDTO;
    }

}
