package com.example.desafio_mobile_horizon.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.desafio_mobile_horizon.model.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteDAO implements IPacienteDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public PacienteDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Paciente paciente) {

        ContentValues cv = new ContentValues();
        cv.put("nome", paciente.getNomePaciente());
        cv.put("idade", paciente.getIdade());
        cv.put("tempCorporal", paciente.getTemperaturaCorporal());
        cv.put("diasTosse", paciente.getDiasTosse());
        cv.put("diasDorCabeca", paciente.getDiasDorCabeca());
        cv.put("semanaItalia", paciente.getSemanaItalia());
        cv.put("semanaChina", paciente.getSemanaChina());
        cv.put("semanaIndonesia", paciente.getSemanaIndonesia());
        cv.put("semanaPortugal", paciente.getSemanaPortugal());
        cv.put("semanaEUA", paciente.getSemanaEUA());
        cv.put("situacao", paciente.getSituacao());

        try {
            escreve.insert(DbHelper.TABELA_PACIENTES, null, cv);
            Log.i("INFO", "Paciente salvo com sucesso");
        }
        catch( Exception e){
            Log.e("INFO", "Erro ao salvar paciente" + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Paciente paciente) {
        return false;
    }

    @Override
    public List<Paciente> listar() {

        List<Paciente> pacientes = new ArrayList<>();

        //Essa string sql retornara os pacientes na seguinte ordem
        // Quarentena -> Liberado -> Internado
        String sql = "SELECT * FROM " + DbHelper.TABELA_PACIENTES + " WHERE 1=1 ORDER BY situacao ASC ;";
        Cursor c = le.rawQuery(sql, null);

        while(c.moveToNext()) {
            Paciente paciente = new Paciente();
            Long id = c.getLong(c.getColumnIndex("id"));
            String nome = c.getString(c.getColumnIndex("nome"));
            int idade = c.getInt(c.getColumnIndex("idade"));
            double tempCorporal = c.getDouble((c.getColumnIndex("tempCorporal")));
            int diasTosse = c.getInt(c.getColumnIndex("diasTosse"));
            int diasDorCabeca = c.getInt(c.getColumnIndex("diasDorCabeca"));
            int semanaItalia = c.getInt(c.getColumnIndex("semanaItalia"));
            int semanaChina = c.getInt(c.getColumnIndex("semanaChina"));
            int semanaIndonesia = c.getInt(c.getColumnIndex("semanaIndonesia"));
            int semanaPortugal = c.getInt(c.getColumnIndex("semanaPortugal"));
            int semanaEUA = c.getInt(c.getColumnIndex("semanaEUA"));
            String situacao = c.getString(c.getColumnIndex("situacao"));

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

            pacientes.add(paciente);
        }

        return pacientes;
    }
}
