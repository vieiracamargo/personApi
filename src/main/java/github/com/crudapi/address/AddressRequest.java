package github.com.crudapi.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AddressRequest(
        @NotBlank(message = "campo obrigatório")
        String neighborhood,
        @NotBlank @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido")
        String zipCode,
        @NotNull(message = "campo obrigatório")
        Integer number,
        @NotBlank(message = "campo obrigatório")
        String city,

        Boolean isMainAddress
) {
        public Address toEntity(){
                Address address = new Address();
                address.setNeighborhood(this.neighborhood);
                address.setZipCode(this.zipCode);
                address.setNumber(this.number);
                address.setCity(this.city);
                address.setIsMainAddress(false);

                return address;
        }

}
