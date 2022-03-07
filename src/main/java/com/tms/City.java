package com.tms;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class City {
    @Id
    private String name;
    private String info;

    public City() {
    }
}
