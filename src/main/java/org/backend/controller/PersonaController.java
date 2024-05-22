package org.backend.controller;


import org.backend.dto.PersonaDTO;
import org.backend.exception.ModeloNotFoundException;
import org.backend.model.Persona;
import org.backend.service.IPersonaService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    private IPersonaService service;

    public PersonaController(IPersonaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Persona>> listar() throws Exception {
        List<Persona> lista = service.listar();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> listarPorId(@PathVariable("id") Integer id) throws Exception {
        Persona obj = service.listarPorId(id);

        if (obj == null) {
            throw  new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<Persona> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception {
        Persona obj = service.listarPorId(id);

        if (obj.getId() == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        EntityModel<Persona> recurso = EntityModel.of(obj);
        WebMvcLinkBuilder linkTo = linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).listarPorId(id));
        recurso.add(linkTo.withRel("persona-recurso"));

        return recurso;
    }

    @PostMapping
    public ResponseEntity<Persona> registrar(@Valid @RequestBody PersonaDTO persona) throws Exception {
        Persona obj = service.registrarWithDto(persona);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Persona> modificar(@Valid @RequestBody Persona persona) throws Exception {
        Persona obj = service.modificar(persona);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
        Persona obj= service.listarPorId(id);
        if (obj == null) {
           throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
