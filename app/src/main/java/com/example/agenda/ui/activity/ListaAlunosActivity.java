package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.DAO.AlunoDao;
import com.example.agenda.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//AppCompatActivity que da suporte a versão anteriores
public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //Código executado durante a criação da actitivy: OnAppearing
        super.onCreate(savedInstanceState);
        setTitle("Lista de alunos");

        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
            }
        });

        //Context, quem está executando a ação
        //Terceiro parametro é a duração
        //Toast.makeText(this, "Gabriel Ribeiro", Toast.LENGTH_LONG).show();

        //TextView aluno = new TextView(this);
        //aluno.setText("Gabriel Ribeiro");
        //setContentView(aluno);

        //A classe R faz um mapeando de todos os arquivos do projeto
        //Lembre-se que para pegar referência dos arquivos estáticos o Android oferece a classe R.
        setContentView(R.layout.activity_lista_alunos);

        AlunoDao dao = new AlunoDao();

//        List<String> alunos = new ArrayList<>(
//                Arrays.asList("Gabriel", "Cecilia", "Eduardo")
//        );

        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);

        //android.R acessando os layouts do android framework
        listaDeAlunos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.todos()));
    }
}
