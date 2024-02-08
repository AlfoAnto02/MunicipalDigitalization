package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.db.Repository.MunicipalRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MunicipalService {

    @Autowired
    private MunicipalRepository municipalRepository;

    public @NonNull Optional<Municipality> getMunicipalByID(Long id){
        return municipalRepository.findById(id);
    }

    public Optional<Municipality> getMunicipalByName(String name){
        return municipalRepository.findByName(name);
    }

    public void saveMunicipal(Municipality municipality){
        municipalRepository.save(municipality);
    }

    public void deleteMunicipal(Municipality municipality){
        municipalRepository.delete(municipality);
    }

    public void deleteMunicipalById(Long id){
        municipalRepository.deleteById(id);
    }

    public void saveAllMunicipals(Iterable<Municipality> municipalities){
        municipalRepository.saveAll(municipalities);
    }

    public void deleteAllMunicipals(Iterable<Municipality> municipalities){
        municipalRepository.deleteAll(municipalities);
    }

    public void deleteAllMunicipals(){
        municipalRepository.deleteAll();
    }

    public Iterable<Municipality> getAllMunicipals(){
        return municipalRepository.findAll();
    }


}
