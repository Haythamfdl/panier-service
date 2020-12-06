package org.sid.panierservice.dao;

import org.sid.panierservice.entities.Jeu;
import org.sid.panierservice.entities.Panier;
import org.sid.panierservice.entities.PanierItem;
import org.springframework.beans.factory.annotation.Autowired;
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
    private  JeuService jeuService;
    @Autowired
    private  ClientService clientService;
    @GetMapping("/fullPanier/{id}")
    public Panier getPanier(@PathVariable(name = "id") Long id){
        Panier panier=panierRepository.findById(id).get();
        panier.setClient(clientService.findClientById(panier.getClientID()));
        panier.getPanierItems().forEach(pi->{
            pi.setJeu(jeuService.findJeuById(pi.getJeuID()));
        });
        return panier;
    }

    @GetMapping("/AjouterPanier/{idp}/{idj}")
    public void ajouterPanier(@PathVariable(name = "idp") Long idp, @PathVariable(name = "idj") Long idj){
        Panier panier = panierRepository.findById(idp).get();
        panier.setClient(clientService.findClientById(panier.getClientID()));
        Jeu jeu= jeuService.findJeuById(idj);
        panierItemRepository.save(new PanierItem(null,jeu.getId() ,null,panier));
        System.out.println("Le Jeu a été ajouter a votre panier");
    }

    @GetMapping("/RetirerJeuP/{idp}/{idj}")
    public void retirerJeuP(@PathVariable(name = "idp") Long idp, @PathVariable(name = "idj") Long idj){
        Panier panier = panierRepository.findById(idp).get();
        panier.setClient(clientService.findClientById(panier.getClientID()));
        Jeu jeu= jeuService.findJeuById(idj);
        PanierItem panierItem = panierItemRepository.findPanierItemByPanierAndJeuID(panier,jeu.getId());
        panierItemRepository.delete(panierItem);
        System.out.println("Le Jeu a été retirer a votre panier");
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
