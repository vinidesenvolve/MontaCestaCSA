package br.com.csaatibaia.montacesta.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.csaatibaia.montacesta.dto.CestaDTO;
import br.com.csaatibaia.montacesta.service.CestaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping(path="/cesta")
public class CestaController {
    
    @Autowired
    CestaService cestaService;
    
    @PostMapping("/{usuarioId}")
    public ResponseEntity<String> fazerCesta(@Valid
        @PathVariable Long usuarioId, 
        @RequestBody List<Long> itensPedidosId){
        
        return cestaService.fazer(usuarioId, itensPedidosId);
    }

    @GetMapping
    public ResponseEntity<List<CestaDTO>> buscarCestas(){
        return cestaService.buscarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CestaDTO> buscarCestaPorId(@PathVariable Long id){
        return cestaService.buscarPorId(id);
    }   
    
    @PutMapping("/{id}")
    public ResponseEntity<String> alterarCesta(@PathVariable Long id, @RequestBody CestaDTO cestaDTO){
        return cestaService.alterar(id, cestaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirCesta(@PathVariable Long id){
        return cestaService.excluir(id);
    }
}
