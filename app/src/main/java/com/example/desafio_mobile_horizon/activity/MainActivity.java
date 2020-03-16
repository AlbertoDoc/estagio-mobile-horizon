package com.example.desafio_mobile_horizon.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.example.desafio_mobile_horizon.R;
import com.example.desafio_mobile_horizon.adapter.PacienteAdapter;
import com.example.desafio_mobile_horizon.helper.DbHelper;
import com.example.desafio_mobile_horizon.helper.RecyclerItemClickListener;
import com.example.desafio_mobile_horizon.model.Paciente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btn;
    private RecyclerView recyclerView;
    private PacienteAdapter pacienteAdapter;
    private List<Paciente> listaPacientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Listagem de pacientes");

        recyclerView = findViewById(R.id.recyclerView);

        //Adicionar evento de clique
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Log.i("clique", "onItemClick");
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Log.i("clique", "onLongItemClick");
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        btn = findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), AdicionarPacienteActivity.class);
                startActivity(intent);
            }
        });
    }

    public void carregarPacientes(){
        //Listar Pacientes
        Paciente paciente1 = new Paciente();
        paciente1.setNomePaciente("Joao");
        paciente1.setIdade(25);
        paciente1.setSituacao("Observação");
        listaPacientes.add(paciente1);

        Paciente paciente2 = new Paciente();
        paciente2.setNomePaciente("Maria");
        paciente2.setIdade(32);
        paciente2.setSituacao("Quarentena");
        listaPacientes.add(paciente2);
        //exibe lista de pacientes no RecyclerView

        //Configurar um adapter
        pacienteAdapter = new PacienteAdapter(listaPacientes);

        //Configurar Recycler View
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration((new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL)));
        recyclerView.setAdapter(pacienteAdapter);
    }

    @Override
    protected void onStart() {
        carregarPacientes();
        super.onStart();
    }
}
