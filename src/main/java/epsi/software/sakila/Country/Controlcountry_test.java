package epsi.software.sakila.Country;

import javax.management.MXBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Nested;

@WebFluxTest(CountryController.class)
public class Controlcountry_test<WebTestClient> {

    @Autowired
    WebTestClient webTestClient;

    CountryService countryService;

    class getAll {

    }

}
