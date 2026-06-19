package integrador.prog2.services;

import integrador.prog2.entities.Usuario;
import integrador.prog2.exceptions.EntidadNoEncontradaException;
import integrador.prog2.exceptions.MailDuplicadoException;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private List<Usuario> usuarios;

    public UsuarioService() {
        this.usuarios = new ArrayList<>();
    }

    // LISTAR SOLO USUARIOS ACTIVOS
    public List<Usuario> listar() {

        List<Usuario> activos = new ArrayList<>();

        for (Usuario usuario : usuarios) {

            if (!usuario.isEliminado()) {
                activos.add(usuario);
            }

        }

        return activos;
    }

    // CREAR
    public void crear(Usuario usuario) throws MailDuplicadoException {

        for (Usuario usr : usuarios) {

            if (!usr.isEliminado()
                    && usr.getMail().equalsIgnoreCase(usuario.getMail())) {

                throw new MailDuplicadoException(
                        "Ya existe un usuario con ese mail."
                );
            }

        }

        usuarios.add(usuario);
    }

    // BUSCAR POR ID
    public Usuario buscarPorId(Long id)
            throws EntidadNoEncontradaException {

        for (Usuario usuario : usuarios) {

            if (usuario.getId().equals(id)
                    && !usuario.isEliminado()) {

                return usuario;
            }

        }

        throw new EntidadNoEncontradaException(
                "Usuario no encontrado."
        );
    }

    // EDITAR
    public void editar(Long id,
                       String nombre,
                       String apellido,
                       String mail,
                       String celular,
                       String contraseña)
            throws EntidadNoEncontradaException,
            MailDuplicadoException {

        Usuario usuario = buscarPorId(id);

        // Validar mail duplicado
        for (Usuario usr : usuarios) {

            if (!usr.getId().equals(id)
                    && !usr.isEliminado()
                    && usr.getMail().equalsIgnoreCase(mail)) {

                throw new MailDuplicadoException(
                        "Ya existe un usuario con ese mail."
                );
            }

        }

        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setMail(mail);
        usuario.setCelular(celular);
        usuario.setContraseña(contraseña);
    }

    // ELIMINAR (BAJA LÓGICA)
    public void eliminar(Long id)
            throws EntidadNoEncontradaException {

        Usuario usuario = buscarPorId(id);

        usuario.setEliminado(true);
    }

}