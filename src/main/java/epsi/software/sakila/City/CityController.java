package epsi.software.sakila.City;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    // Afficher toutes les villes
    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    // Afficher une ville par son Id
    @GetMapping("/{cityId}")
    public City getCityById(@PathVariable Long cityId) {
        return cityService.getCityById(cityId);
    }

    // Afficher les villes dont le nom contient une expression donnée
    @GetMapping("/search")
    public List<City> searchCitiesByName(@RequestParam String cityName) {
        return cityService.searchCitiesByName(cityName);
    }
}
