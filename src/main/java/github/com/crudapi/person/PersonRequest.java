package github.com.crudapi.person;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;

public record PersonRequest(
        @NotBlank
         String name,
         @NotNull
         @DateTimeFormat(pattern = "yyyy-MM-dd")
         @Past(message = "A data deve ser anterior à data atual.")
         LocalDate birthDate
) {
    public Person toEntity(){
        Person person = new Person();
        person.setName(this.name());
        person.setBirthDate(this.birthDate());
        person.setAddresses(new ArrayList<>());

        return person;
    }
}
