
package com.example.apptienda.models;

import android.os.Parcelable;
import android.os.RemoteException;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.apptienda.helpers.App;
import com.example.apptienda.helpers.CustomJsonArrayRequest;
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

public class Pedido implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("fecha_creacion")
    @Expose
    private String fechaCreacion;
    @SerializedName("precio_total")
    @Expose
    private String precioTotal;
    @SerializedName("borrado")
    @Expose
    private String borrado;
    @SerializedName("enviado")
    @Expose
    private String enviado;
    @SerializedName("recibido")
    @Expose
    private String recibido;
    @SerializedName("paquetes")
    @Expose
    private ArrayList<Paquete> paquetes;

    public final static Creator<Pedido> CREATOR = new Creator<Pedido>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Pedido createFromParcel(android.os.Parcel in) {
            return new Pedido(in);
        }

        public Pedido[] newArray(int size) {
            return (new Pedido[size]);
        }

    }
    ;

    protected Pedido(android.os.Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.fechaCreacion = ((String) in.readValue((String.class.getClassLoader())));
        this.precioTotal = ((String) in.readValue((String.class.getClassLoader())));
        this.borrado = ((String) in.readValue((String.class.getClassLoader())));
        this.enviado = ((String) in.readValue((String.class.getClassLoader())));
        this.recibido = ((String) in.readValue((String.class.getClassLoader())));
        this.paquetes = ((ArrayList<Paquete>) in.readValue((ArrayList.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Pedido() {
    }

    /**
     * 
     * @param borrado
     * @param fechaCreacion
     * @param enviado
     * @param id
     * @param recibido
     * @param precioTotal
     */
    public Pedido(String id, String fechaCreacion, String precioTotal, String borrado, String enviado, String recibido) {
        super();
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.precioTotal = precioTotal;
        this.borrado = borrado;
        this.enviado = enviado;
        this.recibido = recibido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(String precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getBorrado() {
        return borrado;
    }

    public void setBorrado(String borrado) {
        this.borrado = borrado;
    }

    public String getEnviado() {
        return enviado;
    }

    public void setEnviado(String enviado) {
        this.enviado = enviado;
    }

    public String getRecibido() {
        return recibido;
    }

    public void setRecibido(String recibido) {
        this.recibido = recibido;
    }

    public ArrayList<Paquete> getPaquetes () {return paquetes; }

    public void setPaquetes(ArrayList<Paquete> paquetes) {this.paquetes=paquetes; }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Pedido.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("fechaCreacion");
        sb.append('=');
        sb.append(((this.fechaCreacion == null)?"<null>":this.fechaCreacion));
        sb.append(',');
        sb.append("precioTotal");
        sb.append('=');
        sb.append(((this.precioTotal == null)?"<null>":this.precioTotal));
        sb.append(',');
        sb.append("borrado");
        sb.append('=');
        sb.append(((this.borrado == null)?"<null>":this.borrado));
        sb.append(',');
        sb.append("enviado");
        sb.append('=');
        sb.append(((this.enviado == null)?"<null>":this.enviado));
        sb.append(',');
        sb.append("recibido");
        sb.append('=');
        sb.append(((this.recibido == null)?"<null>":this.recibido));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    public static List<Pedido> obtenerPedidos() throws ExecutionException, InterruptedException {
        CompletableFuture<ArrayList<Pedido>> future = new CompletableFuture<>();
        try {
            Gson gson=new Gson();
            JSONObject objetoPeticion = new JSONObject();
            objetoPeticion.put("action",DataRepository.getUsuarioLogeado().getId());
            objetoPeticion.put("action", "list_orders");
            String url = "http://pruebatiendadam.atwebpages.com/php/android/listener.php";
            RequestQueue queue = SingletonRequestQueue.getInstance(App.getContext()).getQueue();
            CustomJsonArrayRequest request = new CustomJsonArrayRequest(Request.Method.POST, url, objetoPeticion,
                    response -> {
                        ArrayList<Pedido> pedidos = gson.fromJson(response.toString(),new TypeToken<ArrayList<Pedido>>(){}.getType());
                        future.complete(pedidos);
                    }
                    ,
                    error -> future.completeExceptionally(new RemoteException()));
            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return future.get();
    }
    public boolean crearPedido() throws ExecutionException, InterruptedException {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        try {
            Gson gson=new Gson();
            JSONObject objetoPeticion = new JSONObject();
            objetoPeticion.put("action", "place_order");
            objetoPeticion.put("order",gson.toJson(this));
            String url = "http://pruebatiendadam.atwebpages.com/php/android/listener.php";
            RequestQueue queue = SingletonRequestQueue.getInstance(App.getContext()).getQueue();
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, objetoPeticion,
                    response -> {
                        gson.fromJson(response.toString(),Pedido.class);
                        future.complete(true);
                    }
                    ,
                    error -> future.complete(false));
            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return future.get().booleanValue();
    }
    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.borrado == null)? 0 :this.borrado.hashCode()));
        result = ((result* 31)+((this.fechaCreacion == null)? 0 :this.fechaCreacion.hashCode()));
        result = ((result* 31)+((this.enviado == null)? 0 :this.enviado.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.recibido == null)? 0 :this.recibido.hashCode()));
        result = ((result* 31)+((this.precioTotal == null)? 0 :this.precioTotal.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Pedido) == false) {
            return false;
        }
        Pedido rhs = ((Pedido) other);
        return ((((((Objects.equals(this.borrado, rhs.borrado))&&(Objects.equals(this.fechaCreacion, rhs.fechaCreacion)))&&(Objects.equals(this.enviado, rhs.enviado)))&&(Objects.equals(this.id, rhs.id)))&&(Objects.equals(this.recibido, rhs.recibido)))&&(Objects.equals(this.precioTotal, rhs.precioTotal)));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(fechaCreacion);
        dest.writeValue(precioTotal);
        dest.writeValue(borrado);
        dest.writeValue(enviado);
        dest.writeValue(recibido);
        dest.writeValue(paquetes);
    }

    public int describeContents() {
        return  0;
    }

}
