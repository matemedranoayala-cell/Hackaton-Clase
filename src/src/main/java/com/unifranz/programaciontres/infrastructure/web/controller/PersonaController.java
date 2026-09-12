package com.unifranz.programaciontres.infrastructure.web.controller;

import com.unifranz.programaciontres.application.dto.PersonaDto;
import com.unifranz.programaciontres.application.dto.PersonaResumenDto;
import com.unifranz.programaciontres.application.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @PostMapping
    public ResponseEntity<PersonaResumenDto> guardar(@RequestBody PersonaDto personaDto) {
        return ResponseEntity.ok(personaService.guardar(personaDto));
    }

    @GetMapping
    public ResponseEntity<List<PersonaResumenDto>> listar(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) String artista) {
        return ResponseEntity.ok(personaService.listar(id, genero, artista));
    }

    @GetMapping("/detalle")
    public ResponseEntity<List<PersonaDto>> listarDetalle() {
        return ResponseEntity.ok(personaService.listarDetalle());
    }
}
