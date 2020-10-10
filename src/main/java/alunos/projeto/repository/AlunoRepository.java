package alunos.projeto.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alunos.projeto.domain.Aluno;

public class AlunoRepository {
    private static int cont = 1;
    private Map<Integer, Aluno> alunos = new HashMap<>();

    public List<Aluno> getAll() {
        return new ArrayList<Aluno>(alunos.values());
    }

    public Aluno getById(int id) throws Exception {
        Aluno aluno = alunos.get(id);

        if (aluno == null) {
            throw new Exception("Aluno não encontrado!");
        }

        return aluno;
    }

    public void create(Aluno aluno) {
        if (aluno.getId() == 0)
            aluno.setId(generateId());

        alunos.put(aluno.getId(), aluno);
    }

    public void delete(int id) throws Exception {
        Aluno alunoDeletado = alunos.get(id);

        if (alunoDeletado == null) {
            throw new Exception("Aluno não encontrado!");
        }

        alunos.remove(id);
    }

    public void edit(Aluno aluno) throws Exception {
        Aluno alunoAlterado = alunos.get(aluno.getId());

        if (alunoAlterado == null) {
            throw new Exception("Aluno não encontrado!");
        }

        alunos.put(aluno.getId(), aluno);
    }

    private int generateId() {
        return cont++;
    }
}