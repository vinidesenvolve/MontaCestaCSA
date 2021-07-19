package br.com.csaatibaia.MontaCesta.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

    public String cadastrar(@Valid ItemDTO itemDTO){

            if(itemRepo.existsItemByNome(itemDTO.getNome())){
                return "Nome indisponível, item já cadastrado";
            }

            Item item = new Item();

            item.setNome(itemDTO.getNome());
            item.setDescricao(itemDTO.getDescricao());
            item.setQuantidade(itemDTO.getQuantidade());
            item.setOrigem(itemDTO.getOrigem());
    
            itemRepo.save(item);
    
            return "Item cadastrado!";
        
    }

    public List<ItemDTO> buscarTodos(){

        return itemRepo
            .findAll()
            .stream()
            .map(e -> new ItemDTO(e))
            .collect(Collectors.toList());
    } 

    public ItemDTO buscarPorId(Long id){

        Item item = itemRepo.findById(id).get();
    
        return new ItemDTO(item);
    }

    public ItemDTO buscarPorNome(String nome){

        Item item = itemRepo.findByNome(nome);

        return new ItemDTO(item);
    }

    public String alterar(Long id, ItemDTO itemDTO){

        Item item = itemRepo.findById(id).get();
    
        item.setNome(itemDTO.getNome());
        item.setDescricao(itemDTO.getDescricao());
        item.setQuantidade(itemDTO.getQuantidade());
        item.setOrigem(itemDTO.getOrigem());

        itemRepo.save(item);

        return "Item alterado!";
    }

    public String excluir(Long id){

        itemRepo.deleteById(id);

        return "Item excluído!";
    }
    
}
