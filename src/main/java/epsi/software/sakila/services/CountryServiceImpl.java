package epsi.software.sakila.services;

import epsi.software.sakila.entities.Country;
import epsi.software.sakila.repositories.CountryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country create(Country country) {
        if ( country.getLastUpdate()==null)
            country.setLastUpdate(LocalDateTime.now());

        return countryRepository.save(country);
    }

    @Override
    public Country read(Long id) {
        return countryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Country> readAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country update(Country country) {
        country.setLastUpdate(LocalDateTime.now());
        return countryRepository.save(country);
    }

    @Override
    public boolean delete(Long id) {
        countryRepository.deleteById(id);
        return ( read(id) == null ) ;
    }
}
