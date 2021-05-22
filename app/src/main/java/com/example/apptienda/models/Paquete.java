
package com.example.apptienda.models;


import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.apptienda.App;
import com.example.apptienda.helpers.SingletonRequestQueue;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Paquete implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("borrado")
    @Expose
    private String borrado;
    public final static Creator<Paquete> CREATOR = new Creator<Paquete>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Paquete createFromParcel(android.os.Parcel in) {
            return new Paquete(in);
        }

        public Paquete[] newArray(int size) {
            return (new Paquete[size]);
        }

    }
    ;

    protected Paquete(android.os.Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.nombre = ((String) in.readValue((String.class.getClassLoader())));
        this.img = ((String) in.readValue((String.class.getClassLoader())));
        this.borrado = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Paquete() {
    }

    /**
     * 
     * @param img
     * @param borrado
     * @param id
     * @param nombre
     */
    public Paquete(String id, String nombre, String img, String borrado) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.img = img;
        this.borrado = borrado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBorrado() {
        return borrado;
    }

    public void setBorrado(String borrado) {
        this.borrado = borrado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Paquete.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("nombre");
        sb.append('=');
        sb.append(((this.nombre == null)?"<null>":this.nombre));
        sb.append(',');
        sb.append("img");
        sb.append('=');
        sb.append(((this.img == null)?"<null>":this.img));
        sb.append(',');
        sb.append("borrado");
        sb.append('=');
        sb.append(((this.borrado == null)?"<null>":this.borrado));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    public static ArrayList<Paquete> obtenerPaquetes() throws ExecutionException, InterruptedException {
        CompletableFuture<ArrayList<Paquete>> future = new CompletableFuture<>();
        try {
            Gson gson=new Gson();
            JSONObject objetoPeticion = new JSONObject();

            objetoPeticion.put("action", "");
            String url = "http://pruebatiendadam.atwebpages.com/php/android/listener.php";
            RequestQueue queue = SingletonRequestQueue.getInstance(App.getContext()).getQueue();
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, objetoPeticion,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            ArrayList<Paquete> paquetes = gson.fromJson(response.toString(),new TypeToken<ArrayList<Pedido>>(){}.getType());
                            future.complete(paquetes);
                        }
                    }
                    ,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            future.completeExceptionally(new RemoteException());
                        }
                    });
            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return future.get();
    }
    public ArrayList<Producto> obtenerProductos() throws ExecutionException, InterruptedException {
        CompletableFuture<ArrayList<Producto>> future = new CompletableFuture<>();
        try {
            Gson gson=new Gson();
            JSONObject objetoPeticion = new JSONObject();
            objetoPeticion.put("action", "assigned_products");
            objetoPeticion.put("id",this.getId());
            String url = "http://pruebatiendadam.atwebpages.com/php/android/listener.php";
            RequestQueue queue = SingletonRequestQueue.getInstance(App.getContext()).getQueue();
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, objetoPeticion,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            ArrayList<Producto> productos =gson.fromJson(response.toString(),new TypeToken<ArrayList<Producto>>(){}.getType());;
                            future.complete(productos);
                        }
                    }
                    ,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            future.completeExceptionally(new RemoteException());
                        }
                    });
            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return future.get();
    }
    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.img == null)? 0 :this.img.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.nombre == null)? 0 :this.nombre.hashCode()));
        result = ((result* 31)+((this.borrado == null)? 0 :this.borrado.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Paquete) == false) {
            return false;
        }
        Paquete rhs = ((Paquete) other);
        return ((((Objects.equals(this.img, rhs.img))&&(Objects.equals(this.id, rhs.id)))&&(Objects.equals(this.nombre, rhs.nombre)))&&(Objects.equals(this.borrado, rhs.borrado)));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(nombre);
        dest.writeValue(img);
        dest.writeValue(borrado);
    }

    public int describeContents() {
        return  0;
    }

}
