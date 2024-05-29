package com.example.alunoac2.api;

import com.example.alunoac2.model.Cep;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.PartMap;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {
    @GET("{cep}/json/")
    Call<Cep> getEndereco(@Path("cep") String cep);

}
