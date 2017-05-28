package com.example.joha.medi_tec_users;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Joha on 17/5/2017.
 */
public class Conexion {
    private String baseurl;
    private final Retrofit retrofit;
    private Servidor servidor;

    public static String baseURL = "http://172.24.42.140:8090";


    public Conexion() {
        this.baseurl = "http://172.24.42.140:8090";
        this.retrofit = new Retrofit.Builder().baseUrl(baseurl).addConverterFactory(GsonConverterFactory.create()).build();
        this.servidor = retrofit.create(Servidor.class);
    }

    public Servidor getServidor() {
        return this.servidor;
    }
}
