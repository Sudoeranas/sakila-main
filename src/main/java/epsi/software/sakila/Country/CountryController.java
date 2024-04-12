package epsi.software.sakila.Country;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("")
    public List<Country> getAll(@RequestParam(name = "id", required = false) Long id) {
        return countryService.getAll();
    }

    @GetMapping("id")
    public Mono<Country> getById(@RequestParam Long id) {
        return countryService.getById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
    }


    @PostMapping("update")
    public Mono<Country> updateCountry(@RequestBody Country country) {
        return countryService.updateCountry(country)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
    }
    @PostMapping("create")
    public Mono<Country> createCountry(@RequestBody Country country){
        return countryService.createCountry(country)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
    }

    @DeleteMapping("delete")
    public Boolean deleteCountry(@RequestParam Long id){
        return countryService.deleteCountry(id);
    }

}
