package com.example.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.agenda.DAO.AlunoDao;
import com.example.agenda.database.AgendaDatabase;
import com.example.agenda.database.dao.RoomAlunoDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {
    private final ListaAlunosAdapter adapter;
    private final RoomAlunoDAO dao;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(this.context);

         dao = AgendaDatabase.getInstance(this.context)
                 .getRoomAlunoDAO();
        //this.dao = new AlunoDao();
    }

    public void confirmaRemocao(@NonNull final MenuItem item) {

        //Padrão builder, configura no momento da instancia
        //Tb existe o DatePickerDialog e TimePickerDialog
        new AlertDialog.Builder(context)
                .setTitle("Removendo Aluno")
                .setMessage("Tem certeza que quer remover o aluno")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Fazendo um cast para ter acesso as informaçõe contidas no adapterview com se relaciona com o contextMenu
                        //Considerando que as informações do menu está relacionado ao AdapterView, converta o MenuInfo via cast para AdapterView.AdapterContextMenuInfo.
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

                        //Pegando a posição do item clicado, no caso o aluno
                        //SelecItem ListView == adapter.GetItem
                        Aluno alunoEscolhido  = adapter.getItem(menuInfo.position);
                        remove(alunoEscolhido);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaAlunos() {
        adapter.atualiza(dao.todos());
    }

    private void remove(Aluno aluno){
        dao.remove(aluno);
        adapter.remove(aluno);
    }


    public void configuraAdapter(ListView listaDeAlunos) {
        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        //listaDeAlunos.setAdapter(adapter);

        //adapter = new ArrayAdapter<>(this, R.layout.item_aluno);
        listaDeAlunos.setAdapter(adapter);
    }
}
