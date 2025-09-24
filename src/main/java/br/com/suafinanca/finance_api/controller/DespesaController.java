package br.com.suafinanca.finance_api.controller;

import br.com.suafinanca.finance_api.model.Despesa;
import br.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; 

@RestController 

@RequestMapping("/api/despesas") 

public class DespesaController {

    @Autowired
    private DespesaRepository despesaRepository;

    // METODO PARA LISTAR TODAS AS DESPESAS (GET /api/despesas)
    @GetMapping
    public List<Despesa> listarTodas() {
        return despesaRepository.findAll();
    }

    // METODO PARA CRIAR UMA NOVA DESPESA (POST /api/despesas)
    // <-- FUNCIONALIDADE ADICIONADA: O método de criar que faltava
    @PostMapping
    public Despesa criar(@RequestBody Despesa novaDespesa) {
        return despesaRepository.save(novaDespesa);
    }

    // 3. MÉTODO PARA ATUALIZAR UMA DESPESA (PUT /api/despesas/{id})
    @PutMapping("/{id}") // <-- CORRIGIDO: Usando o verbo PUT para atualizar
    public ResponseEntity<Despesa> atualizar(@PathVariable Long id, @RequestBody Despesa despesaDetalhes) {
        return despesaRepository.findById(id)
                .map(despesaExistente -> {
                    despesaExistente.setDescricao(despesaDetalhes.getDescricao());
                    despesaExistente.setValor(despesaDetalhes.getValor());
                    despesaExistente.setData(despesaDetalhes.getData());
                    despesaExistente.setCategoria(despesaDetalhes.getCategoria());
                    Despesa atualizada = despesaRepository.save(despesaExistente);
                    return ResponseEntity.ok(atualizada);
                })
                .orElse(ResponseEntity.notFound().build()); // <-- CORRIGIDO: 'ResponseEntity' e lógica de não encontrado
    }

    // 4. MÉTODO PARA DELETAR UMA DESPESA (DELETE /api/despesas/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return despesaRepository.findById(id)
                .map(despesa -> {
                    despesaRepository.delete(despesa);
                    return ResponseEntity.ok().build(); // Retorna 200 OK se deletou com sucesso
                })
                .orElse(ResponseEntity.notFound().build()); // Retorna 404 Not Found se a despesa não existia
    }
}