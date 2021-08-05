package br.com.csaatibaia.MontaCesta.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.csaatibaia.MontaCesta.dto.ItemDTO;
import br.com.csaatibaia.MontaCesta.service.ItemService;

@RestController
@RequestMapping(path="/item")
public class ItemController {
    
    @Autowired
    ItemService itemService;

    @PostMapping 
    public ResponseEntity<String> cadastrarItem(@RequestBody @Valid ItemDTO itemDTO){
        return itemService.cadastrar(itemDTO);
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> buscarItens(){
        return itemService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> buscarItenPorId(@PathVariable Long id){
        return itemService.buscarPorId(id);
    } 

    @GetMapping("/nome/{nome}")
    public ResponseEntity<ItemDTO> buscarItemPorNome(@PathVariable String nome){    
        return itemService.buscarPorNome(nome);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> alterarItem(@PathVariable Long id, @RequestBody @Valid ItemDTO itemDTO){
        return itemService.alterar(id, itemDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirItem(@PathVariable Long id){
        return itemService.excluir(id);
    }
    
}
