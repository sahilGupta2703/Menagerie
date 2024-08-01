package com.menagerie.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.menagerie.crm.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    // Additional custom query methods if needed
}
