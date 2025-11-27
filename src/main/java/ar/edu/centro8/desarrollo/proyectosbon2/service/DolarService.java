package ar.edu.centro8.desarrollo.proyectosbon2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ar.edu.centro8.desarrollo.proyectosbon2.model.Dolar;

import org.springframework.http.ResponseEntity;

@Service
public class DolarService {

    // Inyecta el valor de dolar.api.url desde application.properties
    @Value("${dolar.api.url}")
    private String dolarApiUrl;

    //La clase de Spring RestTemplate permite realizar 
    //solicitudes HTTP a APIs externas
    @Autowired
    private RestTemplate restTemplate;

    public Dolar obtenerDolar() {
        ResponseEntity<Dolar> response = restTemplate.getForEntity(dolarApiUrl, Dolar.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Error al obtener el tipo de cambio del dólar");
        }
    }

}
