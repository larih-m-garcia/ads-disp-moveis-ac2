package com.example.alunoac2.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = " https://64e6a1e809e64530d1801cac.mockapi.io/api/v1/ ";
    private static final String BASE_URL2 = "https://viacep.com.br/ws/";
    private static Retrofit retrofit = null;
    private static Retrofit retrofit2 = null;
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static AlunoService getAlunoService() {
        return getClient().create(AlunoService.class);
    }

    public static Retrofit getClient2() {
        if (retrofit2 == null) {
            retrofit2 = new Retrofit.Builder()
                    .baseUrl(BASE_URL2)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit2;
    }

    public static CEPService getCEPService() {
        return getClient2().create(CEPService.class);
    }
}
