package org.example.controller;


import org.example.logic.Pet;
import org.example.logic.PetModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {
    public static final PetModel petModel = PetModel.getInstance();
    public static final AtomicInteger newId = new AtomicInteger(1);

    @PostMapping(value = "/createPet", consumes = "application/json")
    public ResponseEntity<String> createPet(@RequestBody Pet pet) {
        int id = newId.getAndIncrement();
        petModel.add(pet, id);
        return ResponseEntity.ok("Pet '" + pet.getName() + "' created successfully with ID: " + id);
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public Map<Integer, Pet> getAll() {
        return petModel.getAll();
    }

    @GetMapping(value = "/getPet", consumes = "application/json", produces = "application/json")
    public Pet getPet(@RequestBody Map<String, Integer> id) {
        return petModel.getFromList(id.get("id"));
    }

    @DeleteMapping(value = "/deletePet/{id}")
    public ResponseEntity<String> deletePet(@PathVariable int id) {
        Pet removedPet = petModel.removeFromList(id);
        if (removedPet != null) {
            return ResponseEntity.ok("Pet with ID " + id + " and name '" + removedPet.getName() + "' deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
        @PutMapping(value = "/updatePet/{id}", consumes = "application/json")
        public ResponseEntity<String> updatePet(@PathVariable int id, @RequestBody Pet updatedPet) {
            if (petModel.getFromList(id) != null) {
                petModel.update(id, updatedPet);
                return ResponseEntity.ok("Pet with ID " + id + " updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        }

}
