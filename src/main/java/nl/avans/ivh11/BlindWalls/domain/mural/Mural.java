package nl.avans.ivh11.BlindWalls.domain.mural;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Mural {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Message may not be empty.")
    private String name;

    @NotEmpty(message = "Message is required.")
    private String description;

    public Mural() {}

    public Mural(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id){ this.id = id; }
}
