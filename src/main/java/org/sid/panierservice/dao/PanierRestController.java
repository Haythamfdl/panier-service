package org.sid.panierservice.dao;

import org.sid.panierservice.entities.Vinyl;
import org.sid.panierservice.entities.Panier;
import org.sid.panierservice.entities.PanierItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PanierRestController {
    @Autowired
    private  PanierRepository panierRepository;
    @Autowired
    private  PanierItemRepository panierItemRepository;
    @Autowired
    private VinylService vinylService;
    @Autowired
    private  ClientService clientService;
    @GetMapping("/fullPanier/{id}")
    @CrossOrigin
    public Panier getPanier(@PathVariable(name = "id") Long id){
        Panier panier=panierRepository.findById(id).get();
        panier.setClient(clientService.findClientById(panier.getClientID()));
        panier.getPanierItems().forEach(pi->{
            pi.setVinyl(vinylService.findVinylById(pi.getVinylID()));
        });
        return panier;
    }

    @GetMapping("/AjouterPanier/{idp}/{idv}/{q}")
    @CrossOrigin
    public void ajouterPanier(@PathVariable(name = "idp") Long idp, @PathVariable(name = "idv") Long idv,@PathVariable(name = "q") Integer q){
        Panier panier = panierRepository.findById(idp).get();
        panier.setClient(clientService.findClientById(panier.getClientID()));
        Vinyl vinyl = vinylService.findVinylById(idv);
        panierItemRepository.save(new PanierItem(null, vinyl.getId() ,null,q,panier));
        System.out.println("Le Vinyl a été ajouter a votre panier");
    }

    @GetMapping("/RetirerJeuP/{idp}/{idv}")
    @CrossOrigin
    public void retirerJeuP(@PathVariable(name = "idp") Long idp, @PathVariable(name = "idv") Long idv){
        Panier panier = panierRepository.findById(idp).get();
        panier.setClient(clientService.findClientById(panier.getClientID()));
        Vinyl vinyl = vinylService.findVinylById(idv);
        PanierItem panierItem = panierItemRepository.findPanierItemByPanierAndVinylID(panier, vinyl.getId());
        panierItemRepository.delete(panierItem);
        System.out.println("Le Vinyl a été retirer a votre panier");
    }

    @GetMapping("/viderPanier/{idp}")
    public void retirerJeuP(@PathVariable(name = "idp") Long idp){
        Panier panier = panierRepository.findById(idp).get();
        panier.setClient(clientService.findClientById(panier.getClientID()));
        panier.getPanierItems().forEach(pi->{
            panierItemRepository.delete(pi);
        });
        System.out.println("Votre panier a été vider");
    }
}
