package com.example.apptienda.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.Patterns;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.apptienda.App;
import com.example.apptienda.helpers.SingletonRequestQueue;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.regex.Pattern;

public class Usuario implements Parcelable{
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
     * @param fechaNacimiento
     * @param email
     * @param telefono
     * @param calle
     * @param ciudad
     */

    public Usuario(String nombre, String apellidos, String fechaNacimiento, String email, String telefono, String calle, String ciudad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
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
    public Usuario(Parcel in) {
        readFromParceable(in);
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

    public static boolean validateNombre(String nombre) {
        return nombre.length()<=50 && nombre.length()>0;
    }
    public static boolean validateApellidos(String apellidos) {
        return apellidos.length()<=40 && apellidos.length()>0;
    }
    public static boolean validateFechaNacimiento(String fechaNacimiento) {
        return fechaNacimiento.length()>0;
    }
    public static  boolean validateTelefono(String telefono) {
        return Patterns.PHONE.matcher(telefono).matches();
    }
    public static boolean validateCalle(String calle) {
        return calle.length()<=55&& calle.length()>0;
    }
    public static boolean validateCiudad(String ciudad) {
        return ciudad.length()<=50&& ciudad.length()>0;
    }
    public static boolean validatePassword(String password) {
        return password.length()<17&&password.length()>7;
    }
    public static boolean validateEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombre, usuario.nombre) &&
                Objects.equals(apellidos, usuario.apellidos) &&
                Objects.equals(fechaNacimiento, usuario.fechaNacimiento) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(telefono, usuario.telefono) &&
                Objects.equals(calle, usuario.calle) &&
                Objects.equals(ciudad, usuario.ciudad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellidos, fechaNacimiento, email, telefono, calle, ciudad);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
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
            String url="http://pruebatiendadam.atwebpages.com/php/android/listener.php";
            JSONObject usuarioObject = new JSONObject(usuarioJson);
            usuarioObject.put("hash_pass",hash);
            usuarioObject.put("action","register");
            Log.i("todo listo registro:",usuarioObject.toString());
            RequestQueue queue = SingletonRequestQueue.getInstance(App.getContext()).getQueue();
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, url, usuarioObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i(getClass().getSimpleName(),response.toString());
                            callback.onSuccessResponse(response);
                        }
                    }
                    ,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            callback.onErrorResponse(error);
                        }
                    });
            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void LogIn(String email,String password, final VolleyCallback callback){
        try {
            JSONObject logData = new JSONObject();
            logData.put("email",email);
            logData.put("hash_pass",getPassHass(password));
            logData.put("action","login");
            String url="http://pruebatiendadam.atwebpages.com/php/android/listener.php";
            RequestQueue queue = SingletonRequestQueue.getInstance(App.getContext()).getQueue();
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, url, logData,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            callback.onSuccessResponse(response);
                        }
                    }
                    ,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            callback.onErrorResponse(error);
                        }
                    });
            queue.add(request);
        }catch (JSONException e) {
           e.printStackTrace();
        }
    }
    public static void ModificarUsuario(Usuario usuario,final VolleyCallback callback) {
        Gson gson= new Gson();
        try {
            String usuarioJson = gson.toJson(usuario,Usuario.class);
            JSONObject logData = new JSONObject(usuarioJson);
            logData.put("action","modficar");
            Log.i("POSTDATA",logData.toString());
            String url="http://pruebatiendadam.atwebpages.com/php/android/listener.php";
            RequestQueue queue = SingletonRequestQueue.getInstance(App.getContext()).getQueue();
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, url, logData,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Gson gson=new Gson();
                            DataRepository.setUsuarioLogeado(gson.fromJson(response.toString(),Usuario.class));
                            callback.onSuccessResponse(response);
                        }
                    }
                    ,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            callback.onErrorResponse(error);
                        }
                    }
                    );
            queue.add(request);
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String getPassHass(String input) {
        String hash=null;
        try {
                MessageDigest md = MessageDigest.getInstance("MD5");

                byte[] messageDigest = md.digest(input.getBytes());

                BigInteger no = new BigInteger(1, messageDigest);
                //Deberiamos de evitar concatenar en bucles por temas de rendimiento
                StringBuilder hashCto = new StringBuilder(no.toString(16));
                while (hashCto.length() < 32) {
                    hashCto.insert(0, "0");
                }
                hash = hashCto.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(apellidos);
        dest.writeString(fechaNacimiento);
        dest.writeString(email);
        dest.writeString(telefono);
        dest.writeString(calle);
        dest.writeString(ciudad);
    }
    private void readFromParceable(Parcel in) {
        this.nombre=in.readString();
        this.apellidos=in.readString();
        this.fechaNacimiento=in.readString();
        this.email=in.readString();
        this.telefono=in.readString();
        this.calle=in.readString();
        this.ciudad=in.readString();
    }
    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}
