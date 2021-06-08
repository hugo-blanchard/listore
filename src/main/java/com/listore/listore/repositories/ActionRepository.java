package com.listore.listore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.listore.listore.models.Action;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {
}
