package com.example.agenda.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.agenda.database.dao.RoomAlunoDAO;
import com.example.agenda.model.Aluno;

//Informando que essa classe repretensa o database
//é necesspario fazer a extensão
@Database(entities = {Aluno.class}, version = 2, exportSchema = true)
public abstract class AgendaDatabase extends RoomDatabase {

    private static final String NOME_BANCO_DE_DADOS = "agenda.db";

    //a forma como room implementa o dao, devolvendo uma instancia do Room
    public abstract RoomAlunoDAO getRoomAlunoDAO();
    public static AgendaDatabase instance;

    public static AgendaDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DE_DADOS)
                    //permitindo que o room faça chamadas na trehd principal
                    .allowMainThreadQueries()
                    //Destroi e recria o banco
                    //.fallbackToDestructiveMigration()
                    //Migração da versão 1 para 2
                    .addMigrations(new Migration(1, 2) {
                        @Override
                        public void migrate(@NonNull SupportSQLiteDatabase database) {
                            database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
                        }
                    }, new Migration(2, 3) {
                        @Override
                        public void migrate(@NonNull SupportSQLiteDatabase database) {
                            //Criar nova tabela com as informações desejadas
                            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` " +
                                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                    "`nome` TEXT, " +
                                    "`sobrenome` TEXT, " +
                                    "`telefone` TEXT, " +
                                    "`email` TEXT)");
                            //Garantir a integridade, mantendo os dados da tabela antiga e colocando na nova
                            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefone, email)" +
                                    "SELECT id, nome, telefone, email FROM Aluno");


                            //Remover a tabela antiga
                            database.execSQL("DROP TABLE Aluno");

                            //renomear a tabela nova com o nome da tabela antiga
                            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
                        }
                    })
                    .build();
        }
        return instance;
    }
}
