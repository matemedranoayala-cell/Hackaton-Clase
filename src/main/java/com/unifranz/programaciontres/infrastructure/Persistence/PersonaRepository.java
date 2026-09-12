package com.unifranz.programaciontres.infrastructure.Persistence;

import com.unifranz.programaciontres.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
