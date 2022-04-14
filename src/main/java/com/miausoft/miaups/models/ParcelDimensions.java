package com.miausoft.miaups.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="ParcelDimensions")
public class ParcelDimensions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;

    @NotNull
    @Column(name="Label")
    private String label;

    @NotNull
    @Column(name="Height")
    private float height;

    @NotNull
    @Column(name="Width")
    private float width;

    @NotNull
    @Column(name="Length")
    private float length;

    @NotNull
    @Column(name="Price")
    private float price;

    @JsonIgnore
    @OneToMany(mappedBy = "dimensions")
    private Set<Parcel> parcels;
}
