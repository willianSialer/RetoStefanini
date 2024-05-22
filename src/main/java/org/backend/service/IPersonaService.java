package org.backend.service;

import org.backend.dto.PersonaDTO;
import org.backend.model.Persona;

import java.util.List;

public interface IPersonaService extends ICRUD<Persona, Integer>{

    Persona registrarWithDto(PersonaDTO personaDTO) throws Exception;


}
