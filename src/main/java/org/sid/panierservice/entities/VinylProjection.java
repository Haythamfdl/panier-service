package org.sid.panierservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "Vinyl", types = Vinyl.class)
public interface VinylProjection extends Projection {
    public  Long getId();
    public  String getNom();
    public  double getPrix();
}
