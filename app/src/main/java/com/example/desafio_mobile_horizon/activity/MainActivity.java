package com.example.desafio_mobile_horizon.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.desafio_mobile_horizon.R;
import com.example.desafio_mobile_horizon.adapter.PacienteAdapter;
import com.example.desafio_mobile_horizon.helper.PacienteDAO;
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
    private Paciente pacienteSelecionado;

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

                                Log.i("INFO", "clicou e segurou");

                                pacienteSelecionado = listaPacientes.get(position);

                                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                                dialog.setTitle("Confirmar exclusão");
                                dialog.setMessage("Deseja excluir o/a paciente " + pacienteSelecionado.getNomePaciente() + "?");

                                //Configurando botão sim
                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                            PacienteDAO pacienteDAO = new PacienteDAO(getApplicationContext());
                                            if(pacienteDAO.deletar(pacienteSelecionado)){
                                                carregarPacientes();
                                                Toast.makeText(getApplicationContext(), "Sucesso ao excluir paciente", Toast.LENGTH_SHORT).show();
                                            }
                                            else{
                                                Toast.makeText(getApplicationContext(), "Erro ao excluir paciente", Toast.LENGTH_SHORT).show();
                                            }
                                    }
                                });

                                //Configurando botão negativo
                                dialog.setNegativeButton("Não", null);

                                dialog.create();
                                dialog.show();
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
        PacienteDAO pacienteDAO = new PacienteDAO(getApplicationContext());
        listaPacientes = pacienteDAO.listar();
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
