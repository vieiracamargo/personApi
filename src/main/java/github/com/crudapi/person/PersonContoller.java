package github.com.crudapi.person;

import github.com.crudapi.address.AddressRequest;
import github.com.crudapi.address.AddressResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/person")
@RequiredArgsConstructor

public class PersonContoller {
    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonResponse> createPerson(@RequestBody @Valid PersonRequest personRequest, UriComponentsBuilder uriComponentsBuilder) {
        PersonResponse person = personService.createPerson(personRequest);
        URI uri = uriComponentsBuilder.path("api/v1/person").buildAndExpand(person.id()).toUri();
        return ResponseEntity.created(uri).body(person);

    }

    @GetMapping("{id}")
    public ResponseEntity<PersonResponse> getPerson(@PathVariable Long id) {
        PersonResponse person = personService.getPerson(id);
        return ResponseEntity.ok(person);
    }

    @GetMapping
    public ResponseEntity<Page<PersonResponse>> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10) Pageable pageable) {
        Page<PersonResponse> people = personService.getAll(pageable);
        return ResponseEntity.ok(people);
    }

    @PutMapping("{id}")
    public ResponseEntity<PersonResponse> updatePerson(@RequestBody @Valid PersonRequest personRequest, @PathVariable Long id) {
        PersonResponse personResponse = personService.updatePerson(id, personRequest);
        return ResponseEntity.ok(personResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/address/{personId}")
    public ResponseEntity<AddressResponse> createAddres(@PathVariable Long personId, @RequestBody @Valid AddressRequest addressRequest) {
        AddressResponse addresForPerson = personService.createAddress(personId, addressRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(addresForPerson);
    }

    @GetMapping("/address/{personId}")
    public ResponseEntity<List<AddressResponse>> listPersonAddress(@PathVariable Long personId) {
        List<AddressResponse> addressResponses = personService.listPersonAddress(personId);
        return ResponseEntity.ok(addressResponses);
    }

    @PostMapping("address/main/{addressId}")
    public ResponseEntity<AddressResponse> setMainAddress(@PathVariable Long addressId) {
        AddressResponse addressResponse = personService.setMainAddress(addressId);
        return ResponseEntity.ok(addressResponse);
    }

}
