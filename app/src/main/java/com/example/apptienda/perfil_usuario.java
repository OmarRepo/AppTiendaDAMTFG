package com.example.apptienda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.apptienda.helpers.SingletonRequestQueue;
import com.example.apptienda.models.DataRepository;
import com.example.apptienda.models.Usuario;
import com.example.apptienda.models.VolleyCallback;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class perfil_usuario extends AppCompatActivity {
    EditText nombre,apellido,dir,cod,fecha,tlf,correo,pass;
    Usuario usu;
    int clickado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        nombre=findViewById(R.id.nombre_perfilusu);
        apellido=findViewById(R.id.apellido_perfilusu);
        dir=findViewById(R.id.direccion_perfilusu);
        cod=findViewById(R.id.cod_postal_perfilusu);
        fecha=findViewById(R.id.fechanaci_perfilusu);
        tlf=findViewById(R.id.numtlf_perfilusu);
        correo=findViewById(R.id.correo_perfilusu);
        pass=findViewById(R.id.pass_perfilusu);
        usu= DataRepository.getUsuarioLogeado();
        clickado=0;
    }
    public void cambiarEdit(View view){
        if (clickado == 0) {
            nombre.setEnabled(true);
            apellido.setEnabled(true);
            dir.setEnabled(true);
            cod.setEnabled(true);
            fecha.setEnabled(true);
            tlf.setEnabled(true);
            correo.setEnabled(true);
            pass.setEnabled(true);
            clickado=1;
        }
    }
    public void enviarDatosNuevos(View view){
        if(clickado!=0){
            if(nombre.getText().length()!=0 && apellido.getText().length()!=0 && dir.getText().length()!=0 && cod.getText().length()!=0 && fecha.getText().length()!=0 && tlf.getText().length()!=0 && correo.getText().length()!=0 && pass.getText().length()!=0){
                if(cod.length()==5){
                    if(tlf.length()==9){
                        if(validarEmail(correo.getText().toString())){
                            Toast.makeText(this, "Todo okey", Toast.LENGTH_SHORT).show();
                            nombre.setEnabled(false);
                            apellido.setEnabled(false);
                            dir.setEnabled(false);
                            cod.setEnabled(false);
                            fecha.setEnabled(false);
                            tlf.setEnabled(false);
                            correo.setEnabled(false);
                            pass.setEnabled(false);
                            clickado=0;
                        }
                        else {
                            Toast.makeText(this, R.string.toast_formato_correo, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(this, R.string.toast_tamano_tlf, Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this, R.string.toast_tamano_postal, Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this, R.string.toast_rellenar_campos, Toast.LENGTH_SHORT).show();
            }
        }else
            Toast.makeText(this, R.string.modificaDatosAntes, Toast.LENGTH_SHORT).show();

    }
    public void volverTienda(View view){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);

    }
    public  boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    public void calendario(View view){
        EditText et=findViewById(R.id.fechanaci_perfilusu);
        showDatePickerDialog(et);
    }

    private void showDatePickerDialog(EditText et) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                et.setText(selectedDate);
            }
        });

        newFragment.show(this.getSupportFragmentManager(), "datePicker");

    }
}