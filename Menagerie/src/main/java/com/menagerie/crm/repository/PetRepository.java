package com.menagerie.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.menagerie.crm.entity.Pet;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

    // List all Pets, optional filtration
    List<Pet> findBySpecies(String species);

    // Find by ID
    @Query("SELECT p FROM Pet p LEFT JOIN FETCH p.events WHERE p.id = :id")
    Pet findByIdWithEvents(@Param("id") int id);
}

