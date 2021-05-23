package com.example.apptienda.viewmodels;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.apptienda.App;
import com.example.apptienda.HomeActivity;
import com.example.apptienda.RegisterFormActivity;
import com.example.apptienda.models.DataRepository;
import com.example.apptienda.models.Usuario;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class LoginViewModel extends ViewModel {
    public final ObservableField<String> email;
    public final ObservableField<String> password;
    public final ObservableBoolean actionsEnabled;
    public LoginViewModel() {
        super();
        email= new ObservableField<>("");
        password=new ObservableField<>("");
        actionsEnabled=new ObservableBoolean(true);
    }
    public void login() {
        if(validateFields()) {
            actionsEnabled.set(false);
            Thread task= new Thread(() -> {
                try {
                    Usuario usu = Usuario.LogIn(email.get(), password.get());
                    DataRepository.postUsuarioLogeado(usu);
                    navigateToHome();
                } catch (ExecutionException | InterruptedException | TimeoutException | JSONException e) {
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(() -> Toast.makeText(App.getApplication(), "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show());
                } finally {
                    actionsEnabled.set(true);
                }
            }
            );
            task.start();
        }
    }
    private boolean validateFields() {
        return Usuario.validatePassword(password.get()) && Usuario.validateEmail(email.get());
    }
    private void navigateToHome() {
        actionsEnabled.set(false);
        Intent it=new Intent(App.getContext(), HomeActivity.class);
        App.getApplication().startActivity(it);
        actionsEnabled.set(true);
    }
    private void navigateToRegister() {
        actionsEnabled.set(false);
        Intent it=new Intent(App.getContext(), RegisterFormActivity.class);
        App.getApplication().startActivity(it);
        actionsEnabled.set(true);
    }
}
