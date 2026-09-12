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
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String email) {
        return ResponseEntity.ok(personaService.listar(id, nombre, email));
    }

    @GetMapping("/detalle")
    public ResponseEntity<List<PersonaDto>> listarDetalle() {
        return ResponseEntity.ok(personaService.listarDetalle());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDto> editar(
            @PathVariable Long id,
            @RequestBody PersonaDto personaDto) {
        return ResponseEntity.ok(personaService.editar(id, personaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaDto> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.eliminar(id));
    }

    @DeleteMapping("/{id}/fisico")
    public ResponseEntity<Void> eliminarFisico(@PathVariable Long id) {
        personaService.eliminarFisico(id);
        return ResponseEntity.noContent().build();
    }
}
