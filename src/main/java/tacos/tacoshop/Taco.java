package tacos.tacoshop;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date createdAt;

    @NotNull
    @Size(min = 5, message = "Nazwa musi sie skladac z 5 znakow")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min = 1, message = "musisz wybrac przynajmniej jeden skladnik")
    private List<Ingredient>ingredients = new ArrayList<>();

    @PrePersist
    void createdAt(){
        this.createdAt = new Date();
    }
}
