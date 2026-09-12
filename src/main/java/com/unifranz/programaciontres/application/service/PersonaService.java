package com.unifranz.programaciontres.application.service;

import com.unifranz.programaciontres.application.dto.PersonaDto;
import com.unifranz.programaciontres.application.dto.PersonaResumenDto;

import java.util.List;

public interface PersonaService {
    PersonaResumenDto guardar(PersonaDto personaDto);
    List<PersonaResumenDto> listar(Long id, String nombre, String email);
    List<PersonaDto> listarDetalle();
    PersonaDto editar(Long id, PersonaDto personaDto);
    PersonaDto eliminar(Long id);
    void eliminarFisico(Long id);
}
