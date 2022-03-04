package com.tms.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class CityEntity {

    @Id
//    @GenericGenerator(name = "generator", strategy = "increment")
//    @GeneratedValue(generator = "generator")
    private String cityName; //id будет являться имя, так можно?
    private String info;

    public CityEntity() {
    }

}
