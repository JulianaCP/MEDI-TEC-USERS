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
public class SintomasFragment extends Fragment {
    private View rootView;
    MenuInflater inflayer;
    int posicionItemPopuMenuPresionado;
    ArrayList<String> listaEnfermedadSintomas = new ArrayList<>();

    //List<Sintomas>listaReporteBase;
    ListaSintomas listaSintomas;

    ListView enfermedadesSintomas_lista_listView_sintomas;
    ArrayAdapter<String> adapter;


    public SintomasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume(){
        listaEnfermedadSintomas.clear();
        obtenerListaSintoma();
        super.onResume();
    }

    public void mostrarPopuMenu(final View view){
        PopupMenu popupMenu = new PopupMenu(getActivity(), view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                posicionItemPopuMenuPresionado = menuItem.getItemId();
                if(posicionItemPopuMenuPresionado == R.id.verEnfermedades){
                    Global.accionActual= "enfermedadSintoma";
                    EnfermedadesListaFragment nuevoFragment = new EnfermedadesListaFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.ContentForFragments, nuevoFragment).commit();
                }
                else if (posicionItemPopuMenuPresionado == R.id.verMasInformacion){
                    Global.accionActual= "";
                    MedicamentosFragment medicamentosDeEnfermedad = new MedicamentosFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.ContentForFragments, medicamentosDeEnfermedad).commit();
                }
                return true;
            }
        });
        inflayer = getActivity().getMenuInflater();
        inflayer.inflate(R.menu.popu_menu_sintoma,popupMenu.getMenu());
        popupMenu.show();
    }

    public void SintomaNormal(){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Conexion.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servidor servidor = retrofit.create(Servidor.class);

        Call<List<Sintomas>> call = servidor.obtenerSintomas();
        call.enqueue(new Callback<List<Sintomas>>() {
            @Override
            public void onResponse(Call<List<Sintomas>> call, Response<List<Sintomas>> response) {
                List<Sintomas>listaReporteBase = response.body();
                for (int i = 0; i < listaReporteBase.size(); i++)
                {
                    Sintomas nuevo4= new Sintomas(listaReporteBase.get(i).getIdSintoma(),
                            listaReporteBase.get(i).getNombre());
                    listaEnfermedadSintomas.add(nuevo4.getNombre());
                }
                adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                        android.R.layout.simple_list_item_1, listaEnfermedadSintomas);
                enfermedadesSintomas_lista_listView_sintomas.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Sintomas>> call, Throwable t) {
                Toast.makeText(getContext(),"Error en conexión "+String.valueOf(t),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void SintomaDeEnfermedad(){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Conexion.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servidor servidor = retrofit.create(Servidor.class);

        Call<List<Sintomas>> call = servidor.obtenerSintomasDeEnfermedad(Global.idActual);
        call.enqueue(new Callback<List<Sintomas>>() {
            @Override
            public void onResponse(Call<List<Sintomas>> call, Response<List<Sintomas>> response) {
                listaSintomas = new ListaSintomas(response.body());
                for (int i = 0; i < listaSintomas.getSintomas().size(); i++)
                {
                    Sintomas nuevo4= new Sintomas(listaSintomas.getSintomas().get(i).getIdSintoma(),
                            listaSintomas.getSintomas().get(i).getNombre());
                    listaEnfermedadSintomas.add(nuevo4.getNombre());
                }
                adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                        android.R.layout.simple_list_item_1, listaEnfermedadSintomas);
                enfermedadesSintomas_lista_listView_sintomas.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Sintomas>> call, Throwable t) {
                Toast.makeText(getContext(),"Error en conexión "+String.valueOf(t),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void obtenerListaSintoma(){
        if(Global.accionActual.equals("sintoma")){
            SintomaNormal();
        }
        else{
            SintomaDeEnfermedad();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_enfermedades_sintomas, container, false);
        enfermedadesSintomas_lista_listView_sintomas = (ListView) rootView.findViewById(R.id.enfermedades_sintomas_listView);
        enfermedadesSintomas_lista_listView_sintomas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Global.idActual = listaSintomas.getSintomas().get(position).getIdSintoma();
                mostrarPopuMenu(view);
            }
        });
        return rootView;
    }
}
