package ar.edu.centro8.desarrollo.proyectosbon2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.centro8.desarrollo.proyectosbon2.model.Persona;
import ar.edu.centro8.desarrollo.proyectosbon2.repository.PersonaRepository;
import ar.edu.centro8.desarrollo.proyectosbon2.service.PersonaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class PersonaController {

    @Autowired
    private PersonaRepository persoRepo;

    @Autowired
    private PersonaService personaService;

    @GetMapping("/persona/traer")
    public List<Persona> traerPersonas() {
        return persoRepo.findAll();
    }

    @GetMapping("/persona/traer/{id}")
    public Optional<Persona> traerUnaPersona(@PathVariable Long id) {
        return persoRepo.findById(id);
    }

    @PostMapping("/persona/crear")
    public void crearPersona(@RequestBody Persona p) {
        persoRepo.save(p);
    }

    @DeleteMapping("/persona/borrar/{id}")
    public String borrarUnaPersona(@PathVariable Long id) {
        persoRepo.deleteById(id);
        return "persona eliminada correctamente";
    }

    @PutMapping("/persona/actualizar/{id}")
    public String actualizarUnaPersona(@PathVariable Long id, @RequestBody Persona p) {

        Persona personaBuscada = persoRepo.findById(id).get();

        personaBuscada.setNombre(p.getNombre());
        personaBuscada.setEdad(p.getEdad());

        persoRepo.save(personaBuscada);

        return "Datos de la persona actualizada correctamente";
    }


    @GetMapping("persona/buscar/{nombre}")
    public ResponseEntity<List<Persona>> buscarPorNombre(@PathVariable String nombre) {
        List<Persona> personas = personaService.buscarPorNombre(nombre);
        return ResponseEntity.ok(personas);
    }

    @GetMapping("persona/buscarSimilar/{cadena}")
    public ResponseEntity<List<Persona>> buscarPorNombreSimilar(@PathVariable String cadena) {
        List<Persona> personas = personaService.buscarPersonasPorNombreSimilar(cadena);
        return ResponseEntity.ok(personas);
    }

}
