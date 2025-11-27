/* 
Named Query:
Definida en la clase entidad usando @NamedQuery o @NamedNativeQuery
Se referencia por nombre único
Ejemplo:
@NamedQuery(name="Persona.findByNombre", query="SELECT p FROM Persona p WHERE LOWER(p.nombre) LIKE LOWER(?1)")



Query Anotada (@Query): Es mas eficiente.
Definida en la interfaz de repositorio usando @Query
Se ejecuta directamente en el método del repositorio
Ejemplo:
@Query("SELECT p FROM Persona p WHERE LOWER(p.nombre) LIKE LOWER(?1)")
List<Persona> findByNombreContaining(String nombre);

 

*/

package ar.edu.centro8.desarrollo.proyectosbon2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.centro8.desarrollo.proyectosbon2.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    @Query("SELECT p FROM Persona p WHERE LOWER(p.nombre) LIKE LOWER(?1)")
    List<Persona> findByNombreContaining(String nombre);

    @Query("SELECT p FROM Persona p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT(:nombre, '%'))")
    List<Persona> findByNombreLike(@Param("nombre") String nombre);
}
