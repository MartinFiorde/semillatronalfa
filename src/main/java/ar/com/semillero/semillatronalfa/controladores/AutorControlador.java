package ar.com.semillero.semillatronalfa.controladores;

import ar.com.semillero.semillatronalfa.entidades.Autor;
import ar.com.semillero.semillatronalfa.errores.ErrorServicio;
import ar.com.semillero.semillatronalfa.repositorios.AutorRepositorio;
import ar.com.semillero.semillatronalfa.servicios.AutorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/a")
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
public class AutorControlador {

    private AutorRepositorio autorRepositorio;
    private AutorServicio autorServicio;

    @Autowired
    public AutorControlador(AutorRepositorio autorRepositorio, AutorServicio autorServicio) {
        this.autorRepositorio = autorRepositorio;
        this.autorServicio = autorServicio;
    }

    //
    @GetMapping
    public String listarAutores(ModelMap model, @RequestParam(required = false) List<Autor> autores, @RequestParam(required = false) String id) {
//        List<Autor> autores = null;
        if (autores == null || autores.size() == 0) {
            autores = autorRepositorio.findAll();
        }
        model.addAttribute("autores", autores);
        return "autor-t/lista.html";
    }

    @PostMapping
    public String listarBusqueda(ModelMap model, @RequestParam(required = false) String nombre) {
        List<Autor> autores = null;
        if (nombre != null) {
            autores = autorRepositorio.buscarNombresLike(nombre);
        } else {
            autores = autorRepositorio.findAll();
        }
        model.addAttribute("autores", autores);
        return "autor-t/lista.html";
    }

    @GetMapping("/form")
    public String mostrarFormAutor(ModelMap model, @RequestParam(required = false) String id, @RequestParam(required = false) String nombre) {
        model.put("id", id);
        model.put("nombre", nombre);
        return "autor-t/formulario.html";
    }

    @PostMapping("/activar")
    public String activar(@RequestParam(required = false) String id) throws ErrorServicio {
        autorServicio.reactivar(id);
        return "redirect:/a";
    }

    @PostMapping("/desactivar")
    public String desactivar(@RequestParam(required = false) String id) throws ErrorServicio {
        autorServicio.eliminar(id);
        return "redirect:/a";
    }

    @PostMapping("/form1")
    public String precargarFormAutor(ModelMap model, @RequestParam(required = false) String id, @RequestParam(required = false) String nombre) {
        model.put("id", id);
        model.put("nombre", nombre);
        return "autor-t/formulario.html";
    }

    @PostMapping("/form2")
    public String guardarFormAutor(ModelMap model, @RequestParam String nombre, @RequestParam String apellido, @RequestParam(required = false) String id) throws ErrorServicio {
        try {
            System.out.println(id);
            if (id == null || id.isEmpty() || id == "") {
                System.out.println("regis");
                autorServicio.registrar(nombre, apellido);
            } else {
                System.out.println("mod");
                autorServicio.modificar(id, nombre, apellido);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            model.put("error", ex.getMessage());
            model.put("nombre", nombre);
            model.put("apellido", apellido);
            return "autor-t/formulario.html";
//            return "redirect:/a/form";
        }
        return "redirect:/a";
    }

    @GetMapping("/search")
    public String mostrarBuscadorAutores(ModelMap model) {
        return "autor-t/buscador.html";
    }

    @PostMapping("/search")
    public String buscarAutoresguardarFormAutor(ModelMap model, @RequestParam String nombre, @RequestParam String apellido) throws ErrorServicio {
        try {
            autorServicio.registrar(nombre, apellido);
        } catch (Exception ex) {
            System.out.println(ex);
            model.put("nombre", nombre);
            model.put("apellido", apellido);
            return "redirect:/a/search";
        }

        return "redirect:/a";
    }
}
