package com.listore.listore.repositories;

import com.listore.listore.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	Boolean existsByName(String name);
}
