package pl.kopytka.restaurant.application;

import pl.kopytka.restaurant.application.dto.ProductProjection;
import pl.kopytka.restaurant.application.dto.RestaurantQuery;
import pl.kopytka.restaurant.domain.entity.Product;
import pl.kopytka.restaurant.domain.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(target = "id", source = "id.restaurantId")
    RestaurantQuery toProjection(Restaurant restaurant);

    @Mapping(target = "id", source = "id.productId")
    @Mapping(target = "price", source = "price.amount")
    ProductProjection toProjection(Product product);
}
