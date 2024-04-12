package epsi.software.sakila.Country;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping("")
    public Flux<Country> getAll(@RequestParam(name = "id", required = false) Long id) {
        return countryService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Country> getById(@PathVariable Long id) {
        return countryService.getById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
    }

    @PutMapping("/{id}")
    public Mono<Country> updateCountry(@PathVariable Long id, @RequestBody String countryName) {
        return countryService.updateCountry(id, countryName)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
    }
    @PostMapping("")
    public Mono<Country> createCountry(@RequestBody String countryName){
        return countryService.createCountry(countryName)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
    }

    @DeleteMapping("/{id}")
    public Mono<String> deleteCountry(@PathVariable Long id){
        return countryService.deleteCountry(id);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
    }

}