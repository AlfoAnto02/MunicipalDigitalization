package it.cs.unicam.MunicipalDigitalization.api.util;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.*;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D;

import java.awt.*;
import java.util.*;
import java.util.List;
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

    public static boolean isContestSimilarToContestList(ContributionContest contest, List<ContributionContest> contestList) {
        for (ContributionContest c : contestList) {
            if (contest.getInvitationType().equals(c.getInvitationType()) && contest.getPois().equals(c.getPois()) && contest.getItineraries().equals(c.getItineraries()) && contest.getContestType().equals(c.getContestType()) && c != contest)
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
            if (content.getType().equals(c.getType()) && content.getContent().equals(c.getContent())
                    && (content.getReferredItinerary().getId().equals(c.getReferredItinerary().getId())
                    && content.getReferredPOI().getId().equals(c.getReferredPOI().getId())) && c != content)
                return true;
        }
        return false;
    }

    public static boolean isContributionSimilatrToAContributionList(Contribution contribution, List<Contribution> contributionList) {
        for (Contribution c : contributionList) {
            if (contribution.getContest().equals(c.getContest()) && contribution.getContribution().equals(c.getContribution()) && c != contribution)
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

    public static void sortCoordinates(List<Coordinate> coordinates){
        double totalX = 0;
        double totalY = 0;
        for(Coordinate c : coordinates){
            totalX += c.getX();
            totalY += c.getY();
        }
        double centerX = totalX / coordinates.size();
        double centerY = totalY / coordinates.size();

        Coordinate centroid = new Coordinate(centerX, centerY);
        coordinates.sort(new Comparator<Coordinate>() {
            public int compare(Coordinate c1, Coordinate c2) {
                double angle1 = Math.atan2(c1.getY() - centroid.getY(), c1.getX() - centroid.getX());
                double angle2 = Math.atan2(c2.getY() - centroid.getY(), c2.getX() - centroid.getX());
                return Double.compare(angle1, angle2);
            }
        });
    }


    public static boolean isMunicipalityInsideAMunicipalityList(Municipality municipality, List<Municipality> municipalityList) {
        Figura f1 = new Figura(municipality.getTerritory());
        for (Municipality m : municipalityList) {
            Figura f2 = new Figura(m.getTerritory());
            if (f1.isInside(f2)) {
                return true;
            }
        }
        return false;
    }


    /**
     * This class is used to check if a Municipality collides with the Figure of another Municipality
     */
    static class Figura {
        List<Coordinate> coordinates;
        Rectangle boundingBox;

        public Figura(List<Coordinate> coordinates) {
            this.coordinates = coordinates;
            this.boundingBox = calculateBoundingBox(coordinates);
        }

        private Rectangle calculateBoundingBox(List<Coordinate> coordinates) {
            double minX = Double.MAX_VALUE;
            double minY = Double.MAX_VALUE;
            double maxX = Double.MIN_VALUE;
            double maxY = Double.MIN_VALUE;

            for (Coordinate c : coordinates) {
                minX = Math.min(minX, c.getX());
                minY = Math.min(minY, c.getY());
                maxX = Math.max(maxX, c.getX());
                maxY = Math.max(maxY, c.getY());
            }
            return new Rectangle((int) minX, (int) minY, (int) (maxX - minX), (int) (maxY - minY));
        }

        public boolean isInside(Figura f) {
            if (!this.boundingBox.intersects(f.boundingBox)) {
                return false;
            }
            return intersectionPoints(f) || f.intersectionPoints(this);
        }

        private boolean intersectionPoints(Figura f) {
            for (Coordinate c : this.coordinates) {
                if (f.pointInFigura(c)) {
                    return true;
                }
            }
            return false;
        }

        private boolean pointInFigura(Coordinate c) {
            int j = this.coordinates.size() - 1;
            boolean inside = false;
            for (int i = 0; i < this.coordinates.size(); i++) {
                if ((this.coordinates.get(i).getY() < c.getY() && this.coordinates.get(j).getY() >= c.getY()
                        || this.coordinates.get(j).getY() < c.getY() && this.coordinates.get(i).getY() >= c.getY()) &&
                        (this.coordinates.get(i).getX() <= c.getX() || this.coordinates.get(j).getX() <= c.getX())) {
                    inside ^= (this.coordinates.get(i).getX() + (c.getY() - this.coordinates.get(i).getY())
                            / (this.coordinates.get(j).getY() - this.coordinates.get(i).getY()) * (this.coordinates.get(j).getX()
                            - this.coordinates.get(i).getX()) < c.getX());
                }
                j = i;
            }
            return inside;
        }

    }
}
