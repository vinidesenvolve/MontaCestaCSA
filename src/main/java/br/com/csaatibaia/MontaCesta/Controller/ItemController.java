package br.com.csaatibaia.MontaCesta.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.csaatibaia.MontaCesta.DTO.ItemDTO;
import br.com.csaatibaia.MontaCesta.Service.ItemService;

@RestController
@RequestMapping(path="/item")
public class ItemController {
    
    @Autowired
    ItemService itemService;

    @PostMapping 
    public @ResponseBody String cadastrarItem(@RequestBody @Valid ItemDTO itemDTO){
        
        return itemService.cadastrar(itemDTO);
    }

    @GetMapping
    public @ResponseBody List<ItemDTO> buscarItens(){

        return itemService.buscarTodos();
    }

    @GetMapping("/{id}")
    public @ResponseBody ItemDTO buscarItenPorId(@PathVariable Long id){
        
        return itemService.buscarPorId(id);
    } 

    @GetMapping("/nome/{nome}")
    public @ResponseBody ItemDTO buscarItemPorNome(@PathVariable String nome){
        
        return itemService.buscarPorNome(nome);
    }

    @PutMapping("/{id}")
    public @ResponseBody String alterarItem(@PathVariable Long id, @RequestBody ItemDTO itemDTO){

        return itemService.alterar(id, itemDTO);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String excluirItem(@PathVariable Long id){

        return itemService.excluir(id);
    }
    
}
