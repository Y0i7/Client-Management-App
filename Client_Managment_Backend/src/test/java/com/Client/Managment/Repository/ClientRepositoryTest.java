package com.Client.Managment.Repository;

import com.Client.Managment.Domain.Client;
import com.Client.Managment.Persistence.Entity.ClientEntity;
import com.Client.Managment.Persistence.Repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * @Author: Orlando Yepes Espitia
 * @Date: 2025/26/04
 * @Description: This class is used to test the ClientRepository class.
 */
@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void existsByClientEmail_shouldReturnTrueWhenPresent() {
        // Dado un entity con email "x"
        ClientEntity e = new ClientEntity();
        e.setClientName("Joe");
        e.setClientLname("Doe");
        e.setClientEmail("JoeDoe@Email.com");
        e.setClientDate(LocalDateTime.now());
        clientRepository.save(e);

        // Cuando se consulta por el email "x"
        boolean exist = clientRepository.existsByClientEmail("JoeDoe@Email.com");

        // Entonces el resultado es true
        assertThat(exist).isTrue();
    }

    @Test
    void findByClientName_shouldBeCaseInsensitive() {
        ClientEntity e1 = new ClientEntity();
        e1.setClientName("Yoi");
        e1.setClientLname("Nunez");
        e1.setClientEmail("y@example.com");
        e1.setClientDate(LocalDateTime.now());
        clientRepository.save(e1);

        //Buscar con Mayusculas/Minusculas mezcladas
        List<ClientEntity> result = clientRepository.findByClientName("yOi");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getClientName()).isEqualToIgnoringCase("Yoi");
    }

    @Test
    void findAllClientsSortedById_shouldReturnDtoListOrdered() {
        // Guardar entidades
        ClientEntity a = new ClientEntity();
        a.setClientName("A");
        a.setClientLname("A1");
        a.setClientEmail("A@Email.com");
        a.setClientDate(LocalDateTime.now());
        ClientEntity b = new ClientEntity();
        b.setClientName("B");
        b.setClientLname("B1");
        b.setClientEmail("B@Email.com");
        b.setClientDate(LocalDateTime.now());

        clientRepository.save(a);
        clientRepository.save(b);

        // El Query Personalizado devuelve List<Client> (DTO)
        List<Client> dtos = clientRepository.findAllClientsSortedById();

        assertThat(dtos).hasSize(2);
        assertThat(dtos.get(0).getId()).isLessThan(dtos.get(1).getId());
        assertThat(dtos.get(0).getClientName()).isEqualTo("A");

    }
}
