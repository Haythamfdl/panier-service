package org.sid.panierservice;

import org.sid.panierservice.dao.ClientService;
import org.sid.panierservice.dao.JeuService;
import org.sid.panierservice.dao.PanierItemRepository;
import org.sid.panierservice.dao.PanierRepository;
import org.sid.panierservice.entities.Client;
import org.sid.panierservice.entities.Jeu;
import org.sid.panierservice.entities.Panier;
import org.sid.panierservice.entities.PanierItem;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;

@SpringBootApplication
public class PanierServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanierServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ClientService clientService, JeuService jeuService, PanierItemRepository panierItemRepository, PanierRepository panierRepository){
        return args -> {
            Jeu j1 =jeuService.save(new Jeu(null, "J1",10));
            Jeu j2 =jeuService.save(new Jeu(null, "J2",20));
            Jeu j3 =jeuService.save(new Jeu(null, "J3",30));
            Jeu j4 =jeuService.save(new Jeu(null, "J4",40));
            Client c1 = clientService.save(new Client(null,"C1","P1",100));
            Client c2 = clientService.save(new Client(null,"C2","P2",50));
            Panier p1= panierRepository.save(new Panier(null,c1.getId(),null,null));
            Panier p2= panierRepository.save(new Panier(null,c2.getId(),null,null));
            panierItemRepository.save(new PanierItem(null,j1.getId() ,null,p1));
            panierItemRepository.save(new PanierItem(null,j2.getId() ,null,p2));
            panierItemRepository.save(new PanierItem(null,j3.getId() ,null,p1));

        };
    }
}
