package tacos.tacoshop;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Taco_Order")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private Date placedAt;

    @NotBlank(message="podanie imienia jest obowiązkowe")
    private String name;

    @NotBlank(message="podanie ulicy jest obowiazkowe")
    private String street;


    @NotBlank(message="podanie miasta jest obowiazkowe")
    private String city;


    @NotBlank(message="podanie wojewodztwa jest obowiazkowe")
    private String state;

    @NotBlank(message="podanie kodu pocztowego jest obowiazkowe")
    private String zip;

    @CreditCardNumber(message="nieprawidlowy numer karty")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="wartosc musi byc w formacie MM/RR")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Nieprawidlowy kod CVV")
    private String ccCVV;

    @ManyToMany(targetEntity=Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    @ManyToOne
    private User user;

    public void addDesign(Taco design) {
        this.tacos.add(design);
    }

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }
}
