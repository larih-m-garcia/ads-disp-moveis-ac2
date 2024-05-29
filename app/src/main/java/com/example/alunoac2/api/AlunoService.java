package com.example.alunoac2.api;

import com.example.alunoac2.model.Aluno;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AlunoService {
    @GET("aluno")
    Call<List<Aluno>> getAlunos();
    @POST("aluno")
    Call<Aluno> postAluno(@Body Aluno aluno);
}
