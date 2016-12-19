package com.example.alan_.actividad_3b;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //declaramos variables a las que más tarde asignaremos valores recogidos por los views
    private String name, dni, day, month, year, sex;
    //variable que utilizaremos para identificar nuestas preferencias
    public static final String PREFS = "My preferences";
    //declaramos los views que van a interactuar
    private EditText etNom, etDni, etDay, etMonth, etAnyo;
    private RadioButton rbMan, rbWoman;
    private Button btSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //asignamos a cada variable su view
        etNom = (EditText) this.findViewById(R.id.et_nom);
        etDni = (EditText) this.findViewById(R.id.et_dni);
        etDay = (EditText) this.findViewById(R.id.et_day);
        etMonth = (EditText) this.findViewById(R.id.et_month);
        etAnyo = (EditText) this.findViewById(R.id.et_anyo);
        rbMan = (RadioButton) this.findViewById(R.id.rb_man);
        rbWoman = (RadioButton) this.findViewById(R.id.rb_woman);
        btSave = (Button) this.findViewById(R.id.bt_save);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creamos el intent, indicandole desde donde lo llamamos y a donde vamos
                Intent intent = new Intent(MainActivity.this, Resultado.class);

                //cogemos valores de los views y los asignamos a las variables
                name = String.valueOf(etNom.getText());
                dni = String.valueOf(etDni.getText());
                day = String.valueOf(etDay.getText());
                month = String.valueOf(etMonth.getText());
                year = String.valueOf(etAnyo.getText());
                if (rbMan.isChecked()) sex = String.valueOf("Hombre");
                else sex = String.valueOf("Mujer");

                //creamos el objeto de preferencias compartidas
                SharedPreferences mySharedPreferences = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);//modo privado para acceder a estas preferencias

                //obtenemos un editor para modificar las preferencias
                SharedPreferences.Editor editor = mySharedPreferences.edit();

                //verificamos que el usuario a rellenado bien los campos
                if (name.isEmpty()) Toast.makeText(getApplicationContext(), R.string.error_name, Toast.LENGTH_LONG).show();
                else if (dni.isEmpty()) Toast.makeText(getApplicationContext(), R.string.error_dni, Toast.LENGTH_LONG).show();
                else if (day.isEmpty() || Integer.valueOf(day) > 31 || Integer.valueOf(day) < 1) Toast.makeText(getApplicationContext(), R.string.error_date, Toast.LENGTH_LONG).show();
                else if (month.isEmpty() || Integer.valueOf(month) > 12 || Integer.valueOf(month) < 1) Toast.makeText(getApplicationContext(), R.string.error_date, Toast.LENGTH_LONG).show();
                else if (year.isEmpty()) Toast.makeText(getApplicationContext(), R.string.error_date, Toast.LENGTH_LONG).show();

                else {
                //almacenamos objetos en formato clave/valor mediante el editor
                editor.putString("name", name);
                editor.putString("dni", dni);
                editor.putString("day", day);
                editor.putString("month", month);
                editor.putString("year", year);
                editor.putString("sex", sex);
                //guardar cambios
                editor.commit();
                startActivity(intent);
                }
            }
        });
    }
}