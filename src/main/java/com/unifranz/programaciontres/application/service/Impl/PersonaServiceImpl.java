package com.unifranz.programaciontres.application.service.Impl;

import com.unifranz.programaciontres.application.dto.PersonaDto;
import com.unifranz.programaciontres.application.dto.PersonaResumenDto;
import com.unifranz.programaciontres.application.service.PersonaService;
import com.unifranz.programaciontres.domain.Persona;
import com.unifranz.programaciontres.infrastructure.Persistence.PersonaRepository;
import com.unifranz.programaciontres.infrastructure.web.exception.PersonaEliminadaException;
import com.unifranz.programaciontres.infrastructure.web.exception.PersonaNoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {
    private final PersonaRepository personaRepository;

    @Override
    public PersonaResumenDto guardar(PersonaDto personaDto) {
        Persona persona = new Persona();
        persona.setNombre(personaDto.getNombre());
        persona.setEmail(personaDto.getEmail());
        Persona guardada = personaRepository.save(persona);
        return resumen(guardada);
    }

    @Override
    public List<PersonaResumenDto> listar(Long id, String nombre, String email) {
        return personaRepository.findAll()
                .stream()
                .filter(p -> !p.isEliminado())
                .filter(p -> id == null || Objects.equals(p.getId(), id))
                .filter(p -> nombre == null || (p.getNombre() != null
                        && p.getNombre().equalsIgnoreCase(nombre)))
                .filter(p -> email == null || (p.getEmail() != null
                        && p.getEmail().equalsIgnoreCase(email)))
                .map(this::resumen)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonaDto> listarDetalle() {
        return personaRepository.findAll()
                .stream()
                .map(this::dto)
                .collect(Collectors.toList());
    }

    @Override
    public PersonaDto editar(Long id, PersonaDto personaDto) {
        Persona persona = buscar(id);
        if (persona.isEliminado()) {
            throw new PersonaEliminadaException("No se puede editar una persona eliminada");
        }
        persona.setNombre(personaDto.getNombre());
        persona.setEmail(personaDto.getEmail());
        return dto(personaRepository.save(persona));
    }

    @Override
    public PersonaDto eliminar(Long id) {
        Persona persona = buscar(id);
        persona.setEliminado(true);
        return dto(personaRepository.save(persona));
    }

    @Override
    public void eliminarFisico(Long id) {
        Persona persona = buscar(id);
        personaRepository.delete(persona);
    }

    private Persona buscar(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new PersonaNoEncontradaException(
                        "No existe una persona con el id " + id));
    }

    private PersonaResumenDto resumen(Persona persona) {
        return new PersonaResumenDto(persona.getId(), persona.getNombre(), persona.getEmail());
    }

    private PersonaDto dto(Persona persona) {
        return new PersonaDto(persona.getId(), persona.getNombre(), persona.getEmail(), persona.isEliminado());
    }
}