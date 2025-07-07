package pl.kopytka.customer.application;

import org.mapstruct.Mapper;
import pl.kopytka.customer.application.dto.CustomerDto;
import pl.kopytka.customer.web.dto.CreateCustomerDto;
import pl.kopytka.customer.web.dto.CreateCustomerRequest;
import pl.kopytka.customer.web.dto.CustomerResponse;

@Mapper(componentModel = "spring")
public interface CustomerApiMapper {

    CustomerResponse toCustomerResponse(CustomerDto customerDto);

    CreateCustomerDto toCreateCustomerDto(CreateCustomerRequest request);
}