package pl.kopytka.order.application.dto;

public record OrderAddressDto(
        String street,
        String postalCode,
        String city,
        String houseNo) {
}