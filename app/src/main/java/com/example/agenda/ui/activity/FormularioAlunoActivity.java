package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.DAO.AlunoDao;
import com.example.agenda.R;
import com.example.agenda.model.Aluno;

import static com.example.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmaiL;

    //Final é porque estamos usando variaveis locais, em innersclass, jã que elas não podem modificar variaveis de classe
    private final AlunoDao dao = new AlunoDao();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Só temos acesso aos campos dpeois de iniciar a activity, como por exemplo o findViewByID
        setContentView(R.layout.activity_formulario_aluno);


        inicializacaoDosCampos();
        configuraBotaoSalvar();
        carregaAluno();
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_ALUNO)){
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            PreencheCampos();
        }else{
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void PreencheCampos() {
        campoNome.setText(aluno.getNome());
        campoEmaiL.setText(aluno.getEmail());
        campoTelefone.setText(aluno.getTelefone());
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.action_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalizaFormulario();
            }
        });
    }

    private void finalizaFormulario() {
        //Aluno alunoCriado = criaAluno();
        //salva(alunoCriado);
        preencheAluno();
        if(aluno.temIdValido()){
            dao.edita(aluno);
        }else{
            dao.salva(aluno);
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmaiL = findViewById(R.id.activity_formulario_aluno_email);
    }

//    private void salva(Aluno aluno) {
//        dao.salva(aluno);
//
//        //tartActivity enviando o contexto da FormularioAlunoActivity e a referência de classe da ListaAlunosActivity respectivamente via argumento.
//        //intent: classe que indica de onde estamos e para onde queremos ir
//        //startActivity(new Intent(FormularioAlunoActivity.this, ListaAlunosActivity.class));
//
//        //Destroi a activity, desempilhar
//        finish();
//    }

    //Garante que não será devolvido uma referencia nula
    //@NonNull
    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmaiL.getText().toString();

        //Toast.makeText(FormularioAlunoActivity.this
        //        , alunoCriado.getNome() + " - " + alunoCriado.getTelefone()  + " - " + alunoCriado.getEmail()
        //       , Toast.LENGTH_SHORT).show();
        //return new Aluno(nome, telefone, email);

        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setTelefone(telefone);
    }
}