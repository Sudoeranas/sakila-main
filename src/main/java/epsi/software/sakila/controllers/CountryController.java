package epsi.software.sakila.controllers;


import epsi.software.sakila.entities.Country;
import epsi.software.sakila.services.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/countries")
public class CountryController {

    private static final Logger log = LoggerFactory.getLogger(CountryController.class);

    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping(value ={"","/all"} , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, headers = "content-type=text/plain")
    public List<Country> getAll(){
        log.trace("call get all country");
        return service.readAll();
    }

    @PostMapping("/create")
    public Country createCountry(@RequestBody Country country){
        log.trace("call POST create country : {}",country);
        return service.create(country);
    }
}
