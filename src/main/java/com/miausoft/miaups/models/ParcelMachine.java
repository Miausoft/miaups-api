package com.miausoft.miaups.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="ParcelMachine")
public class ParcelMachine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;

    @NotNull
    @Column(name="Address")
    private String address;

    @NotNull
    @Column(name="LockersCount")
    private Integer lockersCount;

    @JsonIgnore
    @OneToMany(mappedBy = "parcelMachine")
    private Set<ParcelMachineLocker> lockers;
}
