package ar.edu.centro8.desarrollo.proyectosbon2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.desarrollo.proyectosbon2.model.Dolar;
import ar.edu.centro8.desarrollo.proyectosbon2.service.DolarService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class DolarController {

    @Autowired
    private DolarService dolarService;

    @GetMapping("/dolar")
    @ResponseBody
    public Dolar obtenerDolar() {
        return dolarService.obtenerDolar();
    }

    
}
