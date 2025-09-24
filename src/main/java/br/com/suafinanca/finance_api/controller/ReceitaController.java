package br.com.suafinanca.finance_api.controller;

import br.com.suafinanca.finance_api.model.Receita; // <-- MUDOU para Receita
import br.repository.ReceitaRepository; // <-- MUDOU para ReceitaRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/receitas") // <-- MUDOU para o endpoint de receitas
public class ReceitaController {

    @Autowired
    private ReceitaRepository receitaRepository; // <-- MUDOU: Injetando o repositório CORRETO

    // 1. MÉTODO PARA LISTAR TODAS AS RECEITAS
    @GetMapping
    public List<Receita> listarTodas() {
        return receitaRepository.findAll(); // <-- MUDOU
    }

    // 2. MÉTODO PARA CRIAR UMA NOVA RECEITA
    @PostMapping
    public Receita criar(@RequestBody Receita novaReceita) { // <-- MUDOU
        return receitaRepository.save(novaReceita); // <-- MUDOU
    }

    // 3. MÉTODO PARA ATUALIZAR UMA RECEITA
    @PutMapping("/{id}")
    public ResponseEntity<Receita> atualizar(@PathVariable Long id, @RequestBody Receita receitaDetalhes) { // <-- MUDOU
        return receitaRepository.findById(id) // <-- MUDOU
                .map(receitaExistente -> {
                    receitaExistente.setDescricao(receitaDetalhes.getDescricao());
                    receitaExistente.setValor(receitaDetalhes.getValor());
                    receitaExistente.setData(receitaDetalhes.getData());
                    receitaExistente.setCategoria(receitaDetalhes.getCategoria());
                    Receita atualizada = receitaRepository.save(receitaExistente); // <-- MUDOU
                    return ResponseEntity.ok(atualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. MÉTODO PARA DELETAR UMA RECEITA
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return receitaRepository.findById(id) // <-- MUDOU
                .map(receita -> {
                    receitaRepository.delete(receita); // <-- MUDOU
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}