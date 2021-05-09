package com.example.apptienda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.apptienda.models.DataRepository;
import com.example.apptienda.models.Usuario;
import com.example.apptienda.viewmodels.LoginViewModel;
import com.example.apptienda.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    LoginViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vm=new ViewModelProvider(this).get(LoginViewModel.class);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(vm);
        binding.executePendingBindings();
    }
    public void login(View V){
        vm.login();
        //Intent it=new Intent(this,HomeActivity.class);
        //startActivity(it);
    }
    public void registrarse(View V){
        Intent it=new Intent(this,RegisterFormActivity.class);
        startActivity(it);
    }
    //Solo es para probar como se ve la pagina, luego lo borro
    public void ir_pagUsuario(View V){
        Intent it = new Intent(this, perfil_usuario.class);
        startActivity(it);

    }
}