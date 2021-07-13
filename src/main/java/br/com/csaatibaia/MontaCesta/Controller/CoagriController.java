package br.com.csaatibaia.MontaCesta.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.csaatibaia.MontaCesta.DTO.CoagriDTO;
import br.com.csaatibaia.MontaCesta.Model.Cesta;
import br.com.csaatibaia.MontaCesta.Model.Coagri;
import br.com.csaatibaia.MontaCesta.Repository.CestaRepository;
import br.com.csaatibaia.MontaCesta.Repository.CoagriRepository;
import br.com.csaatibaia.MontaCesta.Service.CoagriService;

@Controller
@RequestMapping(path="/coagri")
public class CoagriController {

    @Autowired
    CoagriService coagriService;

    @PostMapping
    public @ResponseBody String cadastrarCoagri (@RequestBody CoagriDTO coagriDTO){
        
        return coagriService.cadastrar(coagriDTO);
    }

    @GetMapping
    public @ResponseBody List<Coagri> buscarCoagris(){
        
        return coagriService.buscarTodos();
    }
}
