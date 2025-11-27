package ar.edu.centro8.desarrollo.proyectosbon2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "personas")
@Getter @Setter
@NoArgsConstructor
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    private int edad;

    //constructores
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

}
