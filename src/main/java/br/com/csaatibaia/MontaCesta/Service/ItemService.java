package br.com.csaatibaia.MontaCesta.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.csaatibaia.MontaCesta.DTO.ItemDTO;
import br.com.csaatibaia.MontaCesta.Model.Item;
import br.com.csaatibaia.MontaCesta.Repository.ItemRepository;

@Service
@Validated
public class ItemService {
    
    @Autowired
    ItemRepository itemRepo;

    public ResponseEntity<String> cadastrar(ItemDTO itemDTO){

        if(itemRepo.existsItemByNome(itemDTO.getNome())){
            return new ResponseEntity<>(
                "Nome indisponível.",
                HttpStatus.CONFLICT);
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

        return new ResponseEntity<>(itensDTO, HttpStatus.FOUND);

    } 

    public ResponseEntity<ItemDTO> buscarPorId(Long id){

        if(!isIdValid(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Item item = itemRepo.findById(id).get();        
        
        ItemDTO itemDTO = new ItemDTO(item);

        return new ResponseEntity<>(itemDTO, HttpStatus.FOUND);
    }

    public ResponseEntity<ItemDTO> buscarPorNome(String nome){

        if(!itemRepo.existsItemByNome(nome)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        Item item = itemRepo.findItemByNome(nome);

        ItemDTO itemDTO = new ItemDTO(item);

        return new ResponseEntity<>(itemDTO, HttpStatus.FOUND);
    }

    public ResponseEntity<String> alterar(Long id, ItemDTO itemDTO){

        if(!isIdValid(id)){
            return new ResponseEntity<>(
                "Item inexistente ou não encontrado, verifique se o id é válido.",
                HttpStatus.NOT_FOUND);
        }

        Item item = itemRepo.findById(id).get();
    
        item.setNome(itemDTO.getNome());
        item.setDescricao(itemDTO.getDescricao());
        item.setQuantidade(itemDTO.getQuantidade());
        item.setOrigem(itemDTO.getOrigem());

        itemRepo.save(item);

        return new ResponseEntity<>("Item alterado!", HttpStatus.OK);
    }

    public ResponseEntity<String> excluir(Long id){

        if(!isIdValid(id)){
            return new ResponseEntity<>(
                "Item inexistente ou não encontrado, verifique se o id é válido.",
                HttpStatus.NOT_FOUND);
        }

        itemRepo.deleteById(id);

        return new ResponseEntity<>("Item excluído!", HttpStatus.OK);
    }
    
    private Boolean isIdValid(Long id){

        if(id <= 0){
            return false;
        }

        Optional<Item> item = itemRepo.findById(id);
    
        if(item.isPresent()){
            return true; 
        }
        
        return false;
    }
}
