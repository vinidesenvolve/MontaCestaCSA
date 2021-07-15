package br.com.csaatibaia.MontaCesta.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.csaatibaia.MontaCesta.DTO.CestaDTO;
import br.com.csaatibaia.MontaCesta.Model.Cesta;
import br.com.csaatibaia.MontaCesta.Model.Coagri;
import br.com.csaatibaia.MontaCesta.Repository.CestaRepository;
import br.com.csaatibaia.MontaCesta.Repository.CoagriRepository;

@Service
public class CestaService {

    @Autowired
    CestaRepository cestaRepo;

    @Autowired
    CoagriRepository coagriRepo;

    public ResponseEntity<String> fazer(@Valid CestaDTO cestaDTO, Long coagriId) {
        
        Coagri coagri = coagriRepo.findById(coagriId).get();

        Cesta cesta = new Cesta();

        cesta.setData(cestaDTO.getData());
        cesta.setCoagri(coagri);
        cesta.setTipo(coagri.getTipoCesta());

        cestaRepo.save(cesta);

        return ResponseEntity.ok("Cesta feita!");
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
    
}
