package com.Client.Managment.Service;

import com.Client.Managment.Domain.Client;
import com.Client.Managment.Persistence.Converter.ClientConverter;
import com.Client.Managment.Persistence.Entity.ClientEntity;
import com.Client.Managment.Persistence.Repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*
 * @Author: Orlando Yepes Espitia
 * @Date: 2025/26/04
 * @Description: ClientController is a REST controller that handles HTTP requests related to clients.
 */
@ExtendWith(MockitoExtension.class)
public class ClientServiceDBImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceDBImpl clientServiceDBImpl;

    private ClientEntity entity;
    private Client dto;

    @BeforeEach
    void setUp() {
        entity = new ClientEntity();
        entity.setId(1L);
        entity.setClientName("Joe");
        entity.setClientLname("Doe");
        entity.setClientEmail("JoeDoe@Email.com");
        entity.setClientDate(LocalDateTime.now());

        dto = ClientConverter.toDto(entity);
    }

    @Test
    void findByClientId_existing() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(entity));

        Client result = clientServiceDBImpl.findByClientId(1L);

        assertNotNull(result);
        assertEquals("Joe", result.getClientName());
        verify(clientRepository).findById(1L);
    }

    @Test
    void findByCLientId_notFound() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());

        Client result = clientServiceDBImpl.findByClientId(99L);

        assertNull(result);
        verify(clientRepository).findById(99L);
    }

    @Test
    void saveClient_happyPath() {
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(entity);

        Client toSave = new Client();
        toSave.setClientName("Joe");
        toSave.setClientLName("Doe");
        toSave.setClientEmail("JoeDoe@Email.com");
        toSave.setClientDate(LocalDateTime.now());

        Client saved = clientServiceDBImpl.saveClient(toSave);

        assertEquals(entity.getId(), saved.getId());
        verify(clientRepository).save(any(ClientEntity.class));
    }


}
