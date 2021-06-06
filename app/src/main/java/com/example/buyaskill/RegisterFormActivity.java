package com.example.buyaskill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.buyaskill.viewmodels.RegisterViewModel;

import org.json.JSONException;

public class RegisterFormActivity extends AppCompatActivity {
    RegisterViewModel vm;
    FragmentTransaction transaction;
    RegisterFormFragment_1 form1;
    RegisterFormFragment_2 form2;
    RegisterFormFragment_3 form3;
    Fragment currentForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);
        vm=new ViewModelProvider(this).get(RegisterViewModel.class);
        transaction=getSupportFragmentManager().beginTransaction();
        form1=new RegisterFormFragment_1();
        form2=new RegisterFormFragment_2();
        form3=new RegisterFormFragment_3();
        transaction.add(R.id.formFragmentContainer,form1).commit();
    }

    public void next(View view) {
        currentForm=getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getFragments().size()-1);
        transaction=getSupportFragmentManager().beginTransaction();
        String error="";
        if(currentForm.equals(form1)) {
            error=form1.validateFields();
            if(error.length()==0) {
                transaction.replace(R.id.formFragmentContainer, form2);
                transaction.addToBackStack(null);
            }
            else{
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        }
        if(currentForm.equals(form2)) {
            error=form2.validateFields();
            if(error.length()==0) {
                transaction.replace(R.id.formFragmentContainer, form3);
                transaction.addToBackStack(null);
            }
            else{
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        }
        if(currentForm.equals(form3)) {
            error= form3.validateFields();
            if(error.length()==0) {
                vm.rellenarUsuario();
                try {
                    vm.intentarRegistro();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else{
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        }
        transaction.commit();
    }
    public void calendario(View view) {
        form1.calendario(view);
    }
}