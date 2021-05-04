package com.example.apptienda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apptienda.databinding.ActivityMainBinding;
import com.example.apptienda.databinding.ActivityRegistroBinding;
import com.example.apptienda.viewModels.FullViewModel;
import com.example.apptienda.viewModels.LoginViewModel;

public class Registro extends AppCompatActivity {
    EditText name,apellido,tlf,fecha;
    FullViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        name=findViewById(R.id.nombre_registro);
        apellido=findViewById(R.id.apellidos_registro);
        tlf=findViewById(R.id.tlf);
        fecha=findViewById(R.id.fecha_registro);
        vm=new ViewModelProvider(getViewModelStore(),ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(FullViewModel.class);
        ActivityRegistroBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_registro);
        //HolaMorta
        //Hey
        //o.o funciona
    }
    public void calendario(View view){
        EditText et=findViewById(R.id.fecha_registro);
        showDatePickerDialog(et);
    }
    public void ir_pag2(View V){
        if(name.getText().length()!=0 && apellido.getText().length()!=0 && tlf.getText().length()!=0 && fecha.getText().length()!=0){
            if(tlf.getText().length()==9) {
                Intent it = new Intent(this, Registro2.class);
                it.putExtra("nombre",name.getText().toString());
                it.putExtra("apellido",apellido.getText().toString());
                it.putExtra("tlf",tlf.getText().toString());
                it.putExtra("fecha",fecha.getText().toString());
                startActivity(it);
            }
            else {
                Toast.makeText(this, R.string.toast_tamano_tlf, Toast.LENGTH_SHORT).show();

            }
        }
        else {
            Toast.makeText(this, R.string.toast_rellenar_campos, Toast.LENGTH_SHORT).show();
        }

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