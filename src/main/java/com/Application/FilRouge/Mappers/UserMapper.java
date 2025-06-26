package com.Application.FilRouge.Mappers;


import com.Application.FilRouge.DTO.UserDto;
import com.Application.FilRouge.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.control.MappingControl;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto userToDto(User utilisateurs);
    User dtoToUser(UserDto utilisateursDto);

}
