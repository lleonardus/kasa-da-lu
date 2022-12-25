package com.leonardus.kasadalu.repositories;

import com.leonardus.kasadalu.entities.FrenchToast;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Registered
public interface FrenchToastRepository extends JpaRepository<FrenchToast, Long> {

    Optional<FrenchToast> findByFlavorIgnoreCase(String name);
}
