package epsi.software.sakila.services;

import epsi.software.sakila.entities.Country;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CountryServiceTest {

    private static final Logger log = LoggerFactory.getLogger(CountryServiceTest.class);
    @Autowired
    CountryService service;


    @Test
    void create() {
       Country country = new Country();
       country.setCountry("Les Flandres");
       service.create(country);
    }

    @Test
    void read() {
        Long id = 34l;
        var france = service.read(id);
        assertNotNull(france, "Le pays France à disparu de la base");
        System.out.println(france);
    }

    @Test
    void readAll() {
        service.readAll().stream().forEach(c-> System.out.println(c));
    }
}