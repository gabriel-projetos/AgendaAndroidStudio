package com.example.agenda;

import android.app.Application;

import androidx.room.Room;

import com.example.agenda.DAO.AlunoDao;
import com.example.agenda.database.AgendaDatabase;
import com.example.agenda.database.dao.RoomAlunoDAO;
import com.example.agenda.model.Aluno;

//Precisamos registarr no manifest a extesnsao da application
//Código da application é executado uma unica vez
public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeTeste();
    }

    private void criaAlunosDeTeste() {
        //AlunoDao dao = new AlunoDao();
        //DataBase -> Dao -> Comportamentos

        AgendaDatabase database = Room.databaseBuilder(this, AgendaDatabase.class, "agenda.db")
                .allowMainThreadQueries()
                .build();
        RoomAlunoDAO dao = database.getRoomAlunoDAO();
        //dao.salva(new Aluno("Gabriel", "11963485531", "gabriel.rossi96@hotmail.com"));
        //dao.salva(new Aluno("Eduardo", "11983035280", "edu.rossi96@hotmail.com"));
    }
}
