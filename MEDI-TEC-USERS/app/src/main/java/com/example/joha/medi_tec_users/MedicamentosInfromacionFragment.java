package com.example.joha.medi_tec_users;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class MedicamentosInfromacionFragment extends Fragment {

    TextView nombreLAbel, descripcionLAbel;
    private View rootView;

    public MedicamentosInfromacionFragment() {
        // Required empty public constructor
    }

    public void CargarInformacion(){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Conexion.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servidor servidor = retrofit.create(Servidor.class);
        Call<Medicamentos> call = servidor.obtenerInfoMedicamento(Global.idActual);
        call.enqueue(new Callback<Medicamentos>() {
            @Override
            public void onResponse(Call<Medicamentos> call, Response<Medicamentos> response) {
                Medicamentos enfermedad = response.body();
                nombreLAbel.setText(enfermedad.getNombre());
                descripcionLAbel.setText(enfermedad.getDescripcion());

            }
            @Override
            public void onFailure(Call<Medicamentos> call, Throwable t) {
                Toast.makeText(getContext(),"Error en conexión"+String.valueOf(t),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_medicamentos_infromacion, container, false);
        nombreLAbel= (TextView)rootView.findViewById(R.id.medicamentos_informacion_bd_nomb);
        descripcionLAbel= (TextView)rootView.findViewById(R.id.mediamentos_informacion_bd_desc);
        CargarInformacion();
        return rootView;
    }

}
