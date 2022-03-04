package com.tms.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "city_table")
public class CityEntity {

    @Id
    @Column(name = "id_city")
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private Integer id;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "info_city")
    private String info;

    public CityEntity() {
    }

}
