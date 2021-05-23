package com.example.apptienda.Basura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apptienda.R;

public class Registro2 extends AppCompatActivity {
    String nombre;
    String apellido;
    String tlf;
    String fecha;
    EditText direccion,ciudad,postal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nombre=getIntent().getStringExtra ("nombre");
         apellido=getIntent().getStringExtra("apellido");
         tlf=getIntent().getStringExtra("tlf");
         fecha=getIntent().getStringExtra("fecha");
         //
         direccion=findViewById(R.id.direccion);
        ciudad=findViewById(R.id.ciudad);
        postal=findViewById(R.id.cod_postal);

    }

    public void ir_pag3(View V){
        if(direccion.getText().length()!=0 && ciudad.getText().length()!=0 && postal.getText().length()!=0){
            if(postal.length()==5){
                Intent it=new Intent(this,Registro3.class);
                it.putExtra("nombre",nombre);
                it.putExtra("apellido",apellido);
                it.putExtra("tlf",tlf);
                it.putExtra("fecha",fecha);
                it.putExtra("direccion",direccion.getText().toString());
                it.putExtra("ciudad",ciudad.getText().toString());
                it.putExtra("postal",postal.getText().toString());
                startActivity(it);
            }
            else {
                Toast.makeText(this, R.string.toast_tamano_postal, Toast.LENGTH_SHORT).show();
            }


        }else {
            Toast.makeText(this, R.string.toast_rellenar_campos, Toast.LENGTH_SHORT).show();
        }

    }
}