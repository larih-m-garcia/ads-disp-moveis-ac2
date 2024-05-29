package com.example.alunoac2.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.alunoac2.R;
import com.example.alunoac2.model.Aluno;

import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoHolder>{

    private final List<Aluno> alunos;
    Context context;
    public AlunoAdapter(List<Aluno> alunos, Context context) {
        this.alunos = alunos;
        this.context = context;
    }
    @Override
    public AlunoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlunoHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_aluno, parent, false));
    }
    @Override
    public void onBindViewHolder(AlunoHolder holder, int position) {
        holder.ra.setText(alunos.get(position).getRa() + "");
        holder.nome.setText(alunos.get(position).getNome());
        holder.cep.setText(alunos.get(position).getCep());
        holder.logradouro.setText(alunos.get(position).getLogradouro());
        holder.complemento.setText(alunos.get(position).getComplemento());
        holder.cidade.setText(alunos.get(position).getCidade());
        holder.bairro.setText(alunos.get(position).getBairro());
        holder.uf.setText(alunos.get(position).getUf());
    }
    @Override
    public int getItemCount() {
        return alunos != null ? alunos.size() : 0;
    }

    public class AlunoHolder extends RecyclerView.ViewHolder {

        public TextView ra;
        public TextView nome;
        public TextView cep;
        public TextView logradouro;
        public TextView complemento;
        public TextView cidade;
        public TextView bairro;
        public TextView uf;
        public AlunoHolder(View itemView) {
            super(itemView);
            ra = (TextView) itemView.findViewById(R.id.textRa);
            nome = (TextView) itemView.findViewById(R.id.textNome);
            cep = (TextView) itemView.findViewById(R.id.textCEP);
            logradouro = (TextView) itemView.findViewById(R.id.textLogradouro);
            complemento = (TextView) itemView.findViewById(R.id.textComplemento);
            cidade = (TextView) itemView.findViewById(R.id.textCidade);
            bairro = (TextView) itemView.findViewById(R.id.textBairro);
            uf = (TextView) itemView.findViewById(R.id.textUF);
        }
    }
}
