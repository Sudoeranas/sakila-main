package epsi.software.sakila.Country;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CountryService {


    private final CountryRepository countryRepository;

    public Flux<Country> getAll() {
        return Flux.fromIterable(countryRepository.findAll())
                .switchIfEmpty(Flux.defer(() -> Flux.error(new ResponseStatusException(HttpStatus.NOT_FOUND))));
    }

    public Mono<Country> getById(Long id) {
        return Mono.justOrEmpty(countryRepository.findById(id))
                .map(country -> new Country(
                        country.getId(),
                        country.getCountry(),
                        country.getLastUpdate()
                ))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND))));
    }

    public Mono<Country> updateCountry(Long id, String countryName) {
        return Mono.justOrEmpty(countryRepository.findById(id))
                .map(country -> new Country(
                        country.getId(),
                        countryName,
                        LocalDateTime.now()
                ))
                .flatMap(country -> Mono.justOrEmpty(countryRepository.save(country)))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND))));
    }

    public Mono<Country> createCountry(String countryName) {
        return Mono.justOrEmpty(countryRepository.save(new Country(
                        countryName,
                        LocalDateTime.now()
                )))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR))));
    }

    public Mono<String> deleteCountry(Long id) {
        return Mono.justOrEmpty(countryRepository.findById(id))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found with id: " + id))))
                .flatMap(country -> {
                    countryRepository.delete(country);
                    return Mono.just("Country with id " + id + " has been successfully deleted.");
                });
    }
}