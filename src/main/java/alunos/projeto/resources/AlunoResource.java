package alunos.projeto.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alunos.projeto.domain.Aluno;
import alunos.projeto.repository.AlunoRepository;

@RestController
@RequestMapping(value = "/api/alunos")
public class AlunoResource {
    private AlunoRepository repository = new AlunoRepository();

    @GetMapping
    public ResponseEntity<?> getAlunos() {
        return ResponseEntity.ok(repository.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAlunoById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(repository.getById(id));
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Aluno obj) {
        try {
            Aluno aluno = Aluno.builder().nome(obj.getNome()).curso(obj.getCurso()).build();
            repository.create(aluno);
        
            return new ResponseEntity<>(aluno, HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> alterarAluno(@PathVariable int id, @RequestBody Aluno obj) {
        try {
            Aluno aluno = Aluno.builder().id(id).nome(obj.getNome()).curso(obj.getCurso()).build();
            repository.edit(aluno);
        
            return new ResponseEntity<>(aluno, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removerAluno(@PathVariable int id){
        try {
            repository.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}