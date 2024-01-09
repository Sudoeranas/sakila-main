package epsi.software.sakila.repositories;

import epsi.software.sakila.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
