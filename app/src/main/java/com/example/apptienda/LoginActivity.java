package com.example.apptienda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.apptienda.viewmodels.LoginViewModel;
import com.example.apptienda.databinding.ActivityLoginBinding;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.BlurTransformation;

import static com.example.apptienda.helpers.App.getContext;

public class LoginActivity extends AppCompatActivity {
    LoginViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        vm=new ViewModelProvider(this).get(LoginViewModel.class);
        vm.directLogin();
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setViewModel(vm);
        binding.executePendingBindings();
        ImageView background=findViewById(R.id.loginBackground);
        Picasso.get().load("https://www.telemadrid.es/2019/07/16/programas/madrid-trabaja/libreria_2140595955_7143659_1300x731.jpg").
                fit().transform(new BlurTransformation(getContext(),25,1)).centerCrop().into(background);
        ImageView logo=findViewById(R.id.logo);
        Picasso.get().load("https://api.freelogodesign.org/assets/thumb/logo/24146791_400.png?t=637479826540000000").placeholder(R.drawable.ic_launcher_background).
                fit().centerInside().into(logo);
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