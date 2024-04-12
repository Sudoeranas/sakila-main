package epsi.software.sakila.Country;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootTest
class CountryServiceTest {


    private final CountryService service;

    @Autowired
    public CountryServiceTest(CountryService service) {
        this.service = service;
    }

    @Test
    void getAll() {
        service.getAll().forEach(System.out::println);
    }

    @Test
    void getById() {
        Country country = service.getById(5L).block();
        System.out.println(country);
    }

    @Test
    void update() {
        LocalDate date = LocalDate.of(2024, 1, 9);
        LocalTime time = LocalTime.of(14, 10, 58, 0);

        LocalDateTime localDateTime = LocalDateTime.of(date, time);
        Country country = new Country(
                1L,
                "France",
                localDateTime
        );
        Country country1 = service.updateCountry(country).block();
        System.out.println(country1);
    }
    @Test
    void create() {
        Country country = new Country("Test");
        Country country1 = service.createCountry(country).block();
        System.out.println(country1);
    }
    @Test
    void delete(){
        System.out.println(service.deleteCountry(152L));
    }
}