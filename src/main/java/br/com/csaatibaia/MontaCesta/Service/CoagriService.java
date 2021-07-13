package br.com.csaatibaia.MontaCesta.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.csaatibaia.MontaCesta.DTO.CoagriDTO;
import br.com.csaatibaia.MontaCesta.Model.Coagri;
import br.com.csaatibaia.MontaCesta.Repository.CoagriRepository;

@Service
public class CoagriService {
    
    @Autowired
    CoagriRepository coagriRepo;
    
    public String cadastrar(CoagriDTO coagriDTO){

    Coagri coagri = new Coagri();

        coagri.setEmail(coagriDTO.getEmail());
        coagri.setSenha(coagriDTO.getSenha());
        coagri.setNome(coagriDTO.getNome());
        coagri.setTipoCesta(coagriDTO.getTipoCesta());
        
        coagriRepo.save(coagri);

        return "Coagri cadastrado!";
    }
     
    public List<Coagri> buscarTodos(){
        return coagriRepo.findAll();
    }
}
