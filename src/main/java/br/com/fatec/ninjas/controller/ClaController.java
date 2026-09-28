package br.com.fatec.ninjas.controller;

import br.com.fatec.ninjas.model.Cla;
import br.com.fatec.ninjas.service.ClaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/cla", "/clas"})
public class ClaController {

    @Autowired
    private ClaService claService;

    @PostMapping
    public Cla cadastrarCla(@Valid @RequestBody Cla cla) {
        return claService.cadastrarCla(cla);
    }

    @GetMapping
    public List<Cla> listarClas() {
        return claService.listarClas();
    }

    @GetMapping("/id/{id}")
    public Optional<Cla> pesquisarCla(@PathVariable Long id) {
        return claService.pesquisarCla(id);
    }

    @GetMapping("/nome/{nome}")
    public Cla pesquisarClaPorNome(@PathVariable String nome) {
        return claService.pesquisarClaPorNome(nome);
    }

    @GetMapping("/descricao/{descricao}")
    public List<Cla> pesquisarClaPorDescricao(@PathVariable String descricao) {
        return claService.pesquisarClaPorParteDaDescricao(descricao);
    }

    @PutMapping("/{id}")
    public Cla atualizarCla(@PathVariable Long id, @Valid @RequestBody Cla cla) {
        return claService.atualizarCla(id, cla);
    }

    @DeleteMapping("/{id}")
    public void deletarCla(@PathVariable Long id) {
        claService.deletarCla(id);
    }
}
