package com.legar.auweb.repository;

import com.legar.auweb.entity.AdabasField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdabasFieldRepository extends JpaRepository<AdabasField, Long> {

    /**
     * Finds an AdabasField by its adabasId.
     *
     * @param adabasId the 2-character Adabas short name
     * @return an Optional containing the found AdabasField, or empty if not found
     */
    Optional<AdabasField> findByAdabasId(String adabasId);
}
