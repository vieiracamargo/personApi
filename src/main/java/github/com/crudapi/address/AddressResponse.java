package github.com.crudapi.address;

public record AddressResponse(
        Long Id,

        String neighborhood,

        String zipCode,

        Integer number,

        String city,

        Boolean isMainAddress
) {
    public AddressResponse(Address address) {
        this(
                address.getId(),
                address.getNeighborhood(),
                address.getZipCode(),
                address.getNumber(),
                address.getCity(),
                address.getIsMainAddress()
        );
    }
}
