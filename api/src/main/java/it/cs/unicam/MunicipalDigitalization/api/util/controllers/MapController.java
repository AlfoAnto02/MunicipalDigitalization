package it.cs.unicam.MunicipalDigitalization.api.util.controllers;

import it.cs.unicam.MunicipalDigitalization.api.io.TouristView;
import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.OSMSystem;

import java.util.List;

public class MapController {

    private final TouristView touristView;

    private final OSMSystem osmSystem;

    public MapController(TouristView touristView, OSMSystem osmSystem) {
        this.touristView = touristView;
        this.osmSystem = osmSystem;
    }

    public List<Municipality> getMunicipalities() {
        return osmSystem.getMunicipalRepository();
    }

    public Municipality getMap(String id) {
        //TODO: implement this method to return the map of the given municipality
        throw new UnsupportedOperationException();
    }
}
