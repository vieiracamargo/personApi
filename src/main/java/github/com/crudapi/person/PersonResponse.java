package github.com.crudapi.person;

import github.com.crudapi.address.AddressResponse;

import java.time.LocalDate;
import java.util.List;

public record PersonResponse(
        Long id,
        String name,
        LocalDate birthDate,
        List<AddressResponse> addresses
) {
    public PersonResponse(Person person){
        this(
                person.getId(),
                person.getName(),
                person.getBirthDate(),
                person.getAddresses().stream().map(AddressResponse::new).toList()
        );

    }
}
