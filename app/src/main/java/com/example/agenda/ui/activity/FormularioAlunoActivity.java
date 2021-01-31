package com.example.agenda.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.DAO.AlunoDao;
import com.example.agenda.R;
import com.example.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        final AlunoDao dao = new AlunoDao();


        //Final é porque estamos usando variaveis locais
        final EditText campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        final EditText campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        final EditText campoEmaiL = findViewById(R.id.activity_formulario_aluno_email);



        Button botaoSalvar = findViewById(R.id.action_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = campoNome.getText().toString();
                String telefone = campoTelefone.getText().toString();
                String email = campoEmaiL.getText().toString();

                Aluno alunoCriado = new Aluno(nome, telefone, email);
                //Toast.makeText(FormularioAlunoActivity.this
                //        , alunoCriado.getNome() + " - " + alunoCriado.getTelefone()  + " - " + alunoCriado.getEmail()
                //       , Toast.LENGTH_SHORT).show();
                dao.salva(alunoCriado);

                //tartActivity enviando o contexto da FormularioAlunoActivity e a referência de classe da ListaAlunosActivity respectivamente via argumento.
                //intent: classe que indica de onde estamos e para onde queremos ir
                startActivity(new Intent(FormularioAlunoActivity.this, ListaAlunosActivity.class));
            }
        });
    }
}