package ar.com.semillero.semillatronalfa.servicios;

import ar.com.semillero.semillatronalfa.entidades.Autor;
import ar.com.semillero.semillatronalfa.entidades.Editorial;
import ar.com.semillero.semillatronalfa.errores.ErrorServicio;
import ar.com.semillero.semillatronalfa.repositorios.EditorialRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {

    private EditorialRepositorio editorialRepositorio;

    @Autowired
    public EditorialServicio(EditorialRepositorio editorialRepositorio) {
        this.editorialRepositorio = editorialRepositorio;
    }

    //
    public void registrar(String nombre) throws ErrorServicio {
        Editorial editorial = new Editorial();
        editorial.setNombre(validarNombre(nombre, null));
        editorial.setAlta(Boolean.TRUE);
        editorialRepositorio.save(editorial);
    }

    public void modificar(String id, String nombre) throws ErrorServicio {
        Editorial editorial = validarId(id);
        editorial.setNombre(validarNombre(nombre, id));
        editorialRepositorio.save(editorial);
    }

    public void eliminar(String id) throws ErrorServicio {
        Editorial editorial = validarId(id);
        editorial.setAlta(Boolean.FALSE);
        editorialRepositorio.save(editorial);
    }

    public void reactivar(String id) throws ErrorServicio {
        Editorial editorial = validarId(id);
        editorial.setAlta(Boolean.TRUE);
        editorialRepositorio.save(editorial);
    }

    public Editorial validarId(String id) throws ErrorServicio {
        Optional<Editorial> res = editorialRepositorio.findById(id);
        if (!res.isPresent()) {
            throw new ErrorServicio("No se encontro el editorial solicitado.");
        }
        return res.get();
    }

    public String validarNombre(String nombre, String id) throws ErrorServicio {
         if (nombre.toLowerCase() == null || nombre.isEmpty() || nombre.trim().isEmpty()) {
            throw new ErrorServicio("Debe ingresar un nombre de editorial.");
        }
        if (editorialRepositorio.buscarNombreEqual(nombre.toLowerCase()) != null && editorialRepositorio.buscarNombreEqual(nombre.toLowerCase()).getId() != id) {
            throw new ErrorServicio("Ya existe un editorial con el nombre seleccionado.");
        }
        return nombre.toLowerCase();
    }

    public Editorial validarExistenciaEditorial(String nombre) throws ErrorServicio {
        if (nombre.toLowerCase() == null || nombre.isEmpty() || nombre.trim().isEmpty()) {
            throw new ErrorServicio("Debe ingresar un nombre de editorial.");
        }
        if (editorialRepositorio.buscarNombreEqual(nombre.toLowerCase()) != null) {
            return editorialRepositorio.buscarNombreEqual(nombre.toLowerCase());
        }
        if (editorialRepositorio.buscarNombresLike(nombre.toLowerCase()).isEmpty()) {
            throw new ErrorServicio("No existe un editorial con el nombre seleccionado. Por favor ingrese el editorial e intente nuevamente");
        }

        if (editorialRepositorio.buscarNombresLike(nombre.toLowerCase()).size() == 1) {
            return editorialRepositorio.buscarNombresLike(nombre.toLowerCase()).get(0);
        }

        if (editorialRepositorio.buscarNombresLike(nombre.toLowerCase()).size() > 1 && editorialRepositorio.buscarNombresLike(nombre.toLowerCase()).size() < 11) {
            List<Editorial> editoriales = editorialRepositorio.buscarNombresLike(nombre.toLowerCase());
            System.out.println("editoriales size: " + editoriales.size());
            String tabOpciones = "Por favor ingrese un editorial de entre las siguientes opciones:";
//            Integer count = 0;
//            for (Editorial aux : editoriales) {
//                count += 1;
//                if (count > 1) {
//                    tabOpciones = tabOpciones.concat(" - ");
//                }
//                tabOpciones = tabOpciones.concat(count + ". " + aux.getNombre());
//            }
            throw new ErrorServicio(tabOpciones);
        }

        if (editorialRepositorio.buscarNombresLike(nombre.toLowerCase()).size() >= 11) {
            throw new ErrorServicio("Muchos editoriales tienen" + nombre + "como parte de su nombre. Por favor ingrese un nombre más específico.");
        }

        return editorialRepositorio.buscarNombreEqual(nombre.toLowerCase());
    }

    public List<String> enviarListaNombres(String nombre) {
        List<String> nombres = new ArrayList();
        List<Editorial> editoriales = editorialRepositorio.buscarNombresLike(nombre.toLowerCase());
        for (Editorial aux : editoriales) {
            System.out.println(aux.getNombre());
            nombres.add(aux.getNombre());
        }
        return nombres;
    }

    public Editorial buscarNombreEqual(String nombre) {
        return editorialRepositorio.buscarNombreEqual(nombre);
    }

    public List<Editorial> buscarNombresLike(String nombre) {
        return editorialRepositorio.buscarNombresLike(nombre);
    }

    public List<Editorial> buscarNombresInicial(String nombre) {
        return editorialRepositorio.buscarNombresInicial(nombre);
    }

}
