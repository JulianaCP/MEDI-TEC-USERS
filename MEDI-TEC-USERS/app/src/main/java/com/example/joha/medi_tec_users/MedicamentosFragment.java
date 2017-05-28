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
public class MedicamentosFragment extends Fragment {

    private View rootView;
    MenuInflater inflayer;
    int posicionItemPopuMenuPresionado;
    ArrayList<String> listaEnfermedadMedicamentos = new ArrayList<>();
    //List<Medicamentos>listaReporteBase;
    ListaMedicamento listaMedicamento;
    ListView enfermedadesMedicamentos_lista_listView_medicamentos;
    ArrayAdapter<String> adapter;

    public MedicamentosFragment() {
        // Required empty public constructor
    }
    @Override
    public void onResume(){
        listaEnfermedadMedicamentos.clear();
        obtenerListaSintoma();
        super.onResume();
    }

    public void MedicamentoNormal(){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Conexion.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servidor servidor = retrofit.create(Servidor.class);

        Call<List<Medicamentos>> call = servidor.obtenerMedicamentos();
        call.enqueue(new Callback<List<Medicamentos>>() {
            @Override
            public void onResponse(Call<List<Medicamentos>> call, Response<List<Medicamentos>> response) {
                listaMedicamento = new ListaMedicamento(response.body());
                for (int i = 0; i < listaMedicamento.getMedicamentos().size(); i++)
                {
                    Medicamentos nuevo4= new Medicamentos(listaMedicamento.getMedicamentos().get(i).getIdMedicamento(),
                            listaMedicamento.getMedicamentos().get(i).getNombre(),listaMedicamento.getMedicamentos().get(i).getDescripcion() );
                    listaEnfermedadMedicamentos.add(nuevo4.getNombre());
                }
                adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                        android.R.layout.simple_list_item_1, listaEnfermedadMedicamentos);
                enfermedadesMedicamentos_lista_listView_medicamentos.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Medicamentos>> call, Throwable t) {
                Toast.makeText(getContext(),"Error en conexión"+String.valueOf(t),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void MedicamentosDeEnfermedad(){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Conexion.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servidor servidor = retrofit.create(Servidor.class);

        Call<List<Medicamentos>> call = servidor.obtenerMeicamentosDeEnfermedad(Global.idActual);
        call.enqueue(new Callback<List<Medicamentos>>() {
            @Override
            public void onResponse(Call<List<Medicamentos>> call, Response<List<Medicamentos>> response) {
                listaMedicamento = new ListaMedicamento(response.body());
                for (int i = 0; i < listaMedicamento.getMedicamentos().size(); i++)
                {
                    Medicamentos nuevo4= new Medicamentos(listaMedicamento.getMedicamentos().get(i).getIdMedicamento(),
                            listaMedicamento.getMedicamentos().get(i).getNombre(),listaMedicamento.getMedicamentos().get(i).getDescripcion() );
                    listaEnfermedadMedicamentos.add(nuevo4.getNombre());
                }
                adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                        android.R.layout.simple_list_item_1, listaEnfermedadMedicamentos);
                enfermedadesMedicamentos_lista_listView_medicamentos.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Medicamentos>> call, Throwable t) {
                Toast.makeText(getContext(),"Error en conexión"+String.valueOf(t),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void mostrarPopuMenu(final View view){
        PopupMenu popupMenu = new PopupMenu(getActivity(), view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                posicionItemPopuMenuPresionado = menuItem.getItemId();
                if(posicionItemPopuMenuPresionado == R.id.verEnfermedades){
                    Global.accionActual= "enfermedadMedicamento";
                    EnfermedadesListaFragment nuevoFragment = new EnfermedadesListaFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.ContentForFragments, nuevoFragment).commit();
                }
                else if (posicionItemPopuMenuPresionado == R.id.verMasInformacion){
                    MedicamentosInfromacionFragment medicamentosDeEnfermedad = new MedicamentosInfromacionFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.ContentForFragments, medicamentosDeEnfermedad).commit();
                }
                return true;
            }
        });
        inflayer = getActivity().getMenuInflater();
        inflayer.inflate(R.menu.popu_menu_medicamento,popupMenu.getMenu());
        popupMenu.show();
    }

    public void obtenerListaSintoma(){
        if(Global.accionActual.equals("medicamento")){
            MedicamentoNormal();
        }
        else{
            MedicamentosDeEnfermedad();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_enfermedades_medicamentos, container, false);
        enfermedadesMedicamentos_lista_listView_medicamentos = (ListView) rootView.findViewById(R.id.enfermedades_medicamentos_listView);
        enfermedadesMedicamentos_lista_listView_medicamentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Global.idActual = listaMedicamento.getMedicamentos().get(position).getIdMedicamento();
                mostrarPopuMenu(view);
            }
        });

        return rootView;
    }

}
