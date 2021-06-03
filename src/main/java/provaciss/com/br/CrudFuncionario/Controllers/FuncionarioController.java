package provaciss.com.br.CrudFuncionario.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import provaciss.com.br.CrudFuncionario.Models.Funcionario;
import provaciss.com.br.CrudFuncionario.Services.FuncionarioService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired(required = false)
    private FuncionarioService service;

    @GetMapping
    public List<Funcionario> findAll() {
        return service.listAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Funcionario funcionario) {
        return service.create(funcionario);
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid Funcionario funcionario) {
        return service.update(id, funcionario);

    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
