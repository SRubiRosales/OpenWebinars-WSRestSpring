package com.openwebinars.wsrest;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.*;

//Anotacion Repository indica que es un bean de spring de acceso orientado a datos
@Repository
public class CountryRepository {
    /**
     * Map de countries donde la clave es un String con el codigo del pais
     */
    private final Map<String, Country> countries = new HashMap<>();

    /**
     * Metodo init que se ejecuta en cuanto se instancia este bean para agregar paises de prueba
     */
    @PostConstruct
    public void init() {
        Country spain = new Country();
        spain.setCode("es");
        spain.setName("España");
        spain.setCapital("Madrid");
        spain.setCurrency(Currency.getInstance("EUR").getDisplayName());
        spain.setPopulation(47_326_687);

        Country portugal = new Country();
        portugal.setCode("pt");
        portugal.setName("Portugal");
        portugal.setCapital("Lisboa");
        portugal.setCurrency(Currency.getInstance("EUR").getDisplayName());
        portugal.setPopulation(10_298_252);

        Country mexico = new Country();
        mexico.setCode("mx");
        mexico.setName("México");
        mexico.setCapital("Ciudad de México");
        mexico.setCurrency(Currency.getInstance("MXN").getDisplayName());
        mexico.setPopulation(113_326_687);

        countries.put(spain.getCode(), spain);
        countries.put(portugal.getCode(), portugal);
        countries.put(mexico.getCode(), mexico);
    }

    /**
     * Metodo que lista todos los paises del map
     * @return {@link List}
     */
    public List<Country> findAll() {
        return new ArrayList<>(countries.values());
    }

    /**
     * Metodo que encuentra al pais con el codigo recibido como parametro
     * @param code Codigo del pais
     * @return {@link Country}
     */
    public Optional<Country> findCountry(String code) {
        Assert.notNull(code, "El código del país no puede ser nulo");
        Country result = countries.get(code);
        return result != null ? Optional.of(result) : Optional.empty();
    }

    /**
     * Metodo que agrega un pais al map
     * @param country Objeto country que se agrega al map
     * @return {@link Country}
     */
    public Country addCountry(Country country) {
        country.setCurrency(Currency.getInstance(country.getCurrency()).getDisplayName());
        countries.putIfAbsent(country.getCode(), country);
        return country;
    }
}
