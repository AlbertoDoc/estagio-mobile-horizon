package com.example.desafio_mobile_horizon.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.desafio_mobile_horizon.R;

public class AdicionarPacienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_paciente);

        setTitle("Adicionar novo paciente");
    }
}
