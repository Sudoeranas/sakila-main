package epsi.software.sakila.Country;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;


@RequiredArgsConstructor
@Service
public class CountryService implements ICountryService {


    private final CountryRepository countryRepository;

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public Mono<Country> getById(Long id) {
        return Mono.justOrEmpty(countryRepository.findById(id))
                .map(country -> new Country(
                        country.getId(),
                        country.getCountry(),
                        country.getLastUpdate()
                ));
    }


    public Mono<Country> updateCountry(Country country) {
        return Mono.justOrEmpty(countryRepository.save(country))
                .map(country1 -> new Country(
                        country1.getId(),
                        country1.getCountry()
                ));
    }

    @Override
    public Mono<Country> createCountry(Country country) {
        return Mono.justOrEmpty(countryRepository.save(country))
                .map(country1 -> new Country(
                        country1.getId(),
                        country1.getCountry(),
                        country1.getLastUpdate()
                ));
    }

    @Override
    public Boolean deleteCountry(Long id) {

        if (countryRepository.existsById(id)) {
            countryRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
