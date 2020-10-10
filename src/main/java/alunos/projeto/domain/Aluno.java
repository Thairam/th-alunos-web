package alunos.projeto.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Aluno {
    private int id;
    
    @NonNull
    private String nome;
    
    @NonNull
    private String curso;
}