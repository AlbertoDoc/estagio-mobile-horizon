package com.example.desafio_mobile_horizon.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafio_mobile_horizon.R;
import com.example.desafio_mobile_horizon.model.Paciente;

import java.util.List;

public class PacienteAdapter extends RecyclerView.Adapter<PacienteAdapter.MyViewHolder> {

    private List<Paciente> listaPacientes;

    public PacienteAdapter(List<Paciente> lista) {
        this.listaPacientes = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemPaciente = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.lista_pacientes_adapter, parent, false);

        return new MyViewHolder(itemPaciente);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Paciente paciente = listaPacientes.get(position);

        //Usando o holder para colocar os dados para a listagem de paciente
        holder.nome.setText(paciente.getNomePaciente());
        holder.idade.setText(String.valueOf(paciente.getIdade()));
        holder.situacao.setText(paciente.getSituacao());

        //Definindo cores para cada situação do paciente;
        if(paciente.getSituacao().equals("Internado")){
            holder.situacao.setTextColor(Color.RED);
        }
        else if(paciente.getSituacao().equals("Liberado")){
            holder.situacao.setTextColor(Color.parseColor("#42c22f"));
        }
        else if(paciente.getSituacao().equals("Quarentena")){
            holder.situacao.setTextColor(Color.parseColor("#e3d539"));
        }
    }

    @Override
    public int getItemCount() {
        return this.listaPacientes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome;
        TextView idade;
        TextView situacao;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //Recuperando os campos
            nome = itemView.findViewById(R.id.textNome);
            idade = itemView.findViewById(R.id.textIdade);
            situacao = itemView.findViewById(R.id.textSituacao);
        }
    }
}
