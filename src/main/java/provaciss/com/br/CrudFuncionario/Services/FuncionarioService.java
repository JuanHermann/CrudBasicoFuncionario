package provaciss.com.br.CrudFuncionario.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import provaciss.com.br.CrudFuncionario.Models.Funcionario;
import provaciss.com.br.CrudFuncionario.Repositories.FuncionarioRepository;

import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public List<Funcionario> listAll() {
        return repository.findAll();
    }

    public ResponseEntity findById(Long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(funcionarioNaoEncontrado(id));

    }

    public ResponseEntity create(Funcionario funcionario) {
        funcionario.setId(null);
        return ResponseEntity.created(URI.create("")).body(repository.save(funcionario));
    }


    public ResponseEntity update(Long id, Funcionario funcionario) {
        if (verificarFuncionario(id)) {
            funcionario.setId(id);
            repository.save(funcionario);

            return ResponseEntity.ok().body("Funcionário alterado com sucesso.");
        }
        return funcionarioNaoEncontrado(id);

    }

    public ResponseEntity delete(Long id) {
        if (verificarFuncionario(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().body("Funcionário excluido com sucesso.");
        }

        return funcionarioNaoEncontrado(id);
    }

    public boolean verificarFuncionario(Long id) {
        return repository.findById(id).isPresent();
    }

    public ResponseEntity funcionarioNaoEncontrado(Long id) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Funcionário com o ID %s não encontrado!", id));
    }


}
