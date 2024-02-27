package it.cs.unicam.MunicipalDigitalization.api.util;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractContent;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
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
    public static boolean containsSpecialCharacters(String str) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]+");
        return pattern.matcher(str).matches();
    }

    /**
     * This method checks if a string is a link
     *
     * @param str the string to check
     * @return true if the string is a link, false otherwise
     */
    public static boolean isLink(String str) {
        String urlPattern = "^(http(s)?://)?([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}(/[a-zA-Z0-9-_.~%]+)*(/\\?[a-zA-Z0-9-_.~&=]+)?$";
        return Pattern.matches(urlPattern, str);
    }

    /**
     * This method checks if a POI is similar to a POI present in a list of POIs
     *
     * @param poi     POI to check if it is similar to a POI in the list
     * @param poiList list of POIs
     * @return true if the POI is similar to a POI in the list, false otherwise
     */

    public static boolean isPOISimilarToPoiList(AbstractPOI poi, List<AbstractPOI> poiList) {
        for (AbstractPOI p : poiList) {
            if (poi.getName().equalsIgnoreCase(p.getName()) && poi.getType().equals(p.getType()) && poi.getMunicipality().equals(p.getMunicipality()) && poi.getCoordinate().equals(p.getCoordinate()) && p != poi)
                return true;
        }
        return false;
    }

    /**
     * This method checks if an itinerary is similar to an itinerary present in a list of itineraries
     *
     * @param itinerary     itinerary to check if it is similar to an itinerary in the list
     * @param itineraryList list of itineraries
     * @return true if the itinerary is similar to an itinerary in the list, false otherwise
     */

    public static boolean isItinerarySimilarToItineraryList(AbstractItinerary itinerary, List<AbstractItinerary> itineraryList) {
        for (AbstractItinerary i : itineraryList) {
            if (itinerary.getName().equalsIgnoreCase(i.getName()) && itinerary.getMunicipality().equals(i.getMunicipality()) && itinerary.getCoordinate().equals(i.getCoordinate()) && itinerary.getPOIs().equals(i.getPOIs()) && i != itinerary)
                return true;
        }
        return false;
    }

    /**
     * This method checks if a content is similar to a content present in a list of contents
     *
     * @param content     content to check if it is similar to a content in the list
     * @param contentList list of contents
     * @return true if the content is similar to a content in the list, false otherwise
     */
    public static boolean isContentSimilarToContentList(AbstractContent content, List<AbstractContent> contentList) {
        for (AbstractContent c : contentList) {
            if (content.getType().equals(c.getType()) && (content.getPhoto().equals(c.getPhoto()) && content.getLink().equals(c.getLink()) && content.getDescription().equals(c.getDescription())) && (content.getReferredItinerary().getId().equals(c.getReferredItinerary().getId()) && content.getReferredPOI().getId().equals(c.getReferredPOI().getId())) && c != content)
                return true;
        }
        return false;
    }

    /**
     * This method checks if a municipality is similar to a municipality present in a list of municipalities
     *
     * @param municipality     municipality to check if it is similar to a municipality in the list
     * @param municipalityList list of municipalities
     * @return true if the municipality is similar to a municipality in the list, false otherwise
     */
    public static boolean isMunicipalSimilarToMunicipalityList(Municipality municipality, List<Municipality> municipalityList) {
        for (Municipality m : municipalityList) {
            if (municipality.getName().equalsIgnoreCase(m.getName()) && municipality.getTerritory().equals(m.getTerritory()) && m != municipality)
                return true;
        }
        return false;
    }

    public static double crossProduct(Coordinate a, Coordinate b) {
        return a.getX() * b.getY() - a.getY() * b.getX();
    }

    public static boolean isInsidePolygon(List<Coordinate> polygon, Coordinate p) {
        int n = polygon.size();

        double cross = crossProduct(new Coordinate(polygon.getFirst().getX() - p.getX(), polygon.getFirst().getY() - p.getY()), new Coordinate(polygon.get(1).getX() - p.getX(), polygon.get(1).getY() - p.getY()));

        for (int i = 1; i < n; i++) {
            double newCross = crossProduct(new Coordinate(polygon.get(i).getX() - p.getX(), polygon.get(i).getY() - p.getY()), new Coordinate(polygon.get((i + 1) % n).getX() - p.getX(), polygon.get((i + 1) % n).getY() - p.getY()));

            if (cross * newCross < 0) return false;
        }

        return true;
    }
}
