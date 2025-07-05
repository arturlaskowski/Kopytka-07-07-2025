package pl.kopytka.customer;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerApiMapper {

    CustomerResponse toCustomerResponse(CustomerDto customerDto);

    CreateCustomerDto toCreateCustomerDto(CreateCustomerRequest request);
}