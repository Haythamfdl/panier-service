package org.sid.panierservice.dao;

import org.sid.panierservice.entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PanierRepository extends JpaRepository<Panier,Long> {
}
