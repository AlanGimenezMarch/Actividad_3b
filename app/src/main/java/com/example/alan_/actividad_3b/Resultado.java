package com.example.alan_.actividad_3b;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {

    public static final String PREFS = "My preferences";
    private TextView tvNom, tvDni, tvData, tvSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        tvNom = (TextView) this.findViewById(R.id.tv_resName);
        tvDni = (TextView) this.findViewById(R.id.tv_resDni);
        tvData = (TextView) this.findViewById(R.id.tv_resData);
        tvSex = (TextView) this.findViewById(R.id.tv_resSex);

        //creamos el objeto de preferencias compartidas, que ahora con getSharedPreferences le diremos de donde queremos recuperar las preferencias
        SharedPreferences mySharedPreferences = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);

        //recuperamos indicándole la clave de cada referencia que queremos recuperar. El segundo valor es el valor por defecto
        String name = mySharedPreferences.getString("name", "");
        String dni = mySharedPreferences.getString("dni", "");
        String day = mySharedPreferences.getString("day", "");
        String month = mySharedPreferences.getString("month", "");
        String year = mySharedPreferences.getString("year", "");
        String sex = mySharedPreferences.getString("sex", "");

        //asignamos los valores a cada TextView donde queremos colocarlo
        tvNom.setText(name);
        tvDni.setText(dni);
        tvData.setText(day+"-"+month+"-"+year);
        tvSex.setText(sex);
    }
}
