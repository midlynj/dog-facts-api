package com.example.newproject.Repository;

import com.example.newproject.data.DogFacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogFactsRepository extends JpaRepository<DogFacts, Long > {
}
