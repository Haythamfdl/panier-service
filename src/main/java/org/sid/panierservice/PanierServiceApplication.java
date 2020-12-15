package org.sid.panierservice;

import org.sid.panierservice.dao.ClientService;
import org.sid.panierservice.dao.VinylService;
import org.sid.panierservice.dao.PanierItemRepository;
import org.sid.panierservice.dao.PanierRepository;
import org.sid.panierservice.entities.Client;
import org.sid.panierservice.entities.Vinyl;
import org.sid.panierservice.entities.Panier;
import org.sid.panierservice.entities.PanierItem;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PanierServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanierServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ClientService clientService, VinylService vinylService, PanierItemRepository panierItemRepository, PanierRepository panierRepository){
        return args -> {
            Vinyl v1 = vinylService.save(new Vinyl(null, "V1",10,"Country",""));
            Vinyl v2 = vinylService.save(new Vinyl(null, "V2",20,"Rock",""));
            Vinyl v3 = vinylService.save(new Vinyl(null, "V3",30,"Jazz",""));
            Vinyl v4 = vinylService.save(new Vinyl(null, "V4",40,"Rock",""));
            Client c1 = clientService.save(new Client(null,"C1","C1@gmail.com","P1",100));
            Client c2 = clientService.save(new Client(null,"C2","C2@gmail.com","P2",50));
            Panier p1= panierRepository.save(new Panier(null,c1.getId(),null,null));
            Panier p2= panierRepository.save(new Panier(null,c2.getId(),null,null));
            panierItemRepository.save(new PanierItem(null,v1.getId() ,null,1,p1));
            panierItemRepository.save(new PanierItem(null,v2.getId() ,null,2,p2));
            panierItemRepository.save(new PanierItem(null,v3.getId() ,null,4,p1));

        };
    }
}
