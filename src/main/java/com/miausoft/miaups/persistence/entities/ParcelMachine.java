package com.miausoft.miaups.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miausoft.miaups.converter.AddressConverter;
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
public class ParcelMachine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    public long id;

    @Convert(converter = AddressConverter.class)
    @Column(nullable = false)
    private Address address;

    @Column(name = "lockers_count", nullable = false)
    private Integer lockersCount;

    @Column(nullable = false)
    private Integer availableLockersCount;

    @OneToMany(mappedBy = "parcelMachine")
    private Set<ParcelMachineLocker> lockers;

    public void increaseAvailableLockersCount(){
        if(availableLockersCount + 1 <= lockersCount){
            availableLockersCount++;
        }else{
            throw new RuntimeException("AvailableLockersCount can't be higher than LockersCount");
        }
    }
    public void decreaseAvailableLockersCount(){
        if(availableLockersCount + 1 >= 0){
            availableLockersCount--;
        }else{
            throw new RuntimeException("AvailableLockersCount can't be negative");
        }
    }
}
