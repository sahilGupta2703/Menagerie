package com.menagerie.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.menagerie.crm.entity.Event;
import com.menagerie.crm.entity.Pet;
import com.menagerie.crm.repository.EventRepository;
import com.menagerie.crm.repository.PetRepository;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private EventRepository eventRepository;

    // List Pets, optional filtration by species
    public List<Pet> getAllPets(String species) {
        if (species != null && !species.isEmpty()) {
            return petRepository.findBySpecies(species);
        } else {
            return petRepository.findAll();
        }
    }

    // Get Pet and all events
    @Transactional(readOnly = true)
    public Pet getPetWithEvents(int id) {
        return petRepository.findByIdWithEvents(id);
    }

    // Add new
    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }

    // update
    public Pet updatePet(int id, Pet petDetails) {
        Pet pet = petRepository.findById(id)
                .orElseThrow();

        pet.setName(petDetails.getName());
        pet.setOwner(petDetails.getOwner());
        pet.setSpecies(petDetails.getSpecies());
        pet.setSex(petDetails.getSex());
        pet.setBirth(petDetails.getBirth());
        pet.setDeath(petDetails.getDeath());

        return petRepository.save(pet);
    }

    // Add event
    public Event addEvent(int petId, Event event) {
        Pet pet = petRepository.findById(petId)
               .orElseThrow();
        event.setPet(pet);
        return eventRepository.save(event);
    }

    // Delete 
    public void deletePet(int id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow();
        petRepository.delete(pet);
    }
}

