package com.example.joha.medi_tec_users;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnfermedadesListaFragment extends Fragment {
    private View rootView;
    MenuInflater inflayer;
    int posicionItemPopuMenuPresionado;
    ArrayList<String> listaEnfermedades= new ArrayList<>();
    ListView enfermedades_lista_listView_enfermedades;
    ArrayAdapter<String> adapter;
    List<Enfermedades>listaReporteBase;

    public EnfermedadesListaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume(){
        listaEnfermedades.clear();
        obtenerListaEnfermedad();
        super.onResume();
    }


    public void EnfermedadNormal(){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Conexion.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servidor servidor = retrofit.create(Servidor.class);
        Call<List<Enfermedades>> call = servidor.obtenerEnfermedades();
        call.enqueue(new Callback<List<Enfermedades>>() {
            @Override
            public void onResponse(Call<List<Enfermedades>> call, Response<List<Enfermedades>> response) {
                listaReporteBase = response.body();
                for (int i = 0; i < listaReporteBase.size(); i++)
                {
                    Enfermedades nuevo4= new Enfermedades(listaReporteBase.get(i).getIdEnfermedad(),
                            listaReporteBase.get(i).getNombre(),listaReporteBase.get(i).getDescripcion());
                    listaEnfermedades.add(nuevo4.getNombre());
                }
                adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                        android.R.layout.simple_list_item_1, listaEnfermedades);
                enfermedades_lista_listView_enfermedades.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Enfermedades>> call, Throwable t) {
                Toast.makeText(getContext(),"Error en conexión"+String.valueOf(t),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void EnEnfermadadesSintoma(){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Conexion.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servidor servidor = retrofit.create(Servidor.class);
        Call<List<Enfermedades>> call = servidor.obtenerEnfermedadesDeUnSintoma(Global.idActual);
        call.enqueue(new Callback<List<Enfermedades>>() {
            @Override
            public void onResponse(Call<List<Enfermedades>> call, Response<List<Enfermedades>> response) {
                listaReporteBase = response.body();
                for (int i = 0; i < listaReporteBase.size(); i++)
                {
                    Enfermedades nuevo4= new Enfermedades(listaReporteBase.get(i).getIdEnfermedad(),
                            listaReporteBase.get(i).getNombre(),listaReporteBase.get(i).getDescripcion());
                    listaEnfermedades.add(nuevo4.getNombre());
                }
                adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                        android.R.layout.simple_list_item_1, listaEnfermedades);
                enfermedades_lista_listView_enfermedades.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Enfermedades>> call, Throwable t) {
                Toast.makeText(getContext(),"Error en conexión"+String.valueOf(t),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void EfermedadesMedicamento(){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Conexion.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servidor servidor = retrofit.create(Servidor.class);
        Call<List<Enfermedades>> call = servidor.obtenerEnfermedadesDeUnMedicamento(Global.idActual);
        call.enqueue(new Callback<List<Enfermedades>>() {
            @Override
            public void onResponse(Call<List<Enfermedades>> call, Response<List<Enfermedades>> response) {
                listaReporteBase = response.body();
                for (int i = 0; i < listaReporteBase.size(); i++)
                {
                    Enfermedades nuevo4= new Enfermedades(listaReporteBase.get(i).getIdEnfermedad(),
                            listaReporteBase.get(i).getNombre(),listaReporteBase.get(i).getDescripcion());
                    listaEnfermedades.add(nuevo4.getNombre());
                }
                adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                        android.R.layout.simple_list_item_1, listaEnfermedades);
                enfermedades_lista_listView_enfermedades.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Enfermedades>> call, Throwable t) {
                Toast.makeText(getContext(),"Error en conexión"+String.valueOf(t),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void obtenerListaEnfermedad(){

        if(Global.accionActual.equals("enfermedad")){
            EnfermedadNormal();
        }
        else if(Global.accionActual.equals("enfermedadSintoma")){
            EnEnfermadadesSintoma();
        }
        else{
            EfermedadesMedicamento();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_enfermedades_lista, container, false);
        enfermedades_lista_listView_enfermedades = (ListView) rootView.findViewById(R.id.enfermedades_lista_listView_enfermedades);
        enfermedades_lista_listView_enfermedades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Global.idActual = listaReporteBase.get(position).getIdEnfermedad();
                mostrarPopuMenu(view);
            }
        });
        return rootView;
    }

    public void mostrarPopuMenu(final View view){
        PopupMenu popupMenu = new PopupMenu(getActivity(), view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                posicionItemPopuMenuPresionado = menuItem.getItemId();
                if(posicionItemPopuMenuPresionado == R.id.verSintomas){
                    Global.accionActual= "enfermedadSintoma";
                    SintomasFragment sintomasEnfermedad = new SintomasFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.ContentForFragments, sintomasEnfermedad).commit();
                }
                else if (posicionItemPopuMenuPresionado == R.id.verMedicamentos){
                    Global.accionActual= "enfermedadMedicamento";
                    MedicamentosFragment medicamentosDeEnfermedad = new MedicamentosFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.ContentForFragments, medicamentosDeEnfermedad).commit();
                }
                else{
                    EnfermedadesInformacionFragment medicamentosDeEnfermedad = new EnfermedadesInformacionFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.ContentForFragments, medicamentosDeEnfermedad).commit();
                }
                return true;
            }
        });
        inflayer = getActivity().getMenuInflater();
        inflayer.inflate(R.menu.popu_menu_enfermedad,popupMenu.getMenu());
        popupMenu.show();
    }
}
