package org.backend.mapper;

import org.backend.dto.PersonaDTO;
import org.backend.model.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonaMapper {
    PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);

    @Mapping(target = "fechaNacimiento", source = "fechaNacimiento", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    Persona toEntity(PersonaDTO personaDTO);

    @Mapping(target = "fechaNacimiento", source = "fechaNacimiento", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    PersonaDTO toDto(Persona persona);
}