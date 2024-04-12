package epsi.software.sakila.City;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import epsi.software.sakila.Country.Country;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "city")

public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long id;

    @Column(name = "city")
    private String cityName;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "last_update", updatable = false)
    private LocalDateTime lastUpdate;

    public City(String cityName, Country country, LocalDateTime lastUpdate) {
        this.cityName = cityName;
        this.country = country;
        this.lastUpdate = lastUpdate;
    }
}
