package com.example.joha.medi_tec_users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Joha on 17/5/2017.
 */
public interface Servidor {

    //              ENFERMEDADES
    @GET("enfermedades/obtener")
    Call<List<Enfermedades>> obtenerEnfermedades();

    @GET("sintomaEnfermedad/obtenerEnfermedadesSintoma/{idSintoma}")
    Call<List<Enfermedades>> obtenerEnfermedadesDeUnSintoma(@Path("idSintoma") int id);

    @GET("medicamentoEnfermedad/obtenerEnfermedadesMedicamento/{idMedicamento}")
    Call<List<Enfermedades>> obtenerEnfermedadesDeUnMedicamento(@Path("idMedicamento") int id);

    @GET("enfermedades/obtenerEnfermedad/{id}")
    Call<Enfermedades> obtenerInfoEnfermedad(@Path("id") int id);


    //              SINTOMAS
    @GET("sintomasEnfermedad/obtener/{idEnfermedad}")
    Call<List<Sintomas>> obtenerSintomasDeEnfermedad(@Path("idEnfermedad") int id);

    @GET("sintomas/obtener")
    Call<List<Sintomas>> obtenerSintomas();

    @GET("sintomas/obtenerSintoma/{id}")
    Call<Sintomas> obtenerInfoSintoma(@Path("id") int id);

    //              MEDICAMENTOS
    @GET("medicamentoEnfermedad/obtener/{idEnfermedad}")
    Call<List<Medicamentos>> obtenerMeicamentosDeEnfermedad(@Path("idEnfermedad") int id);

    @GET("medicamentos/obtener")
    Call<List<Medicamentos>> obtenerMedicamentos();

    @GET("medicamentos/obtenerMedicamento/{id}")
    Call<Medicamentos> obtenerInfoMedicamento(@Path("id") int id);
}
