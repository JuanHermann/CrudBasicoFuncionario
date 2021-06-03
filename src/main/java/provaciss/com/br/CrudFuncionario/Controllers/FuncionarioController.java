package provaciss.com.br.CrudFuncionario.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import provaciss.com.br.CrudFuncionario.Models.Funcionario;
import provaciss.com.br.CrudFuncionario.Repositories.FuncionarioRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired(required = false)
    private FuncionarioRepository repository;

    @GetMapping
    public List<Funcionario> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario createFuncionario(@RequestBody @Valid Funcionario funcionario) {
        return repository.save(funcionario);
    }

    @PutMapping
    public ResponseEntity updateById(@PathVariable Long id, @RequestBody @Valid Funcionario funcionario) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity deleteById(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().body("deleted");
    }


}
