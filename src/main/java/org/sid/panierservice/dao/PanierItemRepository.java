package org.sid.panierservice.dao;

import org.sid.panierservice.entities.Panier;
import org.sid.panierservice.entities.PanierItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RepositoryRestResource
public interface PanierItemRepository extends JpaRepository<PanierItem,Long>{
    @GetMapping("/PanierI/{panier}/{idv}")
    public PanierItem findPanierItemByPanierAndVinylID(@PathVariable(name = "panier") Panier panier, @PathVariable(name = "idv") Long idv);
}
