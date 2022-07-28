package com.openwebinars.wsrest;

import lombok.Data;

//Anotacion data autogenera getters y setters
@Data
public class Country {
    private String code;
    private String name;
    private String currency;
    private String capital;
    private int population;
}
