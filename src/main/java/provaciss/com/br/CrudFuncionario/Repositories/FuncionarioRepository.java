package provaciss.com.br.CrudFuncionario.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import provaciss.com.br.CrudFuncionario.Models.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
}
