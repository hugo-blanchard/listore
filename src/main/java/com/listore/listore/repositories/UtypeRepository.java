package com.listore.listore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.listore.listore.models.Utype;

import java.util.Optional;

@Repository
public interface UtypeRepository extends JpaRepository<Utype, Long> {
	Boolean existsByName(String name);
}
