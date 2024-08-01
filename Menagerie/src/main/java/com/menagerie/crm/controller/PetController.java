package com.menagerie.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.menagerie.crm.entity.Event;
import com.menagerie.crm.entity.Pet;
import com.menagerie.crm.service.PetService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/pets")
public class PetController {
	
	static Logger logger=LoggerFactory.getLogger(PetController.class);

    @Autowired
    private PetService petService;

    // List all Pets, optionally filtered by species
    @GetMapping
    public List<Pet> getAllPets(@RequestParam(required = false) String species) {
    	
    	logger.debug("fetching all the pet details");
    	
        return petService.getAllPets(species);
    }

    // Get a single Pet and all of its events
    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetWithEvents(@PathVariable int id) {
        Pet pet = petService.getPetWithEvents(id);
        logger.debug("fetching pet details via id");
        return ResponseEntity.ok(pet);
    }

    // Add a Pet
    @PostMapping
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
    	logger.info("adding a pet");
        Pet createdPet = petService.addPet(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPet);
    }

    // Edit a Pet
    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable int id, @RequestBody Pet petDetails) {
    	logger.debug("updating pet details");
        Pet updatedPet = petService.updatePet(id, petDetails);
        return ResponseEntity.ok(updatedPet);
    }

    // Add an event to a Pet
    @PostMapping("/{id}/events")
    public ResponseEntity<Event> addEvent(@PathVariable int id, @RequestBody Event event) {
        petService.addEvent(id, event);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    // Delete a Pet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable int id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}

