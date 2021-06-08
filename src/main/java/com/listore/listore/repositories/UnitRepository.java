package com.listore.listore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.listore.listore.models.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
}
