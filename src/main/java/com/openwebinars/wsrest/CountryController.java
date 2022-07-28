package com.openwebinars.wsrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase controladora que atiende peticiones HTTP
 * Anotacion RestController: La respuesta a las peticiones se encuentra en el cuerpo del response
 * Anotaciones RequestMapping (GET, POST, PUT, DELETE, etc): Se asocia una ruta HTTP a un metodo controlador
 */
@RestController
public class CountryController {

    @Autowired
    private CountryRepository repository;//Bean del repository

    /**
     * Obtiene la lista de paises
     * Se transforma el formato java a formato json
     * @return {@link List}
     */
    @GetMapping("/country")
    public List<Country> getAllCountries() {
        return repository.findAll();
    }

    /**
     * Obtiene los datos del pais con el codigo recibido como parametro en la ruta
     * Si no encuentra ningun pais con ese codigo, devuelve un 404
     * @param code Codigo del pais
     * @return {@link List}
     */
    @GetMapping("/country/{code}")
    public ResponseEntity<Country> getByCode(@PathVariable String code) {
        return ResponseEntity.of(repository.findCountry(code));
    }

    /**
     * Agrega un pais al map
     * Devuelve codigo 201 indicando que el objeto se agrego con exito
     * @param country
     * @return {@link Country}
     */
    @PostMapping("/country")
    public ResponseEntity<Country> newCountry(@RequestBody Country country) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.addCountry(country));
    }
}
