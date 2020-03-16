package com.example.desafio_mobile_horizon.adapter;

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
        holder.nome.setText(paciente.getNomePaciente());
        holder.idade.setText(String.valueOf(paciente.getIdade()));
        holder.situacao.setText(paciente.getSituacao());
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

            nome = itemView.findViewById(R.id.textNome);
            idade = itemView.findViewById(R.id.textIdade);
            situacao = itemView.findViewById(R.id.textSituacao);
        }
    }
}
