package com.example.apptienda.models;



import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class Producto implements Parcelable
    {
        //Atribtos de la clase
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("nombre")
        @Expose
        private String nombre;
        @SerializedName("descripcion")
        @Expose
        private String descripcion;
        @SerializedName("categoria")
        @Expose
        private String categoria;
        @SerializedName("img")
        @Expose
        private String img;
        @SerializedName("borrado")
        @Expose
        private String borrado;
        public final static Creator<Producto> CREATOR = new Creator<Producto>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Producto createFromParcel(android.os.Parcel in) {
                return new Producto(in);
            }

            public Producto[] newArray(int size) {
                return (new Producto[size]);
            }

        }
                ;

        protected Producto(android.os.Parcel in) {
            this.id = ((String) in.readValue((String.class.getClassLoader())));
            this.nombre = ((String) in.readValue((String.class.getClassLoader())));
            this.descripcion = ((String) in.readValue((String.class.getClassLoader())));
            this.categoria = ((String) in.readValue((String.class.getClassLoader())));
            this.img = ((String) in.readValue((String.class.getClassLoader())));
            this.borrado = ((String) in.readValue((String.class.getClassLoader())));
        }

        /**
         * Constructor sin argumentos
         *
         */
        public Producto() {
        }

        /**
         *
         * @param descripcion
         * @param img
         * @param categoria
         * @param borrado
         * @param id
         * @param nombre
         * Constructor con todos los atributos de la clase
         */
        public Producto(String id, String nombre, String descripcion, String categoria, String img, String borrado) {
            super();
            this.id = id;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.categoria = categoria;
            this.img = img;
            this.borrado = borrado;
        }
        //Metodos gety set de todos los atributos de la clase
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

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
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
        //Metodo toString de la clase
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(Producto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("nombre");
            sb.append('=');
            sb.append(((this.nombre == null)?"<null>":this.nombre));
            sb.append(',');
            sb.append("descripcion");
            sb.append('=');
            sb.append(((this.descripcion == null)?"<null>":this.descripcion));
            sb.append(',');
            sb.append("categoria");
            sb.append('=');
            sb.append(((this.categoria == null)?"<null>":this.categoria));
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
        //Metodo hascode y equals para comparaciones entre objetos
        @Override
        public int hashCode() {
            int result = 1;
            result = ((result* 31)+((this.descripcion == null)? 0 :this.descripcion.hashCode()));
            result = ((result* 31)+((this.img == null)? 0 :this.img.hashCode()));
            result = ((result* 31)+((this.categoria == null)? 0 :this.categoria.hashCode()));
            result = ((result* 31)+((this.borrado == null)? 0 :this.borrado.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.nombre == null)? 0 :this.nombre.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof Producto) == false) {
                return false;
            }
            Producto rhs = ((Producto) other);
            return (((((((this.descripcion == rhs.descripcion)||((this.descripcion!= null)&&this.descripcion.equals(rhs.descripcion)))&&((this.img == rhs.img)||((this.img!= null)&&this.img.equals(rhs.img))))&&((this.categoria == rhs.categoria)||((this.categoria!= null)&&this.categoria.equals(rhs.categoria))))&&((this.borrado == rhs.borrado)||((this.borrado!= null)&&this.borrado.equals(rhs.borrado))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.nombre == rhs.nombre)||((this.nombre!= null)&&this.nombre.equals(rhs.nombre))));
        }

        public void writeToParcel(android.os.Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(nombre);
            dest.writeValue(descripcion);
            dest.writeValue(categoria);
            dest.writeValue(img);
            dest.writeValue(borrado);
        }

        public int describeContents() {
            return 0;
        }

    }




