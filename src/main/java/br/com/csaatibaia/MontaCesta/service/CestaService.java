package br.com.csaatibaia.MontaCesta.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.csaatibaia.MontaCesta.dto.CestaDTO;
import br.com.csaatibaia.MontaCesta.model.Cesta;
import br.com.csaatibaia.MontaCesta.model.Usuario;
import br.com.csaatibaia.MontaCesta.model.Item;
import br.com.csaatibaia.MontaCesta.repository.CestaRepository;
import br.com.csaatibaia.MontaCesta.repository.CoagriRepository;
import br.com.csaatibaia.MontaCesta.repository.ItemRepository;

@Service
public class CestaService {

    @Autowired
    CestaRepository cestaRepo;

    @Autowired
    CoagriRepository coagriRepo;

    @Autowired
    ItemRepository itemRepo;
    
    public ResponseEntity<String> fazer(Long coagriId, List<String> itensPedidos) {
        /**
         * Quantidade de itens 4, 8, 12
         *
         * Uma cesta por semana 
         * 
         * Lista de itens
         */
        if(!isCoagriIdValid(coagriId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        Usuario coagri = coagriRepo.findById(coagriId).get();

        List<Item> itens = new ArrayList<>();

        itensPedidos.stream()
            .forEach(item -> {
            if(itemRepo.existsItemByNome(item)){
                itens.add(itemRepo.findItemByNome(item));
            }
        });

        String data = pegarData();

        Cesta cesta = new Cesta();

        cesta.setData(data);
        cesta.setCoagri(coagri);
        cesta.setTipo(coagri.getTipoCesta());

        cesta.setItens(itens);

        cestaRepo.save(cesta);

        return new ResponseEntity<>("Cesta feita!", HttpStatus.CREATED);
    }

    public List<CestaDTO> buscarTodas() {
        
        return cestaRepo
            .findAll()
            .stream()
            .map(e -> new CestaDTO(e))
            .collect(Collectors.toList());
    }

    public ResponseEntity<CestaDTO> buscarPorId(Long id) {
        
        Cesta cesta = cestaRepo.findById(id).get();
        
        return ResponseEntity.ok().body(new CestaDTO(cesta));
    }

    public ResponseEntity<String> alterar(Long id, CestaDTO cestaDTO) {
        
        Cesta cesta = cestaRepo.findById(id).get();
        
        cesta.setData(cestaDTO.getData());
        cesta.setTipo(cestaDTO.getTipo());

        cestaRepo.save(cesta);

        return ResponseEntity.ok("Cesta alterada!");
    }

    public ResponseEntity<String> excluir(Long id) {
        
        cestaRepo.deleteById(id);

        return ResponseEntity.ok("Cesta excluída!");
    }

    private String pegarData(){
        
        LocalDate localDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y");

        String data = localDate.format(formatter);

        return data;
    }

    private Boolean isCoagriIdValid(Long id){

        if(id <= 0){
            return false;
        }

        Optional<Usuario> coagri = coagriRepo.findById(id);
    
        if(coagri.isPresent()){
            return true; 
        }
        
        return false;
    }

    // private Boolean isIdValid(Long id){

    //     if(id <= 0){
    //         return false;
    //     }

    //     Optional<Cesta> cesta = cestaRepo.findById(id);
    
    //     if(cesta.isPresent()){
    //         return true; 
    //     }
        
    //     return false;
    // }
}
