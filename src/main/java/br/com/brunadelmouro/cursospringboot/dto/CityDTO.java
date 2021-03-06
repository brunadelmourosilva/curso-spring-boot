package br.com.brunadelmouro.cursospringboot.dto;

import br.com.brunadelmouro.cursospringboot.domain.City;
import br.com.brunadelmouro.cursospringboot.domain.State;

import java.io.Serializable;

public class CityDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    public CityDTO() {
    }

    public CityDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CityDTO(City obj) {
        id = obj.getId();
        name = obj.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
