package epsi.software.sakila.Country;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryServiceTest {

    @Mock
    CountryRepository countryRepository;

    @InjectMocks
    CountryService countryService;

    @Nested
    class getAll{
        @Test
        void shouldReturnListOfCountry(){
            Country country_1 = new Country(
                    1L,
                    "country_1",
                    LocalDateTime.now()
            );
            Country country_2 = new Country(
                    2L,
                    "country_2",
                    LocalDateTime.now()
            );

            when(countryRepository.findAll()).thenReturn(List.of(country_1, country_2));
            StepVerifier.create(countryService.getAll())
                    .expectNext(country_1)
                    .expectNext(country_2)
                    .verifyComplete();
        }

        @Test
        void shouldReturnNotFound(){
            when(countryRepository.findAll()).thenReturn(new ArrayList<>());
            StepVerifier.create(countryService.getAll())
                    .verifyErrorMatches(error -> {
                        assertInstanceOf(ResponseStatusException.class, error);
                        ResponseStatusException responseStatusException = (ResponseStatusException) error;
                        assertEquals(HttpStatus.NOT_FOUND, responseStatusException.getStatusCode());
                        return true;
                    });

        }
    }

    @Nested
    class getById{
        @Test
        void shouldReturnCountry(){
            Country country_1 = new Country(
                    1L,
                    "country_1",
                    LocalDateTime.now()
            );
            Country country_2 = new Country(
                    2L,
                    "country_2",
                    LocalDateTime.now()
            );

            when(countryRepository.findById(1L)).thenReturn(Optional.of(country_1));
            when(countryRepository.findById(2L)).thenReturn(Optional.of(country_2));

            StepVerifier.create(countryService.getById(1L))
                    .expectNext(country_1)
                    .verifyComplete();

            StepVerifier.create(countryService.getById(2L))
                    .expectNext(country_2)
                    .verifyComplete();
        }

        @Test
        void shouldReturnNotFound(){
            when(countryRepository.findById(1L)).thenReturn(Optional.empty());
            StepVerifier.create(countryService.getById(1L))
                    .verifyErrorMatches(error -> {
                        assertInstanceOf(ResponseStatusException.class, error);
                        ResponseStatusException responseStatusException = (ResponseStatusException) error;
                        assertEquals(HttpStatus.NOT_FOUND, responseStatusException.getStatusCode());
                        return true;
                    });
        }
    }

    @Nested
    class updateCountry{

        @Test
        void shouldReturnCountry(){
            Country country_bdd = new Country(
                    1L,
                    "country_1",
                    LocalDateTime.now()
            );
            String updatedName = "country_1_updated";
            Country assertUpdatedCountry =  new Country(
                    1L,
                    "country_1_updated",
                    LocalDateTime.now()
            );
            when(countryRepository.findById(1L)).thenReturn(Optional.of(country_bdd));
            when(countryRepository.save(any())).thenReturn(assertUpdatedCountry);

            StepVerifier.create(countryService.updateCountry(1L, updatedName))
                    .expectNext(assertUpdatedCountry)
                    .verifyComplete();
        }

        @Test
        void shouldReturnNotFound(){

            String updatedName = "country_1_updated";
            when(countryRepository.findById(1L)).thenReturn(Optional.empty());

            StepVerifier.create(countryService.updateCountry(1L, updatedName))
                    .verifyErrorMatches(error -> {
                        assertInstanceOf(ResponseStatusException.class, error);
                        ResponseStatusException responseStatusException = (ResponseStatusException) error;
                        assertEquals(HttpStatus.NOT_FOUND, responseStatusException.getStatusCode());
                        return true;
                    });
        }
    }

    @Nested
    class createCountry{

        @Test
        void shouldReturnCountry(){

        }

        @Test
        void shouldReturnNotFound(){

        }
    }

    @Nested
    class deleteCountry{
        @Test
        void shouldReturnString(){

        }

        @Test
        void shouldReturnNotFound(){

        }
    }

}