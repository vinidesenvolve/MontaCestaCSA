package br.com.csaatibaia.MontaCesta.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.csaatibaia.MontaCesta.dto.ItemDTO;
import br.com.csaatibaia.MontaCesta.model.Item;
import br.com.csaatibaia.MontaCesta.repository.ItemRepository;

@Service
@Validated
public class ItemService {
    
    @Autowired
    ItemRepository itemRepo;

    public ResponseEntity<String> cadastrar(ItemDTO itemDTO){

        if(itemRepo.findByNome(itemDTO.getNome()).isPresent()){
            return new ResponseEntity<>("Nome indisponível.", HttpStatus.CONFLICT);
        }

        Item item = new Item();

        item.setNome(itemDTO.getNome());
        item.setDescricao(itemDTO.getDescricao());
        item.setQuantidade(itemDTO.getQuantidade());
        item.setOrigem(itemDTO.getOrigem());

        itemRepo.save(item);
    
        return new ResponseEntity<>("Item cadastrado!", HttpStatus.CREATED);
    }

    public ResponseEntity<List<ItemDTO>> buscarTodos(){

        List<Item> itens = itemRepo.findAll();
        
        if(itens.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<ItemDTO> itensDTO = itens.stream()
            .map(item -> new ItemDTO(item))
            .collect(Collectors.toList());

        return new ResponseEntity<>(itensDTO, HttpStatus.OK);

    } 

    public ResponseEntity<ItemDTO> buscarPorId(Long id){

        Optional<Item> item = itemRepo.findById(id);

        if(item.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ItemDTO itemDTO = new ItemDTO(item.get());

        return new ResponseEntity<>(itemDTO, HttpStatus.FOUND);
    }

    public ResponseEntity<ItemDTO> buscarPorNome(String nome){

        Optional<Item> item = itemRepo.findByNome(nome);

        if(item.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ItemDTO itemDTO = new ItemDTO(item.get());

        return new ResponseEntity<>(itemDTO, HttpStatus.FOUND);
    }

    public ResponseEntity<String> alterar(Long id, ItemDTO itemDTO){

        Optional<Item> item = itemRepo.findById(id);

        if(item.isEmpty()){
            return new ResponseEntity<>("Item não encontrado.", HttpStatus.NOT_FOUND);
        }

        Item itemAtualizado = item.get();
    
        itemAtualizado.setNome(itemDTO.getNome());
        itemAtualizado.setDescricao(itemDTO.getDescricao());
        itemAtualizado.setQuantidade(itemDTO.getQuantidade());
        itemAtualizado.setOrigem(itemDTO.getOrigem());

        itemRepo.save(itemAtualizado);

        return new ResponseEntity<>("Item alterado!", HttpStatus.OK);
    }

    public ResponseEntity<String> excluir(Long id){

        Optional<Item> item = itemRepo.findById(id);

        if(item.isEmpty()){
            return new ResponseEntity<>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
        }
        itemRepo.deleteById(id);

        return new ResponseEntity<>("Item excluído!", HttpStatus.OK);
    }

}
