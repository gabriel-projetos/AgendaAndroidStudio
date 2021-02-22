package com.example.agenda.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//Indicando que é uma tabela
@Entity
//convert os objs para bytes
public class Aluno implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    //Removemos o final para as informações possam ser editaveis
    //private final String nome;

    private String nome;

    private String sobrenome;
    private String telefone;
    private String email;

    //o room usa o construtor sem argumentos, usamos o ignore para tirar os alertas
    @Ignore
    public Aluno(String nome, String telefone, String email, String sobrenome) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.sobrenome = sobrenome;
    }

    public Aluno() {

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }


    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

//    @NonNull
//    @Override
//    public String toString() {
//        return "Aluno{" +
//                "nome='" + nome + '\'' +
//                ", telefone='" + telefone + '\'' +
//                ", email='" + email + '\'' +
//                '}';
//    }

    @NonNull
    @Override
    public String toString() {
        return  nome + " - " + telefone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return id > 0;
    }

    public String getNomeCompleto() {
        return nome + " " + sobrenome;
    }
}
