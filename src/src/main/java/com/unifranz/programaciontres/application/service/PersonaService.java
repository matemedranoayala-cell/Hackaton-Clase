package com.unifranz.programaciontres.application.service;

import com.unifranz.programaciontres.application.dto.PersonaDto;
import com.unifranz.programaciontres.application.dto.PersonaResumenDto;

import java.util.List;

public interface PersonaService {
    PersonaResumenDto guardar(PersonaDto personaDto);
    List<PersonaResumenDto> listar(Long id, String genero, String artista);
    List<PersonaDto> listarDetalle();
}
