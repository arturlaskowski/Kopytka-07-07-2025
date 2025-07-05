package pl.kopytka.application.dto;

public record OrderAddressDto(
        String street,
        String postalCode,
        String city,
        String houseNo) {
}