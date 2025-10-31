package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //se crean las variables que necesitemos
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false, length = 1)
    private char sexo;
    @Column(nullable = false)
    private String ciudad;
    //Para guardar fechas
    @Column(nullable = false)
    private LocalDate nacimiento;
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false)
    private String correo;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String apellido, char sexo, String ciudad, String telefono, String correo, LocalDate nacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.ciudad = ciudad;
        this.nacimiento = nacimiento;
        this.telefono = telefono;
        this.correo = correo;
    }

    /***************Getters***************/
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public char getSexo() {
        return sexo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    /***************Setters***************/
    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override

    //Mensaje que muestra la informacion de los usuarios
    public String toString(){
        return  id + ": " + nombre + " " + apellido
                + " - " + ciudad + " - " + telefono
                + " - " + correo + " - " + nacimiento;
    }
}
