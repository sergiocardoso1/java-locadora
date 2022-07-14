package com.srg.locadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srg.locadora.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
