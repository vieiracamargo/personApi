package github.com.crudapi.address;

import github.com.crudapi.person.Person;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data


public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String neighborhood;
    @Column(nullable = false)
    private String zipCode;
    @Column(nullable = false)
    private Integer number;
    @Column(nullable = false)
    private String city;

    @Column(nullable = true)
    private Boolean isMainAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
}
