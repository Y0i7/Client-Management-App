package com.Client.Managment.Service;

import com.Client.Managment.Domain.Client;
import org.springframework.stereotype.Service;

import java.util.List;
/*
 * @Author: Orlando Yepes Espitia
 * @Date: 2025/26/04
 * @Description: ClientService interface for managing client operations.
 */
@Service
public interface ClientService {

    List<Client> findAllClients();

    List<Client> findByClientName(String clientName);

    Client findByClientId(Long id);

    Client saveClient(Client client);

    Client updateClient(Client client);

    Client deleteClient(Long id);

    boolean existByEmail(String email);

}
