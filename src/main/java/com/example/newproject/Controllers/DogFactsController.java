package com.example.newproject.Controllers;

import com.example.newproject.Repository.DogFactsRepository;
import com.example.newproject.data.DogFacts;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/dogfacts", produces = "application/json")
public class DogFactsController {
    private DogFactsRepository dogFactsRepository;

    @GetMapping("")
    private List<DogFacts> getDogFacts() {

        List<DogFacts> factsOfDogs = dogFactsRepository.findAll()
;
        return factsOfDogs;
    }

    @PostMapping("")
    private void createDogFact(@RequestBody DogFacts dogFact) {
        dogFactsRepository.save(dogFact);
    }

    @DeleteMapping("/{id}")
    private void deleteDogFact(@PathVariable Long id) {
        try {
            dogFactsRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item does not exist");
        }
    }

    @PutMapping("/{id}")
    private void updateDogFact(@PathVariable Long id, @RequestBody DogFacts upDateDogFact) {
        Optional<DogFacts> dogFact =  dogFactsRepository.findById(id);
        if(dogFact.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item does not exit to update");
        }

        upDateDogFact.setId(id);
        dogFactsRepository.save(upDateDogFact);
    }

}
