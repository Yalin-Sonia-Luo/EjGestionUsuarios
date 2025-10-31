package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.example.entities.Usuario;
import org.example.persistence.UserJPA;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Usuario> cliente = new LinkedList<>();

        //Creamos Entity ManagerFactory y EntityMenager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PuClientes");
        EntityManager em = emf.createEntityManager();
        int opcion;

            while (true) {
                mostrarMenu();
                opcion = input.nextInt();
                input.nextLine();
                switch (opcion){

                /******************** Cerrar ********************/
                case 0:
                    System.out.println("Cerrando programa");
                    em.close();
                    emf.close();
                    return;

                /******************** Crear ********************/
                case 1:
                    /******************** Nombre ********************/
                    System.out.println("Introduzca el nombre");
                    String nombre = input.nextLine();
                    //Mientras que el nombre esté vacio imprimirá el sout
                    while (nombre.isEmpty() || nombre.matches(".*\\d.*")) {
                        if (nombre.isEmpty()) {
                            System.out.println("El nombre es obligatorio, introdúzcalo nuevamente");
                        } else {
                            System.out.println("El nombre no puede contener números, introdúzcalo nuevamente");
                        }
                        nombre = input.nextLine();
                    }

                    /******************** Apellido ********************/
                    System.out.println("Introduzca los apellidos");
                    String apellido = input.nextLine();
                    while (apellido.isEmpty() || apellido.matches(".*\\d.*")) {
                        if (apellido.isEmpty()) {
                            System.out.println("El apellido es obligatorio, introdúzcalo nuevamente");
                        } else {
                            System.out.println("El apellido no puede contener números, introdúzcalo nuevamente");
                        }
                        apellido = input.nextLine();
                    }


                    /******************** Sexo ********************/
                    String sexoStr = "";
                    char sexo;
                    System.out.println("Introduzca el sexo (M/F):");
                    do {
                        sexoStr = input.nextLine().trim().toUpperCase();

                        if (sexoStr.isEmpty()) {
                            System.out.println("El sexo es obligatorio, introdúzcalo nuevamente");
                        }else if (!(sexoStr.equals("M") || sexoStr.equals("F"))){
                            System.out.println("Debe introducir una opción valida M (Masculino) / F (Femenino)");
                        }
                    //repetirá el proceso mientras no cumpla los requisitos
                    } while (sexoStr.isEmpty() || !(sexoStr.equals("M") || sexoStr.equals("F")));
                    sexo = sexoStr.charAt(0);


                    /******************** Ciudad ********************/
                    System.out.println("Introduzca la ciudad");
                    String ciudad = input.nextLine();

                    while (ciudad.isEmpty() || ciudad.matches(".*\\d.*")) {
                        if (ciudad.isEmpty()) {
                            System.out.println("La ciudad es obligatoria, introdúzcalo nuevamente");
                        } else {
                            System.out.println("La ciudad no puede contener números, introdúzca nuevamente");
                        }
                        ciudad = input.nextLine();
                    }


                    /******************** Telefono ********************/
                    System.out.println("Introduzca el telefono");
                    String telefono = input.nextLine();
                    while (telefono.isEmpty() || !telefono.matches("\\d+")) {
                        if (telefono.isEmpty()) {
                            System.out.println("El telefono es obligatorio, introdúzcalo nuevamente");
                        } else {
                            System.out.println("El telefono no puede contener letras, introdúzcalo nuevamente");
                        }
                        telefono = input.nextLine();
                    }


                    /******************** Correo ********************/
                    String correo = "";
                    System.out.println("Introduzca el correo:");
                    do {
                        correo = input.nextLine().trim();

                        if (correo.isEmpty()) {
                            System.out.println("El correo es obligatorio, introdúzcalo nuevamente:");
                        } else if (!correo.contains("@") || !correo.contains(".")) {
                            System.out.println("Introduzca un correo válido (Debe contener '@' y '.'):");
                            correo = "";
                        }

                    } while (correo.isEmpty());


                    /******************** Nacimiento ********************/
                    LocalDate nacimiento = null;
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    System.out.println("Introduzca la fecha de nacimiento (dd-MM-yyyy): ");
                    do {
                        String fechaStr = input.nextLine().trim();
                        //Comprueba si sigue el formato indicado, si no lanzará la excepcion.
                        if (fechaStr.isEmpty()) {
                            System.out.println("La fecha es obligatoria, introdúzcala nuevamente");
                            continue;
                        }
                        try {
                            nacimiento = LocalDate.parse(fechaStr, formato);
                            //Si el nacimiento es posterior a la fecha actual, repite el bucle
                            if (nacimiento.isAfter(LocalDate.now())) {
                                System.out.println("¿Quieres viajar al futuro? Introduzca una fecha correcta, que no eres un viajero temporal");
                                nacimiento = null;
                            }
                            //Si no se cumple el formato repite el bucle
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato inválido, use dd-MM-yyyy (día-mes-año)");
                        }
                    } while (nacimiento == null);

                    Usuario usuario = new Usuario(null, nombre, apellido, sexo, ciudad, telefono, correo, nacimiento);


                    em.getTransaction().begin();
                    em.persist(usuario);
                    em.getTransaction().commit();
                    
                    System.out.println("Usuario agregado correctamente:");
                    System.out.println(
                            "-----------------------------------" +
                                    "\nNombre: " + nombre +
                                    "\nApellido: " + apellido +
                                    "\nSexo: " + sexo +
                                    "\nCiudad: " + ciudad +
                                    "\nTeléfono: " + telefono +
                                    "\nCorreo: " + correo +
                                    "\nFecha de nacimiento: " + nacimiento.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                                    "\n-----------------------------------"
                    );
                    break;

                    /******************** Listar ********************/
                case 2:
                    //Llama a la funcion de listar usuarios y en funcion de esta muestra los usuarios o el mensaje
                    List<Usuario> usuarios = UserJPA.listarUsuarios();

                    if (usuarios.isEmpty()) {
                        System.out.println("No hay usuarios registrados.");
                    } else {
                        System.out.println("Lista de usuarios:");
                        for (Usuario u : usuarios) {

                            System.out.println(u);
                        }
                    }
                    break;

                /******************** Modificar ********************/
                    case 3:
                        System.out.println("Ingrese el ID del usuario que quiera modificar");
                        Long idModificar = Long.parseLong(input.nextLine());
                        Usuario usuarioModificar = em.find(Usuario.class, idModificar);

                        //Si el numero de ID introducido no se encuentra devuelve null
                        if (usuarioModificar == null) {
                            System.out.println("Usuario no encontrado.");
                            break;
                        }else if (usuarioModificar != null) {


                            /******************** Nombre ********************/
                            System.out.println("Nombre actual: " + usuarioModificar.getNombre());
                            System.out.println("    - Introduzca el nuevo nombre, si no desea editar pulse enter: ");
                            String nuevoNombre = input.nextLine();if (!nuevoNombre.isEmpty()) {
                                if (nuevoNombre.matches(".*\\d.*")) {
                                    System.out.println("El nombre no puede contener números. No se realizaron cambios.");
                                } else {
                                    usuarioModificar.setNombre(nuevoNombre);
                                    System.out.println("nombre modificado correctamente.");
                                }
                            } else {
                                System.out.println("El nombre no fue modificado (se mantiene el anterior).");
                            }


                            /******************** Apellidos ********************/
                            System.out.println("Apellido actual: " + usuarioModificar.getApellido());
                            System.out.println("    - Introduzca el nuevo apellido, si no desea editar pulse enter: ");
                            String nuevosApellidos = input.nextLine();
                            if (!nuevosApellidos.isEmpty()) {
                                if (nuevosApellidos.matches(".*\\d.*")) {
                                    System.out.println("El apellido no puede contener números. No se realizaron cambios.");
                                } else {
                                    usuarioModificar.setApellido(nuevosApellidos);
                                    System.out.println("Apellido modificado correctamente.");
                                }
                            } else {
                                System.out.println("El apellido no fue modificado (se mantiene el anterior).");
                            }


                            /******************** Sexo ********************/
                            System.out.println("Sexo actual: " + usuarioModificar.getSexo());
                            System.out.println("    - Introduzca el nuevo sexo, si no desea editar pulse enter: ");
                            String nuevoSexoStr = input.nextLine().trim().toUpperCase();
                            if (!nuevoSexoStr.isEmpty() && (nuevoSexoStr.charAt(0) == 'M' || nuevoSexoStr.charAt(0) == 'F')) {
                                usuarioModificar.setSexo(nuevoSexoStr.charAt(0));
                            }


                            /******************** Ciudad ********************/
                            System.out.println("Ciudad actual: " + usuarioModificar.getCiudad());
                            System.out.println("    - Introduzca ela nueva ciudad, si no desea editar pulse enter: ");
                            String nuevaCiudad = input.nextLine();
                            if (!nuevaCiudad.isEmpty()) {
                                if (nuevaCiudad.matches(".*\\d.*")) {
                                    System.out.println("La ciudad no puede contener números. No se realizaron cambios.");
                                } else {
                                    usuarioModificar.setCiudad(nuevosApellidos);
                                    System.out.println("Ciudad modificada correctamente.");
                                }
                            } else {
                                System.out.println("La ciudad no fue modificado (se mantiene el anterior).");
                            }


                            /******************** Fecha de nacimiento ********************/
                            DateTimeFormatter formatoNacimiento = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            System.out.println("Fecha de nacimiento actual: " + usuarioModificar.getNacimiento().format(formatoNacimiento));
                            System.out.println("    - Introduzca la nueva fecha de nacimiento, si no desea editar pulse enter: ");
                            String nuevaFechaStr = input.nextLine();
                            if (!nuevaFechaStr.isEmpty()) {
                                //Comprueba si sigue el formato indicado, si no lanzará la excepcion.
                                try {
                                    LocalDate nuevaFecha = LocalDate.parse(nuevaFechaStr, formatoNacimiento);
                                    //Comprueba si introduce una fecha posterior a la actual.
                                    if (!nuevaFecha.isAfter(LocalDate.now())) {
                                        usuarioModificar.setNacimiento(nuevaFecha);
                                    } else {
                                        System.out.println("La fecha no puede ser futura. Se mantiene la original.");
                                    }
                                } catch (DateTimeParseException e) {
                                    System.out.println("Formato inválido. Se mantiene la fecha original.");
                                }
                            }


                            /******************** Teléfono ********************/
                            System.out.println("Numero de telefono actual: " + usuarioModificar.getTelefono());
                            System.out.println("    - Introduzca el nuevo numero de telefono, si no desea editar pulse enter: ");
                            String nuevoTelefono = input.nextLine();
                            if (!nuevoTelefono.isEmpty()) {
                                if (nuevoTelefono.matches("\\d+")) { // Solo dígitos
                                    usuarioModificar.setTelefono(nuevoTelefono);
                                    System.out.println("Teléfono modificado correctamente.");
                                } else {
                                    System.out.println("El teléfono solo puede contener números. No se realizaron cambios.");
                                }
                            } else {
                                System.out.println("El teléfono no fue modificado (se mantiene el anterior).");
                            }


                            /******************** Correo ********************/
                            System.out.println("Correo actual: " + usuarioModificar.getCorreo());
                            System.out.println("    - Introduzca el nuevo correo, si no desea editar pulse enter: ");
                            String nuevoEmail = input.nextLine();
                            if (!nuevoEmail.isEmpty()) {
                                if (nuevoEmail.contains("@") && nuevoEmail.contains(".")) {
                                    usuarioModificar.setCorreo(nuevoEmail);
                                    System.out.println("Correo modificado correctamente.");
                                } else {
                                    System.out.println("Correo inválido. Se mantiene el original.");
                                }
                            } else {
                                System.out.println("El correo no fue modificado (se mantiene el anterior).");
                            }


                            /******************** Guardar cambios ********************/
                            em.getTransaction().begin();
                            em.merge(usuarioModificar);
                            em.getTransaction().commit();

                            System.out.println("Usuario actualizado correctamente: ");
                            System.out.println(usuarioModificar);

                        }
                        break;

                /******************** Eliminar ********************/
                case 4:
                    System.out.print("Dame el ID del usuario: ");
                    Long idUser = input.nextLong();
                    input.nextLine(); // limpia el buffer

                    // Buscar el usuario con la ID indicada en la base de datos
                    Usuario usuarioEliminar = em.find(Usuario.class, idUser);
                    if (usuarioEliminar != null) {
                        em.getTransaction().begin();
                        em.remove(usuarioEliminar);
                        em.getTransaction().commit();
                        System.out.println("Usuario eliminado correctamente de la base de datos.");

                        List<Usuario> usuariosActualizados = UserJPA.listarUsuarios();
                        if (usuariosActualizados.isEmpty()) {
                            System.out.println("No hay usuarios registrados.");
                        } else {
                            System.out.println("Lista actualizada de usuarios:");
                            for (Usuario u : usuariosActualizados) {
                                System.out.println(u);
                            }
                        }
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;

                /******************** Buscar ********************/
                case 5:
                    System.out.print("Ingrese la ciudad: ");
                    String ciudadBuscar = input.nextLine();
                    /*Permite selecionar todos objetos de tipo usuario, convierte las ciudadesa guardas a minusculas,
                    comparando que sea igual a la ciuda introducida
                    ("SELECT u FROM Usuario u WHERE LOWER(u.ciudad) = :ciudad")*/
                    TypedQuery<Usuario> queryCiudad = em.createQuery("SELECT u FROM Usuario u WHERE LOWER(u.ciudad) = :ciudad", Usuario.class);
                    queryCiudad.setParameter("ciudad", ciudadBuscar.toLowerCase());

                    List<Usuario> usuariosCiudad = queryCiudad.getResultList();
                    if (usuariosCiudad.isEmpty()) {
                        System.out.println("No se encontraron usuarios en esta ciudad.");
                    }else{
                        usuariosCiudad.forEach(System.out::println);
                    }
                    break;

                /******************** Otros ********************/
                default:
                    System.out.println("Introduzca una opcion valida");
                    break;
            }
        }
    }
    private static void mostrarMenu() {
        System.out.println("\nGestión de usuarios");
        System.out.println("0 - Salir");
        System.out.println("1 - Crear usuario");
        System.out.println("2 - Listar usuarios");
        System.out.println("3 - Modificar usuario");
        System.out.println("4 - Eliminar usuario");
        System.out.println("5 - Buscar usuarios por ciudad");
    }
}