package com.example.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.agenda.model.Aluno;

import java.util.List;

//comportamentos de CRUD, uma entidade
@Dao
public interface RoomAlunoDAO {

    @Insert
    void salva(Aluno aluno);

    @Delete
    void remove(Aluno aluno);

    @Query("SELECT * FROM aluno")
    List<Aluno> todos();

    @Update
    void edita(Aluno aluno);
}
