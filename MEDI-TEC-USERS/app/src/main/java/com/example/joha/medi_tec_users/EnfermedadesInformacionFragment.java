package com.example.joha.medi_tec_users;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnfermedadesInformacionFragment extends Fragment {

    TextView nombreLAbel, descripcionLAbel;
        private View rootView;
    public EnfermedadesInformacionFragment() {
        // Required empty public constructor
    }


    public void CargarInformacion(){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Conexion.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servidor servidor = retrofit.create(Servidor.class);
        Call<Enfermedades> call = servidor.obtenerInfoEnfermedad(Global.idActual);
        call.enqueue(new Callback<Enfermedades>() {
            @Override
            public void onResponse(Call<Enfermedades> call, Response<Enfermedades> response) {
                Enfermedades enfermedad = response.body();
                nombreLAbel.setText(enfermedad.getNombre());
                descripcionLAbel.setText(enfermedad.getDescripcion());

            }
            @Override
            public void onFailure(Call<Enfermedades> call, Throwable t) {
                Toast.makeText(getContext(),"Error en conexión"+String.valueOf(t),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_enfermedades_informacion, container, false);
        nombreLAbel= (TextView)rootView.findViewById(R.id.enfermedades_informacion_bd_nomb);
        descripcionLAbel= (TextView)rootView.findViewById(R.id.enfermedades_informacion_bd_desc);
        CargarInformacion();
        return rootView;

        //aceptarButton = (Button) rootView.findViewById(R.id.enfermedades_agregar_button_Aceptar);
    }
}
