package com.aluracursos.forohub.domain;

import jakarta.validation.constraints.NotBlank;

public record DatosTopico(
        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

        @NotBlank
        String fechaCreacion,

        @NotBlank
        String status,

        @NotBlank
        String autor,

        @NotBlank
        String curso
) {
}