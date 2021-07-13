package br.com.csaatibaia.MontaCesta.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.csaatibaia.MontaCesta.Model.Cesta;
import br.com.csaatibaia.MontaCesta.Model.Coagri;
import br.com.csaatibaia.MontaCesta.Repository.CestaRepository;
import br.com.csaatibaia.MontaCesta.Repository.CoagriRepository;

@Controller
@RequestMapping(path="/coagri")
public class CoagriController {

    @Autowired
    CoagriRepository coagriRepo;
    CestaRepository cestaRepo;

    @PostMapping
    public @ResponseBody String adicionarCoagri (
        @RequestBody Coagri novoCoagri){

        Coagri coagri = new Coagri();

        coagri.setEmail(novoCoagri.getNome());
        coagri.setSenha(novoCoagri.getSenha());
        coagri.setNome(novoCoagri.getNome());
        
        coagri.setCesta(novoCoagri.getCesta());
        
        coagriRepo.save(coagri);

        return "Coagri cadastrado!";
    }

    @GetMapping
    public @ResponseBody List<Coagri> getAllCoagris(){
        return coagriRepo.findAll();
    }
}
