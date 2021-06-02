package com.example.apptienda.viewmodels;

import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.example.apptienda.R;
import com.example.apptienda.helpers.App;
import com.example.apptienda.helpers.Callbacks.VolleyJSONCallback;
import com.example.apptienda.models.Usuario;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


public class RegisterViewModel extends ViewModel {
    public MutableLiveData<Usuario> registroUsuario;
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
        nombre = new ObservableField<>("");
        appellido = new ObservableField<>("");
        telefono = new ObservableField<>("");
        fecha_nacimiento = new ObservableField<>("");
        calle = new ObservableField<>("");
        ciudad = new ObservableField<>("");
        codigoPostal = new ObservableField<>("");
        email = new ObservableField<>("");
        password = new ObservableField<>("");
        registroUsuario=new MutableLiveData<>();
    }
    public String validateFields1() {
        String resultado="";
        if(!Usuario.validateNombre(nombre.get())) {
            resultado+=App.getContext().getString(R.string.toast_nombreInvalido)+"\n";
        }
        if(!Usuario.validateApellidos(appellido.get())){
            resultado+=App.getContext().getString(R.string.toast_apellidoInvalido)+"\n";
        }
        if(!Usuario.validateTelefono(telefono.get())) {
            resultado+=App.getContext().getString(R.string.toast_tamano_tlf)+"\n";
        }
        if(!Usuario.validateFechaNacimiento(fecha_nacimiento.get())){
            resultado+=App.getContext().getString(R.string.toast_fechaInvalida)+"\n";
        }
        if(resultado.length()!=0) {

        }
        return resultado;
    }
    public String validateFields2() {
        String resultado="";
        if(!Usuario.validateCalle(calle.get())){
            resultado+=App.getContext().getString(R.string.toast_calleInvalida)+"\n";
        }
        if(!Usuario.validateCiudad(ciudad.get())) {
            resultado+=App.getContext().getString(R.string.toast_ciudad)+"\n";
        }
        if(!Usuario.validatePostal(codigoPostal.get())) {
            resultado += App.getContext().getString(R.string.toast_tamano_postal) + "\n";
        }
        return resultado;
    }
    public String validateFields3() {
        String resultado="";
        if(!Usuario.validateEmail(email.get())) {
            resultado+=App.getContext().getString(R.string.toast_formato_correo)+"\n";
        }
        if(!Usuario.validatePassword(password.get())){
            resultado+=App.getContext().getString(R.string.toast_formato_pass)+"\n";
        }
        if(resultado.length()!=0) {

        }
        return resultado;
    }
    public void rellenarUsuario() {
        Usuario usuarioNuevo=new Usuario();
        usuarioNuevo.setNombre(nombre.get());
        usuarioNuevo.setApellidos(appellido.get());
        usuarioNuevo.setCiudad(ciudad.get());
        usuarioNuevo.setEmail(email.get());
        usuarioNuevo.setCalle(calle.get()+","+codigoPostal.get());
        usuarioNuevo.setTelefono(telefono.get());
        usuarioNuevo.setFechaNacimiento(fecha_nacimiento.get());
        registroUsuario.setValue(usuarioNuevo);
    }
    public synchronized void intentarRegistro() throws JSONException {
        registroUsuario.getValue().RegistrarUsuario(password.get(), new VolleyJSONCallback() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                Gson gson = new Gson();
                Usuario usu = gson.fromJson(result.toString(),Usuario.class);
                Toast.makeText(App.getContext(), "Registro de usuario: "+usu.getNombre()+"\nCompletado satisfactoriamente", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(App.getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
