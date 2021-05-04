package com.example.apptienda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.apptienda.models.Usuario;
import com.example.apptienda.viewModels.LoginViewModel;
import com.example.apptienda.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    LoginViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vm=new ViewModelProvider(getViewModelStore(),ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(LoginViewModel.class);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(vm);
        binding.executePendingBindings();
    }
    public void login(View V){
        Usuario usuarioLogeado=vm.login();
        if(usuarioLogeado!=null){
            Toast.makeText(this, usuarioLogeado.toString(), Toast.LENGTH_SHORT).show();
            //Intent it=new Intent(this,Registro.class);
            //it.putExtra("usuario",usuarioLogeado);
        }
        else{
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
    public void registrarse(View V){
        Intent it=new Intent(this,Registro.class);
        startActivity(it);
    }
}