package com.ynov.guilde.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AventurierJpaRepository extends JpaRepository<AventurierEntity, UUID> {
}