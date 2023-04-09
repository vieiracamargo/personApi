package github.com.crudapi.person;

import github.com.crudapi.address.Address;
import github.com.crudapi.address.AddressRepository;
import github.com.crudapi.address.AddressRequest;
import github.com.crudapi.address.AddressResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public PersonResponse createPerson(PersonRequest personRequest) {
        Person savedEntity = personRepository.save(personRequest.toEntity());
        return new PersonResponse(savedEntity);
    }

    public PersonResponse getPerson(Long id) {
        Person person = personExist(id);
        return new PersonResponse(person);
    }

    @Transactional
    public Page<PersonResponse> getAll(Pageable pageable) {
        Page<Person> people = personRepository.findAll(pageable);
        List<PersonResponse> result = people.map(PersonResponse::new).toList();
        return new PageImpl<>(result, pageable, people.getTotalElements());
    }

    @Transactional
    public PersonResponse updatePerson(Long id, PersonRequest personRequest) {
        Person person = personExist(id);
        Person updatedPerson = personRequest.toEntity();
        person.setName(updatedPerson.getName());
        person.setBirthDate(updatedPerson.getBirthDate());

        return new PersonResponse(personRepository.save(person));
    }

    @Transactional
    public void deletePerson(Long id) {
        Person person = personExist(id);
        personRepository.delete(person);
    }

    private Person personExist(Long id) {
        return personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public AddressResponse createAddress(Long personId, AddressRequest addressRequest) {
        Person person = personExist(personId);
        Address address = addressRequest.toEntity();
        address.setPerson(person);
        person.addAddress(address);

        addressRepository.save(address);
        personRepository.save(person);

        return new AddressResponse(address);
    }

    @Transactional
    public List<AddressResponse> listPersonAddress(Long personId) {
        Person person = personExist(personId);
        return person.getAddresses().stream().map(AddressResponse::new).toList();
    }

    @Transactional
    public AddressResponse setMainAddress(Long addressId) {
        Address address = addressExist(addressId);
        address.setIsMainAddress(true);
        addressRepository.save(address);
        return new AddressResponse(address);
    }

    public Address addressExist(Long id) {
        return addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
