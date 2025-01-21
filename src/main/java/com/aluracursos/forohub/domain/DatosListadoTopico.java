package com.aluracursos.forohub.domain;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        String autor
) {
    public DatosListadoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor()
        );
    }
}
