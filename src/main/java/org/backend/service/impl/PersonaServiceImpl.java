package org.backend.service.impl;

import org.backend.dto.PersonaDTO;
import org.backend.mapper.PersonaMapper;
import org.backend.model.Persona;
import org.backend.repo.IGenericRepo;
import org.backend.repo.IPersonaRepo;
import org.backend.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl extends CRUDImpl<Persona, Integer> implements IPersonaService {

    @Autowired
    private IPersonaRepo repo;

    @Override
    protected IGenericRepo<Persona, Integer> getRepo() {
        return repo;
    }

    @Override
    public Persona registrarWithDto(PersonaDTO personaDTO) {

        Persona persona = PersonaMapper.INSTANCE.toEntity(personaDTO);

        return repo.save(persona);
    }


}
