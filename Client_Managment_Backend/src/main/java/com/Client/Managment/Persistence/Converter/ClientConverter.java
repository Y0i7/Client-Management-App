package com.Client.Managment.Persistence.Converter;

import com.Client.Managment.Domain.Client;
import com.Client.Managment.Persistence.Entity.ClientEntity;

import java.util.List;
import java.util.stream.Collectors;
/*
    * @Author: Orlando Yepes Espitia
    * @Date: 2025/26/04
    * @Description: ClientConverter is a utility class that provides methods to convert between
 */
public class ClientConverter {

    /*
     * ClientConverter is a utility class that provides methods to convert between
     * ClientEntity and Client DTO (Data Transfer Object) representations.
     *
     * It includes methods to convert single entities, lists of entities, and vice versa.
     */
    public static Client toDto(ClientEntity entity){ if (entity == null){ return null; }
        Client dto = new Client();
        dto.setId(entity.getId());
        dto.setClientName(entity.getClientName());
        dto.setClientLName(entity.getClientLname());
        dto.setClientEmail(entity.getClientEmail());
        dto.setClientDate(entity.getClientDate());
        return dto;
    }

    public static ClientEntity toEntity(Client dto){ if (dto == null){ return null; }
        ClientEntity entity = new ClientEntity();
        entity.setId(dto.getId());
        entity.setClientName(dto.getClientName());
        entity.setClientLname(dto.getClientLName());
        entity.setClientEmail(dto.getClientEmail());
        entity.setClientDate(dto.getClientDate());
        return entity;
    }

    public static List<Client> toDtoList(List<ClientEntity> entities){
        return entities.stream()
                .map(ClientConverter::toDto)
                .collect(Collectors.toList());
    }

    public static List<ClientEntity> toEntityList(List<Client> dtos){
        return dtos.stream()
                .map(ClientConverter::toEntity)
                .collect(Collectors.toList());
    }

}
