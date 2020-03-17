package com.example.desafio_mobile_horizon.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.desafio_mobile_horizon.R;
import com.example.desafio_mobile_horizon.helper.PacienteDAO;
import com.example.desafio_mobile_horizon.model.Paciente;
import com.google.android.material.textfield.TextInputEditText;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class AdicionarPacienteActivity extends AppCompatActivity {

    private TextInputEditText editNome;
    private EditText editIdade;
    private EditText editTempCorporal;
    private EditText editDiasTosse;
    private EditText editDiasDorCabeca;
    private EditText editSemanaItalia;
    private EditText editSemanaChina;
    private EditText editSemanaIndonesia;
    private EditText editSemanaPortugal;
    private EditText editSemanaEUA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_paciente);

        setTitle("Adicionar novo paciente");

        editNome = findViewById(R.id.textInputNome);
        editIdade = findViewById(R.id.editTextIdade);
        editTempCorporal = findViewById(R.id.editTextTempCorporal);
        editDiasTosse = findViewById(R.id.editTextDiasTosse);
        editDiasDorCabeca = findViewById(R.id.editTextDiasDorCabeca);
        editSemanaItalia = findViewById(R.id.editTextSemanaItalia);
        editSemanaChina = findViewById(R.id.editTextSemanaChina);
        editSemanaIndonesia = findViewById(R.id.editTextSemanaIndonesia);
        editSemanaPortugal = findViewById(R.id.editTextSemanaPortugal);
        editSemanaEUA = findViewById(R.id.editTextSemanaEUA);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_paciente, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.pacienteSalvar:
                //Executa ação para salvar o paciente
                PacienteDAO pacienteDAO = new PacienteDAO(getApplicationContext());

                String nome = editNome.getText().toString();
                int idade = parseInt(editIdade.getText().toString());
                double tempCorporal = parseDouble(editTempCorporal.getText().toString());
                int diasTosse = parseInt(editDiasTosse.getText().toString());
                int diasDorCabeca = parseInt(editDiasDorCabeca.getText().toString());
                int semanaItalia = parseInt(editSemanaItalia.getText().toString());
                int semanaChina = parseInt(editSemanaChina.getText().toString());
                int semanaIndonesia = parseInt(editSemanaIndonesia.getText().toString());
                int semanaPortugal = parseInt(editSemanaPortugal.getText().toString());
                int semanaEUA = parseInt(editSemanaEUA.getText().toString());
                String situacao;

                //Verificando se foi digitado um nome ou uma temperatura corporal inválida
                if (nome.equals(null) || tempCorporal < 30.0) {
                    Toast.makeText(getApplicationContext(), "Dados inválidos", Toast.LENGTH_SHORT).show();
                    return false;
                }

                //Tratando a entrada de dados para definir a situação do paciente
                if((semanaItalia <= 6 && semanaItalia != 0) || (semanaChina <= 6 && semanaChina !=0) ||
                        (semanaIndonesia <= 6 && semanaIndonesia!=0) || (semanaPortugal <= 6 && semanaPortugal!=0) ||
                        (semanaEUA <= 6 && semanaEUA!=0)){
                    if(idade > 60 || idade < 10){
                        if(diasTosse >= 5 && diasDorCabeca >= 5 && tempCorporal > 37.0){
                            situacao = "Internado";
                        }
                        else if(diasTosse >= 5 || diasDorCabeca >= 3 || tempCorporal > 37.0){
                            situacao = "Quarentena";
                        }
                        else{
                            situacao = "Liberado";
                        }
                    }
                    else{
                        //Idade igual ou entre 10 e 60 anos
                        if(diasTosse >= 5 && diasDorCabeca > 5 && tempCorporal > 37.0){
                            situacao = "Internado";
                        }
                        else if(tempCorporal > 37.0 && (diasDorCabeca >= 5 && diasTosse >= 5)){
                            situacao = "Quarentena";
                        }
                        else{
                            situacao = "Liberado";
                        }
                    }
                }
                else{
                    situacao = "Liberado";
                }

                Paciente paciente = new Paciente();
                paciente.setNomePaciente(nome);
                paciente.setIdade(idade);
                paciente.setTemperaturaCorporal(tempCorporal);
                paciente.setDiasTosse(diasTosse);
                paciente.setDiasDorCabeca(diasDorCabeca);
                paciente.setSemanaItalia(semanaItalia);
                paciente.setSemanaChina(semanaChina);
                paciente.setSemanaIndonesia(semanaIndonesia);
                paciente.setSemanaPortugal(semanaPortugal);
                paciente.setSemanaEUA(semanaEUA);
                paciente.setSituacao(situacao);

                pacienteDAO.salvar(paciente);
                finish();

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
