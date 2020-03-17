package com.example.desafio_mobile_horizon.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "DB_PACIENTES";
    public static String TABELA_PACIENTES = "pacientes";

    public DbHelper(@Nullable Context context) {

        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Sql para criar a tabela Pacientes
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_PACIENTES
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nome VARCHAR, idade INT(3), tempCorporal REAL, " +
                " diasTosse INT(3), diasDorCabeca INT(3), semanaItalia INT(3), semanaChina INT(3), " +
                "semanaIndonesia INT(3) ,semanaPortugal INT(3), semanaEUA INT(3), situacao VARCHAR); ";

        try {
            db.execSQL(sql);
            Log.i("INFO DB", "Sucesso ao criar a tabela");
        }
        catch(Exception e){
            Log.i("INFO DB", "Erro ao criar a tabela" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Caso banco de dados for atualizado será este método que será chamado
        //Não será utilizado
    }
}
