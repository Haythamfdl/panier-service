package org.sid.panierservice.dao;

import org.sid.panierservice.entities.Vinyl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RepositoryRestResource
public interface VinylService extends JpaRepository<Vinyl,Long> {
    @GetMapping("/vinyls/{id}")
    @CrossOrigin
    public Vinyl findVinylById(@PathVariable(name = "id") Long id);
}
