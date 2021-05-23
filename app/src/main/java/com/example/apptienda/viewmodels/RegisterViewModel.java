package com.example.apptienda.viewmodels;

import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apptienda.App;
import com.example.apptienda.models.Usuario;

import java.util.concurrent.ExecutionException;

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
            resultado+="error:nombre\n";
        }
        if(!Usuario.validateApellidos(appellido.get())){
            resultado+="error:apellido\n";
        }
        if(!Usuario.validateTelefono(telefono.get())) {
            resultado+="error:telefono\n";
        }
        if(!Usuario.validateFechaNacimiento(fecha_nacimiento.get())){
            resultado+="error:nacimiento\n";
        }
        if(resultado.length()!=0) {

        }
        return resultado;
    }
    public String validateFields2() {
        String resultado="";
        if(resultado.length()!=0) {

        }
        return resultado;
    }
    public String validateFields3() {
        String resultado="";
        if(!Usuario.validateEmail(email.get())) {
            resultado+="error:email\n";
        }
        if(!Usuario.validatePassword(password.get())){
            resultado+="error:contraseña\n";
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
    public void intentarRegistro() {
        try {
            Usuario usu = registroUsuario.getValue().RegistrarUsuario(password.get());
            Toast.makeText(App.getContext(), "Registro de usuario: "+usu.getNombre()+"\nCompletado satisfactoriamente", Toast.LENGTH_SHORT).show();
        } catch (ExecutionException e) {
            Toast.makeText(App.getContext(), "Error al procesar el registro", Toast.LENGTH_SHORT).show();
        } catch (InterruptedException e) {
            Toast.makeText(App.getContext(), "Error al procesar el registro", Toast.LENGTH_SHORT).show();
        }

    }

}
