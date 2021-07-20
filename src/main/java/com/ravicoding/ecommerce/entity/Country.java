package com.ravicoding.ecommerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="country")
@Setter
@Getter
public class Country
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="country")  // mappedBy placed by the Variable where we join the column
    private List<State> states;

    // mappedBy placed by the Variable where we join the column (mappedBy ="country", @JoinColumn(name="country_id" private Country country;)
}
