package ie.atu.week5_githubactions.controller;

import ie.atu.week5_githubactions.model.Passenger;
import ie.atu.week5_githubactions.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
// added comments
@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
    private PassengerService service;
    public PassengerController(PassengerService service) {this.service = service;}

    @GetMapping
    public ResponseEntity<List<Passenger>> getAll(){return ResponseEntity.ok(service.findAll());}

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getbyId(@PathVariable String id){
        Optional<Passenger> maybe = service.findById(id);
        if(maybe.isPresent()){
            return ResponseEntity.ok(maybe.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Passenger> create(@Valid @RequestBody Passenger p){
        Passenger created = service.create(p);// calls the create method from the service layer and adds p to the array list
        return ResponseEntity
                .created(URI.create("api/passengers/" + created.getPassengerId()))
                .body(created);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Passenger> update(@PathVariable String id, @Valid @RequestBody Passenger p){
        Optional<Passenger> maybe = service.update(id, p.getName(), p.getEmail());
        if(maybe.isPresent()){
            return ResponseEntity.ok(maybe.get());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Passenger> delete(@PathVariable String id){
        boolean deleted = service.delete(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
