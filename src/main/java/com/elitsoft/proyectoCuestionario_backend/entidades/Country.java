
package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author ELITSOFT86
 */
@Entity
@Table(name = "countries")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name")
    private String name;

    public Country() {
    }


    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    @JsonIgnore
    @Column(name = "name")
    private List<State> states;


    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
