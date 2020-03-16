package com.example.desafio_mobile_horizon.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.example.desafio_mobile_horizon.PacienteDatabase;
import com.example.desafio_mobile_horizon.R;
import com.example.desafio_mobile_horizon.adapter.PacienteAdapter;
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

        try {
            //Criando banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase( "app", MODE_PRIVATE, null);

            //Criar tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pacientes (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome VARCHAR, idade INT(3), tempCorporal INT(2), perTosse INT(3)," +
                    "perDorCabeca INT(3), semanaItalia INT(3), semanaChina INT(3)," +
                    "semanaIndonesia INT(3), semanaPortugal INT(3), semanaEUA INT(3)," +
                    "situacao VARCHAR)");


            //Inserindo dados
            //bancoDados.execSQL("INSERT INTO pacientes(nome, idade, tempCorporal, perTosse," +
                    //"perDorCabeca, semanaItalia, semanaChina, semanaIndonesia, semanaPortugal," +
                    //"semanaEUA, situacao) VALUES ('Alberto', 20, 1, 2, 3, 4, 5, 6, 7, 8, 'Quarentena')");

            //bancoDados.execSQL("INSERT INTO pacientes(nome, idade, tempCorporal, perTosse," +
                    //"perDorCabeca, semanaItalia, semanaChina, semanaIndonesia, semanaPortugal," +
                    //"semanaEUA, situacao) VALUES ('Carol', 23, 2, 3, 4, 5, 6, 7, 8, 9, 'Livre')");

            //bancoDados.execSQL("DELETE FROM pacientes WHERE id = 2");

            //Recuperando dados
            String consulta = "SELECT * FROM pacientes";
            Cursor cursor = bancoDados.rawQuery(consulta, null);

            //Indices da tabela
            int indiceId = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();
            while(cursor != null){
                String id = cursor.getString(indiceId);
                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);

                Log.i("RESULTADO - id ", id + "/nome: " + nome + "/ idade: " + idade);
                cursor.moveToNext();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

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
