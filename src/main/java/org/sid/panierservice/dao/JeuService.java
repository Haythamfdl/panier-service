package org.sid.panierservice.dao;

import org.sid.panierservice.entities.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RepositoryRestResource
public interface JeuService extends JpaRepository<Jeu,Long> {
    @GetMapping("/jeus/{id}")
    public Jeu findJeuById(@PathVariable(name = "id") Long id);
}
