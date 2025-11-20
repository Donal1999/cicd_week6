package ie.atu.week5_githubactions.service;

import ie.atu.week5_githubactions.controller.errorHandling.DuplicateError;
import ie.atu.week5_githubactions.model.Passenger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // service layer
public class PassengerService {
    private final List<Passenger> store= new ArrayList<Passenger>(); //mock repository layer
    //get
    public List<Passenger> findAll() {
        return new ArrayList<>(store);
    }
    //get with id
    public Optional <Passenger> findById(String id) {
        for(Passenger p : store) {// loop through the array to check if id equal
            if(p.getPassengerId().equals(id)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }
    //post
    public Passenger create(Passenger p) { // create a passenger to add to arraylist
        if(findById(p.getPassengerId()).isPresent()) {
            throw new DuplicateError("Passenger with id :" + p.getPassengerId() + " already exists");
        }
        store.add(p);
        return p;
    }

    public Optional<Passenger> update(String id, String name, String email) {
        for(Passenger p : store) {
            if(p.getPassengerId().equals(id)) {
                p.setName(name);
                p.setEmail(email);
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public boolean delete(String id) {
        for(int i=0; i<store.size(); i++) {
            if(store.get(i).getPassengerId().equals(id)) {
                store.remove(i);
                return true;
            }
        }
        return false;
    }

}
