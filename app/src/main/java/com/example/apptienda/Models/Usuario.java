package com.example.apptienda.Models;

import android.util.Log;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.apptienda.App;
import com.example.apptienda.Helpers.SingletonRequestQueue;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Usuario {
    private String nombre;
    private String apellidos;
    private String correo;
    private String fechaNacimiento;
    private String email;
    private String telefono;
    private String calle;
    private String ciudad;

    /**
     * Contructor completo que se usa al recoger los datos de la base de datos directamente
     * @param nombre
     * @param apellidos
     * @param correo
     * @param fechaNacimiento
     * @param email
     * @param telefono
     * @param calle
     * @param ciudad
     */

    public Usuario(String nombre, String apellidos, String correo, String fechaNacimiento, String email, String telefono, String calle, String ciudad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.telefono = telefono;
        this.calle = calle;
        this.ciudad = ciudad;
    }

    /**
     * Constructor sin parametros para usar durante el registro
     */
    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombre, usuario.nombre) &&
                Objects.equals(apellidos, usuario.apellidos) &&
                Objects.equals(correo, usuario.correo) &&
                Objects.equals(fechaNacimiento, usuario.fechaNacimiento) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(telefono, usuario.telefono) &&
                Objects.equals(calle, usuario.calle) &&
                Objects.equals(ciudad, usuario.ciudad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellidos, correo, fechaNacimiento, email, telefono, calle, ciudad);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", correo='" + correo + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", calle='" + calle + '\'' +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
    public static void RegistrarUsuario(Usuario usuario,String password,final VolleyCallback callback) {
        Gson gson= new Gson();
        try {
            String usuarioJson = gson.toJson(usuario,Usuario.class);
            String hash=getPassHass(password);
            String url="";
            JSONObject usuarioObject = new JSONObject(usuarioJson);
            usuarioObject.put("hash",hash);
            RequestQueue queue = SingletonRequestQueue.getInstance(App.getContext()).getQueue();
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, url, usuarioObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i(getClass().getSimpleName(),response.toString());
                            callback.onSuccessResponse(response.toString());
                        }
                    }
                    ,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i(getClass().getSimpleName(),error.toString());
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    /*public static boolean RegistrarUsuario(Usuario usuario,String password) {
        AtomicBoolean result= new AtomicBoolean(false);
        Gson gson= new Gson();
        try {
            String usuarioJson = gson.toJson(usuario,Usuario.class);
            String hash=getPassHass(password);
            String url="";
            JSONObject usuarioObject = new JSONObject(usuarioJson);
            usuarioObject.put("hash",hash);
            RequestQueue queue = SingletonRequestQueue.getInstance(App.getContext()).getQueue();
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST,url,usuarioObject,
                    response -> result.set(true)
                    ,
                    error -> result.set(false));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result.get();
    }*/
    public static void LogIn(String usuario,String password, final VolleyCallback callback){
        try {
            JSONObject logData = new JSONObject();
            logData.put("user",usuario);
            logData.put("hash",getPassHass(password));
            String url="";
            RequestQueue queue = SingletonRequestQueue.getInstance(App.getContext()).getQueue();
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, url, logData,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i(getClass().getSimpleName(),response.toString());
                            callback.onSuccessResponse(response.toString());
                        }
                    }
                    ,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i(getClass().getSimpleName(),error.toString());
                        }
                    });
        }catch (JSONException e) {
            Log.e("Json parseado error","Error:",e);
        }
    }
    public static void ModificarUsuario(Usuario usuario,final VolleyCallback callback) {
        Gson gson= new Gson();
        try {
            String usuarioJson = gson.toJson(usuario,Usuario.class);
            JSONObject logData = new JSONObject(usuarioJson);
            String url="";
            RequestQueue queue = SingletonRequestQueue.getInstance(App.getContext()).getQueue();
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, url, logData,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i(getClass().getSimpleName(),response.toString());
                            callback.onSuccessResponse(response.toString());
                        }
                    }
                    ,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i(getClass().getSimpleName(),error.toString());
                        }
                    });
        }catch (JSONException e) {
            Log.e("Json parseado error","Error:",e);
        }
    }

    public static String getPassHass(String input) {
        try {
                MessageDigest md = MessageDigest.getInstance("MD5");

                byte[] messageDigest = md.digest(input.getBytes());

                BigInteger no = new BigInteger(1, messageDigest);

                String hash = no.toString(16);
                while (hash.length() < 32) {
                    hash = "0" + hash;
                }
                return hash;
            }
        catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
        }
    }
}
