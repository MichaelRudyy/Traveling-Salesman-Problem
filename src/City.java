import javax.xml.ws.soap.MTOM;

/**
 * Created by Michael Rudyy on 04-Apr-17.
 */
public class City {
    private static final double EARTH_EQUATORAIL_RADIUS = 6378.1370D;
    private static final double CONVERT_DEGREES_TO_RADIANS = Math.PI / 180D;
    private static final double CONVERT_KM_TO_MILES = 0.621371;
    private double longtitude;
    private double latitude;
    private String name;

    public City(String name, double latitude, double longtitude) {
        this.name = name;
        this.latitude = latitude * CONVERT_DEGREES_TO_RADIANS;
        this.longtitude = longtitude * CONVERT_DEGREES_TO_RADIANS;
    }

    public String getName() {
        return name;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String toString() {
        return getName();
    }

    public double measureDistance(City city) {
        double deltaLongitude = (city.getLongtitude() - this.getLongtitude());
        double deltaLatitude = (city.getLatitude() - this.getLatitude());
        double a = Math.pow(Math.sin(deltaLatitude / 2D), 2D) +
                Math.cos(this.getLatitude())
                        * Math.cos(city.getLatitude())
                        * Math.pow(Math.sin(deltaLongitude / 2D), 2D);
        return CONVERT_KM_TO_MILES * EARTH_EQUATORAIL_RADIUS * 2D *Math.atan2(Math.sqrt(a),Math.sqrt(1D - a));
    }
}
