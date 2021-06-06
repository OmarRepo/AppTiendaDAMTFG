package com.example.buyaskill.viewmodels;

import android.content.Intent;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.example.buyaskill.LoginActivity;
import com.example.buyaskill.R;
import com.example.buyaskill.helpers.App;
import com.example.buyaskill.helpers.Callbacks.VolleyJSONCallback;
import com.example.buyaskill.models.Usuario;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


public class RegisterViewModel extends ViewModel {
    private MutableLiveData<Usuario> registroUsuario;

    public MutableLiveData<Usuario> getRegistroUsuario() {
        return registroUsuario;
    }
    //form1
    public ObservableField<String> nombre;
    public ObservableField<String> appellido;
    public ObservableField<String> telefono;
    public ObservableField<String> fecha_nacimiento;
    //form2
    public ObservableField<String> calle;
    public ObservableField<String> ciudad;
    public ObservableField<String> codigoPostal;
    //form3
    public ObservableField<String> email;
    public ObservableField<String> password;

    public RegisterViewModel() {
        calle = new ObservableField<>("");
        codigoPostal = new ObservableField<>("");
        password = new ObservableField<>("");
        registroUsuario=new MutableLiveData<>(new Usuario());
    }
    public String validateFields1() {
        String resultado="";
        if(!Usuario.validateNombre(registroUsuario.getValue().getNombre())) {
            resultado+=App.getContext().getString(R.string.toast_nombreInvalido)+"\n";
        }
        if(!Usuario.validateApellidos(registroUsuario.getValue().getApellidos())){
            resultado+=App.getContext().getString(R.string.toast_apellidoInvalido)+"\n";
        }
        if(!Usuario.validateTelefono(registroUsuario.getValue().getTelefono())) {
            resultado+=App.getContext().getString(R.string.toast_tamano_tlf)+"\n";
        }
        if(!Usuario.validateFechaNacimiento(registroUsuario.getValue().getFechaNacimiento())){
            resultado+=App.getContext().getString(R.string.toast_fechaInvalida)+"\n";
        }
        return resultado;
    }
    public String validateFields2() {
        String resultado="";
        if(!Usuario.validateCalle(calle.get())){
            resultado+=App.getContext().getString(R.string.toast_calleInvalida)+"\n";
        }
        if(!Usuario.validateCiudad(registroUsuario.getValue().getCiudad())) {
            resultado+=App.getContext().getString(R.string.toast_ciudad)+"\n";
        }
        if(!Usuario.validatePostal(codigoPostal.get())) {
            resultado += App.getContext().getString(R.string.toast_tamano_postal) + "\n";
        }
        return resultado;
    }
    public String validateFields3() {
        String resultado="";
        if(!Usuario.validateEmail(registroUsuario.getValue().getEmail())) {
            resultado+=App.getContext().getString(R.string.toast_formato_correo)+"\n";
        }
        if(!Usuario.validatePassword(password.get())){
            resultado+=App.getContext().getString(R.string.toast_formato_pass)+"\n";
        }
        return resultado;
    }
    public void rellenarUsuario() {
        registroUsuario.getValue().setCalle(calle.get()+","+codigoPostal.get());
    }
    public synchronized void intentarRegistro() throws JSONException {
        Toast.makeText(App.getContext(), "Intentando registrar al usuario: "+registroUsuario.getValue().toString(), Toast.LENGTH_SHORT).show();
        registroUsuario.getValue().RegistrarUsuario(password.get(), new VolleyJSONCallback() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                Gson gson = new Gson();
                Usuario usu = gson.fromJson(result.toString(),Usuario.class);
                Toast.makeText(App.getContext(), "Registro de usuario: "+usu.getNombre()+"\nCompletado satisfactoriamente", Toast.LENGTH_SHORT).show();
                Intent it=new Intent(App.getContext(), LoginActivity.class);
                App.getContext().startActivity(it);
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(App.getContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
