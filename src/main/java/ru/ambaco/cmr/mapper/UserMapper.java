package ru.ambaco.cmr.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.ambaco.cmr.Dto.UserDto;
import ru.ambaco.cmr.entities.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto usertoUserDto(User user);
}
