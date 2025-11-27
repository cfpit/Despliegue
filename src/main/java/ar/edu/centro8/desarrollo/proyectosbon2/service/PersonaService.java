package ar.edu.centro8.desarrollo.proyectosbon2.service;


import ar.edu.centro8.desarrollo.proyectosbon2.model.Persona;
import ar.edu.centro8.desarrollo.proyectosbon2.repository.PersonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> buscarPorNombre(String nombre) {
        return personaRepository.findByNombreContaining(nombre);
    }

    public List<Persona> buscarPersonasPorNombreSimilar(String nombre) {
        return personaRepository.findByNombreLike(nombre);
    }

    
}
