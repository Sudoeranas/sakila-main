package epsi.software.sakila.Country;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CountryRepositorytest {

    @Autowired
    CountryRepository countryRepository;

    @Test
    void findAll(){
        Country country_1 = countryRepository.save(new Country(
                1L,
                "country_1",
                LocalDateTime.now()
        ));
        Country country_2 = countryRepository.save(new Country(
                2L,
                "country_2",
                LocalDateTime.now()
        ));
        assertEquals(countryRepository.findAll(), List.of(country_1, country_2));
    }

    @Test
    void findById(){
        Country country_1 = countryRepository.save(new Country(
                1L,
                "country_1",
                LocalDateTime.now()
        ));
        Country country_2 = countryRepository.save(new Country(
                2L,
                "country_2",
                LocalDateTime.now()
        ));

        assertEquals(countryRepository.findById(1L).orElseThrow(),country_1);
        assertEquals(countryRepository.findById(2L).orElseThrow(),country_2);
    }
}