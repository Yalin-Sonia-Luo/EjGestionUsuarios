package org.example.controller;

import org.example.entities.Usuario;
import org.example.persistence.UserJPA;

import java.util.List;

//Controlador que maneja la lógica de negocio de la aplicación
public class ClienteController{
    private final UserJPA ClienteJPA = new UserJPA();
    public void registrarUsuario(Usuario usuario) {
        ClienteJPA.registrarUsuario(usuario);
    }

    public List<Usuario> listarClientes() {
        return ClienteJPA.listarUsuarios();
    }

    public void actualizarCliente(Usuario cliente) {
        ClienteJPA.actualizar(cliente);
    }

    public void eliminarCliente(Long id) {
        ClienteJPA.eliminar(id);
    }

    public Usuario buscarPorId(Long id) {
        return ClienteJPA.buscarPorId(id);
    }

    public List<Usuario> buscarPorCiudad(String ciudad) {
        return ClienteJPA.buscarPorCiudad(ciudad);
    }
}

