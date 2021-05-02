package com.example.apptienda.model;

import android.provider.ContactsContract;

import java.util.Objects;

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
    public static boolean RegistrarUsuario(Usuario usuario,String password) {
        boolean result=false;

        return  result;
    }
    public static boolean ModificarUsuario() {
        boolean result=false;

        return  result;
    }
}
