package github.com.crudapi.person;

import github.com.crudapi.address.Address;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate birthDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person", orphanRemoval = true)
    private List<Address> addresses;

    public void addAddress(Address address){
        this.addresses.add(address);
    }
}
