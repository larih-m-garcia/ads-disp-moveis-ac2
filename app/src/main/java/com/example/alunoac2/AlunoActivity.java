package com.example.alunoac2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alunoac2.api.AlunoService;
import com.example.alunoac2.api.ApiClient;
import com.example.alunoac2.api.CEPService;
import com.example.alunoac2.model.Aluno;
import com.example.alunoac2.model.Cep;
import com.example.alunoac2.model.CepCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlunoActivity extends AppCompatActivity {

    Button buttonSalvar;

    AlunoService apiAlunoService;
    CEPService apiCEPService;
    TextView editTextRa, editTextNome, editTextCEP, editTextLogradouro, editTextComplemento, editTextCidade, editTextBairro, editTextUF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);
        buttonSalvar = (Button) findViewById(R.id.buttonSalvar);
        apiAlunoService = ApiClient.getAlunoService();
        apiCEPService = ApiClient.getCEPService();
        editTextRa = findViewById(R.id.editTextRa);
        editTextNome = findViewById(R.id.editTextNome);
        editTextCEP = findViewById(R.id.editTextCEP);
        editTextLogradouro = findViewById(R.id.editTextLogradouro);
        editTextComplemento = findViewById(R.id.editTextComplemento);
        editTextCidade = findViewById(R.id.editTextCidade);
        editTextBairro = findViewById(R.id.editTextBairro);
        editTextUF = findViewById(R.id.editTextUF);

        editTextCEP.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                buscarEndereco(editTextCEP.getText().toString(), new CepCallback() {
                    @Override
                    public void onSuccess(Cep cep) {
                        editTextLogradouro.setText(cep.getLogradouro());
                        editTextComplemento.setText(cep.getComplemento());
                        editTextCidade.setText(cep.getLocalidade());
                        editTextBairro.setText(cep.getBairro());
                        editTextUF.setText(cep.getUf());
                    }

                    @Override
                    public void onError(Throwable t) {
                        // Handle error
                        Log.e("Inserir", "Erro ao buscar endereço", t);
                    }
                });
            }
        });

        buttonSalvar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Aluno aluno = new Aluno();
                aluno.setRa(Integer.parseInt(editTextRa.getText().toString()));
                aluno.setNome(editTextNome.getText().toString());
                aluno.setCep(editTextCEP.getText().toString());
                aluno.setLogradouro(editTextLogradouro.getText().toString());
                aluno.setComplemento(editTextComplemento.getText().toString());
                aluno.setCidade(editTextCidade.getText().toString());
                aluno.setBairro(editTextBairro.getText().toString());
                aluno.setUf(editTextUF.getText().toString());
                inserirAluno(aluno);
            }
        });
    }

    private void buscarEndereco(String cep, CepCallback callback) {
        Call<Cep> call = apiCEPService.getEndereco(cep);
        call.enqueue(new Callback<Cep>() {
            @Override
            public void onResponse(Call<Cep> call, Response<Cep> response) {
                if (response.isSuccessful()) {
                    // A solicitação foi bem-sucedida
                    Cep createdPost = response.body();
                    Toast.makeText(AlunoActivity.this, "Busca de endereço feita com sucesso!", Toast.LENGTH_SHORT).show();
                    callback.onSuccess(createdPost);
                } else {
                    // A solicitação falhou
                    Log.e("Inserir", "Erro ao buscar: " + response.code());
                    callback.onError(new Exception("Erro ao buscar: " + response.code()));
                }
            }
            @Override
            public void onFailure(Call<Cep> call, Throwable t) {
                // Ocorreu um erro ao fazer a solicitação
                Log.e("Inserir", "Erro ao criar: " + t.getMessage());
                callback.onError(t);
            }
        });
    }

    private void inserirAluno(Aluno aluno) {
        Call<Aluno> call = apiAlunoService.postAluno(aluno);
        call.enqueue(new Callback<Aluno>() {
            @Override
            public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                if (response.isSuccessful()) {
                    // A solicitação foi bem-sucedida
                    Aluno createdPost = response.body();
                    Toast.makeText(AlunoActivity.this, "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // A solicitação falhou
                    Log.e("Inserir", "Erro ao criar: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<Aluno> call, Throwable t) {
                // Ocorreu um erro ao fazer a solicitação
                Log.e("Inserir", "Erro ao criar: " + t.getMessage());
            }
        });
    }
}