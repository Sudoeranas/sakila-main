package epsi.software.sakila.Country;

import reactor.core.publisher.Mono;

import java.util.List;

public interface ICountryService {
    List<Country> getAll();
    Mono<Country> getById(Long id);
    Mono<Country> updateCountry(Country country);

    Mono<Country> createCountry(Country country);
    Boolean deleteCountry(Long id);
}
