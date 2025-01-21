package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public void registrarTopico(@RequestBody @Valid DatosTopico datosTopico){
        topicoRepository.save(new Topico(datosTopico));
    }

    @GetMapping
    public Page<DatosListadoTopico> listadoTopico(@PageableDefault(size=10) Pageable pageable){
        return topicoRepository.findByActivoTrue(pageable).map(DatosListadoTopico::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerTopicoPorId(@PathVariable Long id){
        Optional buscarTopico = topicoRepository.findByIdAndActivoTrue(id);

        if(buscarTopico.isPresent()){
            Topico topico = topicoRepository.getReferenceById(id);
            return ResponseEntity.ok(new DatosListadoTopico(topico));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Topico no encontrado");
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico,
                                 @PathVariable Long id){
        Optional buscarTopico = topicoRepository.findByIdAndActivoTrue(id);

        if(buscarTopico.isPresent()){
            Topico topico = topicoRepository.getReferenceById(id);
            topico.actualizarDatos(datosActualizarTopico);
            return ResponseEntity.ok(new DatosListadoTopico(topico));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Topico no encontrado");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id){
        Optional buscarTopico = topicoRepository.findByIdAndActivoTrue(id);
        if(buscarTopico.isPresent()){
            Topico topico = topicoRepository.getReferenceById(id);
            topico.desactivarTopico();
            return ResponseEntity.ok(new DatosListadoTopico(topico));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Topico no encontrado");
    }
}
