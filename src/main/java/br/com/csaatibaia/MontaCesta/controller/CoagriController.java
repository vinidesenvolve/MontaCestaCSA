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

import br.com.csaatibaia.MontaCesta.dto.CoagriDTO;
import br.com.csaatibaia.MontaCesta.service.CoagriService;

@Controller
@RequestMapping(path="/coagri")
public class CoagriController {

    @Autowired
    CoagriService coagriService;

    @PostMapping
    public ResponseEntity<String> cadastrarCoagri (@RequestBody @Valid CoagriDTO coagriDTO){
        return coagriService.cadastrar(coagriDTO);
    }

    @GetMapping
    public ResponseEntity<List<CoagriDTO>> buscarCoagris(){
        return coagriService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoagriDTO> buscarCoagriPorId(@PathVariable Long id){
        return coagriService.buscarPorId(id);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CoagriDTO> buscarCoagriPorEmail(@PathVariable String email){
        return coagriService.buscarPorEmail(email);
    }

    //Get por nome

    @PutMapping("/{id}")
    public ResponseEntity<String> alterarCoagri(@PathVariable Long id, @Valid @RequestBody CoagriDTO coagriDTO){
            
        return coagriService.alterar(id, coagriDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirCoagri(@PathVariable Long id){
        
        return coagriService.excluir(id);
    }
    
}
