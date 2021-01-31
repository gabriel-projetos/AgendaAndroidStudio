package com.example.agenda.DAO;

import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDao {

    private final static List<Aluno> alunos = new ArrayList<>();

    public void salva(Aluno aluno) {
        alunos.add(aluno);
    }
//Devovler uma copia da lista é a melhor situação
    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}
