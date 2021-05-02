package com.example.apptienda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Registro3 extends AppCompatActivity {
    String nombre;
    String apellido;
    String tlf;
    String fecha;
    String direccion;
    String ciudad;
    String postal;
    EditText correo,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro3);
        nombre=getIntent().getStringExtra ("nombre");
        apellido=getIntent().getStringExtra("apellido");
        tlf=getIntent().getStringExtra("tlf");
        fecha=getIntent().getStringExtra("fecha");
        direccion=getIntent().getStringExtra ("direccion");
        ciudad=getIntent().getStringExtra("ciudad");
        postal=getIntent().getStringExtra("postal");
        correo=findViewById(R.id.correo);
        pass=findViewById(R.id.pass_registro);

    }
    public  boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    public void fin_registro(View V){
        if(correo.getText().length()!=0 && pass.getText().length()!=0){
            if(validarEmail(correo.getText().toString())){
                Intent it=new Intent(this,MainActivity.class);
               /* it.putExtra("nombre",nombre);
                it.putExtra("apellido",apellido);
                it.putExtra("tlf",tlf);
                it.putExtra("fecha",fecha);
                it.putExtra("direccion",direccion);
                it.putExtra("ciudad",ciudad);
                it.putExtra("postal",postal);
                it.putExtra("correo",correo.getText().toString());
                it.putExtra("pass",pass.getText().toString());*/
                Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();
                startActivity(it);
            }
            else {
                Toast.makeText(this, R.string.toast_formato_correo, Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, R.string.toast_rellenar_campos, Toast.LENGTH_SHORT).show();
        }

    }
}