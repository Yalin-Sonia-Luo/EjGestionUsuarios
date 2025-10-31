package org.example.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entities.Usuario;

import java.util.List;

public class UserJPA {
    //registra el usuario
    public static void registrarUsuario(Usuario cliente){
        try (EntityManager em = ConfigJPA.getEntityMenager()){
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        }
    }

    //Hace una lista de los usuarios
    public static List <Usuario> listarUsuarios (){
        try (EntityManager em = ConfigJPA.getEntityMenager()){
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        }
    }

    //Actualiza el usuario
    public void actualizar (Usuario cliente){
        EntityManager em = ConfigJPA.getEntityMenager();
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
        em.close();
    }

    //Elimina el usuario
    public void eliminar (Long id){
        EntityManager em = ConfigJPA.getEntityMenager();
        em.getTransaction().begin();
        Usuario cliente = em.find(Usuario.class, id);
        if (cliente != null){
            em.remove(cliente);
        }else{
            System.out.println("Usuario invalido, introduzca la ID correcta");
        }
        em.getTransaction().commit();
        em.close();
    }

    //Busca por id
    public Usuario buscarPorId(Long id) {
        EntityManager em = ConfigJPA.getEntityMenager();
        Usuario cliente = em.find(Usuario.class, id);
        em.close();
        if (cliente == null) {
            System.out.println("Usuario inválido, introduzca la ID correcta");
        }
        return cliente;
    }

    //Busca por ciudad
    public List<Usuario> buscarPorCiudad (String ciudad) {
        EntityManager em = ConfigJPA.getEntityMenager();
        //(SELECT u FROM Usuario u WHERE u.ciudad = :ciudad) Permite selecionar todos objetos de tipo usuario
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM u cliente WHERE u.ciudad = :ciudad", Usuario.class);
        query.setParameter("ciudad", ciudad);
        // Asignar el valor al parámetro
        List<Usuario> cliente = query.getResultList();
        em.close();
        return cliente;
    }
}
