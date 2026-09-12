package com.unifranz.programaciontres.application.service.Impl;

import com.unifranz.programaciontres.application.dto.MusicaDto;
import com.unifranz.programaciontres.application.dto.MusicaResumenDto;
import com.unifranz.programaciontres.application.service.MusicaService;
import com.unifranz.programaciontres.domain.Musica;
import com.unifranz.programaciontres.infrastructure.Persistence.MusicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MusicaServiceImpl implements MusicaService {
    private final MusicaRepository musicaRepository;

    @Override
    public MusicaResumenDto guardar(MusicaDto musicaDto) {
        Musica musica = new Musica();
        musica.setTitulo(musicaDto.getTitulo());
        musica.setArtista(musicaDto.getArtista());
        musica.setGenero(musicaDto.getGenero());
        Musica guardada = musicaRepository.save(musica);
        return new MusicaResumenDto(guardada.getTitulo(), guardada.getGenero());
    }

    @Override
    public List<MusicaResumenDto> listar(Long id, String genero, String artista) {
        return musicaRepository.findAll()
                .stream()
                .filter(m -> id == null || Objects.equals(m.getId(), id))
                .filter(m -> genero == null || (m.getGenero() != null
                        && m.getGenero().equalsIgnoreCase(genero)))
                .filter(m -> artista == null || (m.getArtista() != null
                        && m.getArtista().equalsIgnoreCase(artista)))
                .map(m -> new MusicaResumenDto(m.getTitulo(), m.getGenero()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MusicaDto> listarDetalle() {
        return musicaRepository.findAll()
                .stream()
                .map(m -> new MusicaDto(m.getId(), m.getTitulo(), m.getArtista(), m.getGenero()))
                .collect(Collectors.toList());
    }
}
