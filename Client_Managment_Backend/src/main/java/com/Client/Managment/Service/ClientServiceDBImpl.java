package com.Client.Managment.Service;

import com.Client.Managment.Domain.Client;
import com.Client.Managment.Persistence.Converter.ClientConverter;
import com.Client.Managment.Persistence.Entity.ClientEntity;
import com.Client.Managment.Persistence.Repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/*
 * @Author: Orlando Yepes Espitia
 * @Date: 2025/26/04
 * @Description: This class implements the ClientService interface and provides methods to interact with the database.
 */
@Service("DBClient")
@ConditionalOnProperty(value = "clients.strategy", havingValue = "EN_DB")
public class ClientServiceDBImpl implements ClientService{

    // This is the repository that will be used to access the database
    @Autowired
    private final ClientRepository clientRepository;

    // Constructor injection of the ClientRepository
    public ClientServiceDBImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAllClientsSortedById();
    }

    @Override
    public Client findByClientId(Long id){
        Optional<ClientEntity> optionalClientEntity = clientRepository.findById(id);
        return optionalClientEntity
                .map(ClientConverter::toDto)
                .orElse(null);
    }

    @Override
    public List<Client> findByClientName(String clientName){
        List<ClientEntity> entities = clientRepository.findByClientName(clientName);
        return ClientConverter.toDtoList(entities);
    }

    @Transactional
    @Override
    public Client saveClient(Client client){
        ClientEntity clientEntity = ClientConverter.toEntity(client);
        ClientEntity savedEntity = clientRepository.save(clientEntity);
        client.setId(savedEntity.getId());
        return ClientConverter.toDto(savedEntity);
    }

    @Transactional
    @Override
    public Client updateClient(Client client){
        Optional<ClientEntity> optionalClientEntity = clientRepository.findById(client.getId());
        if (optionalClientEntity.isPresent()){
            ClientEntity updateEntity = clientRepository.save(ClientConverter.toEntity(client));
            return ClientConverter.toDto(updateEntity);
        }
        return null;
    }

    @Transactional
    @Override
    public Client deleteClient(Long id){
        Optional<ClientEntity> optionalClientEntity = clientRepository.findById(id);
        if (optionalClientEntity.isPresent()){
            clientRepository.deleteById(id);
            return optionalClientEntity.map(ClientConverter::toDto).get();
        }
        return null;
    }

    public boolean existByEmail(String email){
        return clientRepository.existsByClientEmail(email);
    }

}
