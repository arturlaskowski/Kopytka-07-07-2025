package pl.kopytka.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity(name = "order_addresses")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderAddress {

    @Id
    private UUID id;

    private String street;

    @NotBlank
    private String postalCode;

    @NotBlank
    private String city;

    @NotBlank
    private String houseNo;

    public OrderAddress(String street, String postalCode, String city, String houseNo) {
        this.id = UUID.randomUUID();
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.houseNo = houseNo;
    }
}
