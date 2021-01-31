package com.example.agenda.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.DAO.AlunoDao;
import com.example.agenda.R;
import com.example.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmaiL;
    private final AlunoDao dao = new AlunoDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Só temos acesso aos campos dpeois de iniciar a activity, como por exemplo o findViewByID
        setContentView(R.layout.activity_formulario_aluno);

        //Final é porque estamos usando variaveis locais
        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();
        configuraBotaoSalvar();
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.action_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno alunoCriado = criaAluno();
                salva(alunoCriado);
            }
        });
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmaiL = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void salva(Aluno aluno) {
        dao.salva(aluno);

        //tartActivity enviando o contexto da FormularioAlunoActivity e a referência de classe da ListaAlunosActivity respectivamente via argumento.
        //intent: classe que indica de onde estamos e para onde queremos ir
        //startActivity(new Intent(FormularioAlunoActivity.this, ListaAlunosActivity.class));

        //Destroi a activity, desempilhar
        finish();
    }

    private Aluno criaAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmaiL.getText().toString();

        //Toast.makeText(FormularioAlunoActivity.this
        //        , alunoCriado.getNome() + " - " + alunoCriado.getTelefone()  + " - " + alunoCriado.getEmail()
        //       , Toast.LENGTH_SHORT).show();
        return new Aluno(nome, telefone, email);
    }
}