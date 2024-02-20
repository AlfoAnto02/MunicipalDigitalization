package it.cs.unicam.MunicipalDigitalization.api.util;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * This class represents a sort of utility class for matching algorithms.
 */
public class MatchingAlgorithms {

    /**
     * This method returns a list of unique types of a list of POIType
     *
     * @param pois the list of POIs
     * @return a list of unique types
     */
    public static List<String> uniqueTypes(List<AbstractPOI> pois) {
        Set<String> uniqueTypes = new HashSet<>();
        for (AbstractPOI poi : pois) {
            uniqueTypes.add(poi.getType().toString());
        }
        return new ArrayList<>(uniqueTypes);
    }

    /*
    * This method checks if a string contains special characters
     */
    public static boolean containsSpecialCharacters(String str){
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]+");
        return pattern.matcher(str).matches();
    }

    /**
     * This method checks if a string is a link
     *
     * @param str the string to check
     * @return true if the string is a link, false otherwise
     */
    public static boolean isLink(String str){
        String urlPattern = "^(http(s)?://)?([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}(/[a-zA-Z0-9-_.~%]+)*(/\\?[a-zA-Z0-9-_.~&=]+)?$";
        return Pattern.matches(urlPattern, str);
    }
}
