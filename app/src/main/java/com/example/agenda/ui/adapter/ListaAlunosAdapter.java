package com.example.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    //quantidade de itens que o adapter tem
    @Override
    public int getCount() {
        return alunos.size();
    }

    //o item que queremos pegar com base na posição
    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    //Possição do aluno
    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    //Criação da view com base no layout estatico
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //de onde vamos criar o layout
        View viewCreate = criaView(parent);
        Aluno alunoDevolvido = alunos.get(position);
        vincula(viewCreate, alunoDevolvido);
        return viewCreate;
    }

    private void vincula(View view, Aluno aluno) {
        TextView nome = view.findViewById(R.id.item_aluno_nome);
        nome.setText(aluno.getNomeCompleto());
        TextView telefone = view.findViewById(R.id.item_aluno_telefone);
        telefone.setText(aluno.getTelefone());
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater
                .from(context)
                //qual arquivo vamos colocar no adapter
                //dizer em qual viewGroup percente
                //layout é filho do parente
                //false porque nao podemos adicionar uma view Diretamente ao pai, só criamos a view e a View faz o trabalho
                .inflate(R.layout.item_aluno, parent, false);
    }

    public void atualiza(List<Aluno> alunos){
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);

        //notificar que o data foi notificado
        notifyDataSetChanged();
    }
}
