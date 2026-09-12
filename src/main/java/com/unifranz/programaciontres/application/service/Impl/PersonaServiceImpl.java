package com.unifranz.programaciontres.application.service.Impl;

import com.unifranz.programaciontres.application.dto.PersonaDto;
import com.unifranz.programaciontres.application.dto.PersonaResumenDto;
import com.unifranz.programaciontres.application.service.PersonaService;
import com.unifranz.programaciontres.domain.Persona;
import com.unifranz.programaciontres.infrastructure.Persistence.PersonaRepository;
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
        persona.setTitulo(personaDto.getTitulo());
        persona.setArtista(personaDto.getArtista());
        persona.setGenero(personaDto.getGenero());
        Persona guardada = personaRepository.save(persona);
        return new PersonaResumenDto(guardada.getTitulo(), guardada.getGenero());
    }

    @Override
    public List<PersonaResumenDto> listar(Long id, String genero, String artista) {
        return personaRepository.findAll()
                .stream()
                .filter(m -> id == null || Objects.equals(m.getId(), id))
                .filter(m -> genero == null || (m.getGenero() != null
                        && m.getGenero().equalsIgnoreCase(genero)))
                .filter(m -> artista == null || (m.getArtista() != null
                        && m.getArtista().equalsIgnoreCase(artista)))
                .map(m -> new PersonaResumenDto(m.getTitulo(), m.getGenero()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonaDto> listarDetalle() {
        return personaRepository.findAll()
                .stream()
                .map(m -> new PersonaDto(m.getId(), m.getTitulo(), m.getArtista(), m.getGenero()))
                .collect(Collectors.toList());
    }
}
