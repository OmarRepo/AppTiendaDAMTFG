package com.example.buyaskill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.buyaskill.viewmodels.LoginViewModel;
import com.example.buyaskill.databinding.ActivityLoginBinding;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.BlurTransformation;

import static com.example.buyaskill.helpers.App.getContext;

public class LoginActivity extends AppCompatActivity {
    LoginViewModel vm;
    @Override
    //creamos los elementos a usar para el login, usando Piccaso para cargar las imagenes deseadas
    protected void onCreate(Bundle savedInstanceState) {
        vm=new ViewModelProvider(this).get(LoginViewModel.class);
        vm.directLogin();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setViewModel(vm);
        binding.executePendingBindings();
        ImageView background=findViewById(R.id.loginBackground);
        Picasso.get().load("https://www.telemadrid.es/2019/07/16/programas/madrid-trabaja/libreria_2140595955_7143659_1300x731.jpg").
                fit().transform(new BlurTransformation(getContext(),25,1)).centerCrop().into(background);
        ImageView logo=findViewById(R.id.logo);
        Picasso.get().load(R.drawable.logo_transparent).placeholder(R.drawable.ic_launcher_background).
                resize(750,750).centerCrop().into(logo);
    }
    public void login(View V){
        vm.login();
    }
    public void registrarse(View V){
        Intent it = new Intent(this, RegisterFormActivity.class);
        startActivity(it);
    }
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            super.onBackPressed();
        } else {

        }
    }
}