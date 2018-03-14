package mx.edu.ittepic.a211_persistenciabasica_zulmacoronel;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Button guardar,cargar;

    EditText editcorreo;
    Spinner zodiaco;

    RadioGroup grupo;

    CheckBox prog,escribir,correr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        editcorreo=findViewById(R.id.editcorreo);
        zodiaco=findViewById(R.id.spinner);
        grupo=findViewById(R.id.radioGroup);

        prog=findViewById(R.id.cbxprogramar);
        escribir=findViewById(R.id.cbxescribir);
        correr=findViewById(R.id.cbxcorrer);


        cargar=findViewById(R.id.btncargar);

        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editcorreo.setText(sharedPreferences.getString("correo",null));
                zodiaco.setSelection(sharedPreferences.getInt("zodiaco",0));
                ((RadioButton)grupo.getChildAt(sharedPreferences.getInt("radio",0))).setChecked(true);
                prog.setChecked(sharedPreferences.getBoolean("chkprogra",false));
                escribir.setChecked(sharedPreferences.getBoolean("chkescr",false));
                correr.setChecked(sharedPreferences.getBoolean("chkcorrer",false));
            }
        });
        guardar=findViewById(R.id.btnsalvar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = getPreferences(MODE_PRIVATE).edit();
                editor.putString("correo", editcorreo.getText().toString());
                editor.putInt("zodiaco",   zodiaco.getSelectedItemPosition());
                int index = grupo.indexOfChild(findViewById(grupo.getCheckedRadioButtonId()));
                editor.putInt("radio",   index);
                editor.putBoolean("chkprogra",prog.isChecked() );
                editor.putBoolean("chkescr",escribir.isChecked() );
                editor.putBoolean("chkcorrer",correr.isChecked() );
                editor.commit();

                Toast.makeText(MainActivity.this,"DATOS GUARDADOS",Toast.LENGTH_LONG).show();
                editcorreo.setText("");
                prog.setChecked(false);
                escribir.setChecked(false);
                correr.setChecked(false);
                grupo.clearCheck();
                zodiaco.setSelection(0);
            }

        });

    }

}

