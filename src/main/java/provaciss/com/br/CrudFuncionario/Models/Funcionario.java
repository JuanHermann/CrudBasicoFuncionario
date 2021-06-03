package provaciss.com.br.CrudFuncionario.Models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 30, message
            = "Nome precisa estar entre 2 e 30 caracteres")
    private String nome;

    @Size(min = 2, max = 30, message
            = "Sobrenome precisa estar entre 2 e 30 caracteres")
    private String sobrenome;

    @Email(message = "Email deve ser valido")
    private String email;

    private Long pis;
}
