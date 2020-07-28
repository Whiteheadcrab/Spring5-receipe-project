package whiteheadcrab.springframework.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import whiteheadcrab.springframework.commands.CategoryCommand;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category extends CategoryCommand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;
}
