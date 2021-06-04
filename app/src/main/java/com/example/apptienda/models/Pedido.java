
package com.example.apptienda.models;

import android.os.Parcelable;
import android.os.RemoteException;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.apptienda.helpers.App;
import com.example.apptienda.helpers.Callbacks.VolleyJSONArrayCallback;
import com.example.apptienda.helpers.Callbacks.VolleyJSONCallback;
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
    @SerializedName("id_usuario")
    @Expose
    private String idUsuario;
    @SerializedName("id_pedido")
    @Expose
    private String idPedido;
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
        this.idUsuario = ((String) in.readValue((String.class.getClassLoader())));
        this.idPedido = ((String) in.readValue((String.class.getClassLoader())));
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

    public Pedido(String idUsuario,String idPedido, String fechaCreacion, String precioTotal, String borrado, String enviado, String recibido) {
        super();
        this.idUsuario = idUsuario;
        this.idPedido = idPedido;
        this.fechaCreacion = fechaCreacion;
        this.precioTotal = precioTotal;
        this.borrado = borrado;
        this.enviado = enviado;
        this.recibido = recibido;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
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
        return "Pedido{" +
                "idUsuario='" + idUsuario + '\'' +
                ", idPedido='" + idPedido + '\'' +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", precioTotal='" + precioTotal + '\'' +
                ", borrado='" + borrado + '\'' +
                ", enviado='" + enviado + '\'' +
                ", recibido='" + recibido + '\'' +
                '}';
    }

    public static void obtenerPedidos(final VolleyJSONArrayCallback callback) throws ExecutionException, InterruptedException {
        CompletableFuture<ArrayList<Pedido>> future = new CompletableFuture<>();
        try {
            Gson gson=new Gson();
            JSONObject objetoPeticion = new JSONObject();
            objetoPeticion.put("id",DataRepository.getUsuarioLogeado().getId());
            objetoPeticion.put("action", "list_orders");
            String url = "http://pruebatiendadam.atwebpages.com/php/android/listener.php";
            RequestQueue queue = SingletonRequestQueue.getInstance(App.getContext()).getQueue();
            CustomJsonArrayRequest request = new CustomJsonArrayRequest(Request.Method.POST, url, objetoPeticion,
                    callback::onSuccessResponse
                    ,
                    callback::onErrorResponse);
            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void crearPedido(final VolleyJSONCallback callback) throws ExecutionException, InterruptedException {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        try {
            Gson gson=new Gson();
            JSONObject objetoPeticion = new JSONObject();
            objetoPeticion.put("action", "place_order");
            objetoPeticion.put("order",gson.toJson(this));
            String url = "http://pruebatiendadam.atwebpages.com/php/android/listener.php";
            RequestQueue queue = SingletonRequestQueue.getInstance(App.getContext()).getQueue();
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, objetoPeticion,
                    callback::onSuccessResponse
                    ,
                    callback::onErrorResponse);
            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idPedido, fechaCreacion, precioTotal, borrado, enviado, recibido, paquetes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(idUsuario, pedido.idUsuario) &&
                Objects.equals(idPedido, pedido.idPedido) &&
                Objects.equals(fechaCreacion, pedido.fechaCreacion) &&
                Objects.equals(precioTotal, pedido.precioTotal) &&
                Objects.equals(borrado, pedido.borrado) &&
                Objects.equals(enviado, pedido.enviado) &&
                Objects.equals(recibido, pedido.recibido) &&
                Objects.equals(paquetes, pedido.paquetes);
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(idUsuario);
        dest.writeValue(idPedido);
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
