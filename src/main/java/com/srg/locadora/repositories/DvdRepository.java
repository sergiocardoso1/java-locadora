package com.srg.locadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srg.locadora.domain.Dvd;

@Repository
public interface DvdRepository extends JpaRepository<Dvd, Long>{

}
