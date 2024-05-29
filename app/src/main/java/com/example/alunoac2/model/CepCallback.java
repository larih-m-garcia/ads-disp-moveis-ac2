package com.example.alunoac2.model;

public interface CepCallback {
    void onSuccess(Cep cep);
    void onError(Throwable t);
}
