package it.cs.unicam.MunicipalDigitalization.api.model;

import it.cs.unicam.MunicipalDigitalization.api.util.MunicipalRepository;
import it.cs.unicam.MunicipalDigitalization.api.util.controllers.MapController;

import java.util.List;
import java.util.Map;

public class OSMSystem {

    private MunicipalRepository municipalRepository;

    private MapController mapController;

    public OSMSystem(MunicipalRepository municipalRepository) {
        this.municipalRepository = municipalRepository;
    }

    public List<Municipality> getMunicipalRepository() {
        return municipalRepository.getMunicipalities();
    }

    public Map getMap(Municipality municipality) {
        //TODO: implement this method to return the map of the given municipality
        throw new UnsupportedOperationException();
    }

    public Municipality getMunicipality(String id) {
        return municipalRepository.getMunicipality(id);
    }
}
