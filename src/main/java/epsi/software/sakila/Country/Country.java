package epsi.software.sakila.Country;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long id;

    @Column(name = "country")
    private String country;


    @Column(name = "last_Update", updatable = false)
    private LocalDateTime lastUpdate;

    public Country(String country) {
        this.id = null;
        this.country = country;
        this.lastUpdate = LocalDateTime.now();
    }

    public Country(Long id, String country) {
        this.id = id;
        this.country = country;
        this.lastUpdate = LocalDateTime.now();
    }

}
