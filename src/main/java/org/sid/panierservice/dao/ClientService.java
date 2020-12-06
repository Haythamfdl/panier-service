package org.sid.panierservice.dao;

import org.sid.panierservice.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RepositoryRestResource
public interface ClientService extends JpaRepository<Client,Long> {
    @GetMapping("/clients/{id}")
    public Client findClientById(@PathVariable(name = "id") Long id);
}
