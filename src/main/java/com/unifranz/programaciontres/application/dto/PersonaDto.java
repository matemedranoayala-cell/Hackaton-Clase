package com.unifranz.programaciontres.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDto {
    private Long id;
    private String nombre;
    private String email;
    private boolean eliminado;
}
