package it.cs.unicam.MunicipalDigitalization.tests;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class tests the Municipality repository.
 */
@SpringBootTest
@Transactional
public class MunicipalRepoTest {

    @Autowired
    private MunicipalService municipalRepository;

    private Municipality municipality;

    @BeforeEach
    public void setup() {
        // Create a new Municipality before each test
        municipality = new Municipality();
        municipality.setName("Municipal");
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(1, 1));
        coordinates.add(new Coordinate(2, 2));
        coordinates.add(new Coordinate(3, 3));
        municipality.setTerritory(coordinates);
        municipalRepository.saveMunicipal(municipality);
    }

    @AfterEach
    public void tearDown() {
        // Remove the Municipality after each test
        municipalRepository.deleteMunicipalById(municipality.getId());
    }

    /**
     * Test the add of a Municipality in the Municipality repository.
     */
    @Test
    public void testAddMunicipal() {

        //Check if the Municipality is present
        assertTrue(municipalRepository.getMunicipalByName("Municipal").isPresent());

        //Print the Municipality
        System.out.println(municipalRepository.getMunicipalByName("Municipal").get().getName());
    }

    /**
     * Test the remove of a municipality in the Municipality repository.
     */
    @Test
    public void testRemoveMunicipal() {

        //Check if the Municipality is present and remove it
        municipalRepository.deleteMunicipalById(municipality.getId());

        //Check if the Municipality is present anymore
        assertTrue(municipalRepository.getMunicipalByName("Municipal").isEmpty());
    }

    /**
     * Test the update of a Municipality in the Municipality repository.
     */
    @Test
    public void testUpdateMunicipal() {

        //Get the Municipality and update it
        Municipality municipality1 = municipalRepository.getMunicipalByName("Municipal").get();
        municipality1.setName("Municipal1");
        municipalRepository.saveMunicipal(municipality1);

        //Check if the Municipality is present
        assertTrue(municipalRepository.getMunicipalByName("Municipal1").isPresent());
    }

}
