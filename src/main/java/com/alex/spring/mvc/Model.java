package com.alex.spring.mvc;

import lombok.Data;

/**
 * Created by gaojun on 16/3/21.
 */
@Data
public class Model {

    private Integer id;

    private String name;

    public Model(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}
