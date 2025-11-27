/*El anotador @Data de Lombok genera los siguientes métodos automáticamente para la clase
 anotada:

Constructor vacío (default())
Constructor con todos los campos (allArgsConstructor())
Método getter para cada campo (getter())
Método setter para cada campo (setter())
Método toString() que incluye todos los campos de la clase
Método equals() y hashCode() basados en todos los campos de la clase*/

package ar.edu.centro8.desarrollo.proyectosbon2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Dolar {
    
    private String moneda;
    private String casa;
    private String nombre;
    private Double compra;
    private Double venta;
    
    @JsonProperty("fechaActualizacion")
    private String fechaActualizacion;

}
