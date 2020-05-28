package tacos.tacoshop.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacos.tacoshop.Taco;
import tacos.tacoshop.data.TacoRepository;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping( path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {

    private final TacoRepository tacoRepo;

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos(){
        PageRequest pageRequest = PageRequest.of(0,12, Sort.by("createdAt").descending());
        return tacoRepo.findAll(pageRequest).getContent();
    }



    @GetMapping("/{id}")
    public Taco tacoById(@PathVariable("id") int id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()) {
            return optTaco.get();
        }
        return null;
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }


}
