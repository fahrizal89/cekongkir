package com.fahrizal.cekongkir.domain;

import lombok.Data;

/**
 * Class that represents a Province in the domain layer.
 */
@Data
public class Province {

    private final int id;

    private String name;

    public Province(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
