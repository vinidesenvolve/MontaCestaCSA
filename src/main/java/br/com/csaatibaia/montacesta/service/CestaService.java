package br.com.csaatibaia.montacesta.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.csaatibaia.montacesta.dto.CestaDTO;
import br.com.csaatibaia.montacesta.model.Cesta;
import br.com.csaatibaia.montacesta.model.Item;
import br.com.csaatibaia.montacesta.model.Usuario;
import br.com.csaatibaia.montacesta.repository.CestaRepository;
import br.com.csaatibaia.montacesta.repository.ItemRepository;
import br.com.csaatibaia.montacesta.repository.UsuarioRepository;
import br.com.csaatibaia.montacesta.util.Data;

@Service
public class CestaService {

    @Autowired
    CestaRepository cestaRepo;

    @Autowired
    UsuarioRepository usuarioRepo;

    @Autowired
    ItemRepository itemRepo;
    
    public ResponseEntity<String> fazer(Long usuarioId, List<Long> itensPedidosId) {
        /**
         * Não zerar itens 
         * 
         * Quantidade de itens 4, 8, 12
         *
         * Uma cesta por semana 
         * 
         * Lista de itens
         * 
         * Perfil do usuário
         */

        //Carrega Usuário
        Optional<Usuario> usuario = usuarioRepo.findById(usuarioId);

        if(usuario.isEmpty()){
            return new ResponseEntity<>("Usuário inválido.", HttpStatus.NOT_FOUND);
        }

        //Carrega Lista Itens
        List<Item> itens = new ArrayList<>();

        itensPedidosId.stream().forEach(id -> {
            Optional<Item> item = itemRepo.findById(id);

            if(item.isPresent()){
                itens.add(item.get());
            }
        });

        if(itens.isEmpty()){
            return new ResponseEntity<>("Itens não encontrados.", HttpStatus.NOT_FOUND);
        }

        if(itens.size() != usuario.get().getTipoCesta()){
            return new ResponseEntity<>("Quantidade de itens inválida.", HttpStatus.BAD_REQUEST);
        }

        String data = Data.pegarData();

        Cesta cesta = new Cesta();

        cesta.setData(data);
        cesta.setUsuario(usuario.get());
        cesta.setItens(itens);

        cestaRepo.save(cesta);

        return new ResponseEntity<>("Cesta feita!", HttpStatus.CREATED);
    }

    public ResponseEntity<List<CestaDTO>> buscarTodas() {
        
        List<Cesta> cestas = cestaRepo.findAll();

        if(cestas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<CestaDTO> cestasDTO = cestas.stream()
            .map(cesta -> new CestaDTO(cesta))
            .collect(Collectors.toList());

        return new ResponseEntity<>(cestasDTO, HttpStatus.OK);
    }

    public ResponseEntity<CestaDTO> buscarPorId(Long id) {
        
        Optional<Cesta> cesta = cestaRepo.findById(id);
        
        if(cesta.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CestaDTO cestaDTO = new CestaDTO(cesta.get());

        return new ResponseEntity<>(cestaDTO, HttpStatus.FOUND);
    }

    //IMPLEMENTAR
    public ResponseEntity<String> alterar(Long id, CestaDTO cestaDTO) {
        return new ResponseEntity<>("Opção não disponível...", HttpStatus.OK);
    }

    public ResponseEntity<String> excluir(Long id) {
        
        Optional<Cesta> cesta = cestaRepo.findById(id);

        if(cesta.isEmpty()){
            return new ResponseEntity<>("Cesta não encontrada", HttpStatus.NOT_FOUND);
        }

        cestaRepo.deleteById(id);

        return new ResponseEntity<>("Cesta excluída!", HttpStatus.OK);
    }
 
}
