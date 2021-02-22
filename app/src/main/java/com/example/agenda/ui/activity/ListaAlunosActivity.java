package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.ListaAlunosView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

//AppCompatActivity que da suporte a versão anteriores
public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";
//    private final AlunoDao dao = new AlunoDao();
//    private ListaAlunosAdapter adapter;
    //passando os metodos para outra classe, não é possivel pegar o contexto antes do onCreate
    //private final ListaAlunosView listaAlunosView = new ListaAlunosView(this);


    private ListaAlunosView listaAlunosView;


    //Utilizar a referência do Adapter para manipular os dados do AdapterView.
    //private ArrayAdapter<Aluno> adapter;


    //ctor
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        //SEMPRE QUE ROTACIONA A ACTIVITY É DESTRUIDA E CRIADA NOVAMENTE.

        //Código executado durante a criação da actitivy: OnAppearing
        super.onCreate(savedInstanceState);
        //A classe R faz um mapeando de todos os arquivos do projeto
        //Lembre-se que para pegar referência dos arquivos estáticos o Android oferece a classe R.
        setContentView(R.layout.activity_lista_alunos);


        setTitle(TITULO_APPBAR);

        //Passar parametros de contexto pelo oncreate
        listaAlunosView = new ListaAlunosView(this);


        configuraFabNovoAluno();
        configuraLista();



        //Context, quem está executando a ação
        //Terceiro parametro é a duração
        //Toast.makeText(this, "Gabriel Ribeiro", Toast.LENGTH_LONG).show();

        //TextView aluno = new TextView(this);
        //aluno.setText("Gabriel Ribeiro");
        //setContentView(aluno);

    }


    //Adicionar menus de contexto
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //Adicionando o menu de contexto via arquivo estatico
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
        //menu.add("Remover");
    }

    //Quando tipo de menu de contexto chamado é disparado esse eveto
    @Override
    public boolean onContextItemSelected(@NonNull final MenuItem item) {

        //Pegando pelo id o titulo
        int itemId = item.getItemId();

        //Vendo qual menu de contexto foi clicado
        @SuppressWarnings("unused") CharSequence tituloMenu = item.getTitle();
        //if(tituloMenu.equals("Remover")){
        if(itemId == R.id.activity_lista_alunos_menu_remover){
            listaAlunosView.confirmaRemocao(item);
        }else{
            Toast.makeText(ListaAlunosActivity.this, "Olá", Toast.LENGTH_LONG).show();
        }


        return super.onContextItemSelected(item);
    }



    private void configuraFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioModoInsereAluno();
            }
        });
    }

    private void abreFormularioModoInsereAluno() {
        startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
    }

    //OnAppearing
    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosView.atualizaAlunos();
    }


    private void configuraLista() {
        //        List<String> alunos = new ArrayList<>(
//                Arrays.asList("Gabriel", "Cecilia", "Eduardo")
//        );

        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);

        //android.R acessando os layouts do android framework
        //setAdapter é item source

        //Implementação de uma variavel local dentro de uma implementaçção de uma classe anonima ela precisa ser final
        //final List<Aluno> alunos = dao.todos();
        listaAlunosView.configuraAdapter(listaDeAlunos);
        configuraListenerClickPorItem(listaDeAlunos);
        //configuraListnerDeCliqueLongoPorItem(listaDeAlunos);

        //registrando um menu de contexto para cada item da lista, quando recebe uma ViewGruoup ele já consegue  se resolver e adicionar um menu de contexto para cada item filho da lista
        registerForContextMenu(listaDeAlunos);
    }

//    private void configuraListnerDeCliqueLongoPorItem(ListView listaDeAlunos) {
//        listaDeAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Log.i("Clique Longo", "Posição: " + position);
//                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);
//                //dao.remove(alunoEscolhido);
//                remove(alunoEscolhido);
//
//                //Consome o evento, nao passando  ele para frente
//                return false;
//            }
//
//            private void remove(Aluno aluno) {
//                //CollectionView, apaga e notifica a tela
//                adapter.remove(aluno);
//            }
//        });
//    }

    private void configuraListenerClickPorItem(ListView listaDeAlunos) {
        //metodo exclusivo de adapter view, onClick
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //item selected, como é um ojb é necessário fazer o cast
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);
                //Não foi detalhado em vídeo, mas tanto a chave como o valor do log precisam ser Strings, portanto, valores primitivos precisam ser concatenados ou convertidos.
                Log.i("aluno", "onItemClick: " + alunoEscolhido);
                abreFormularioModoEditaAluno(alunoEscolhido);
            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        //Abrir uma nova tela, pushAsync
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);

        //Enviar dados, objetos ou primitivos, é necesspario que a classe implemente Serializable
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);

        startActivity(vaiParaFormularioActivity);
    }
}
