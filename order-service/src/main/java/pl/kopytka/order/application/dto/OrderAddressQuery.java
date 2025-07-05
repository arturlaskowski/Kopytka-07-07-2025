package pl.kopytka.order.application.dto;

public record OrderAddressQuery(
    String street,
    String postalCode,
    String city,
    String houseNo
) {}
