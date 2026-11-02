public class Plane {
    private String make;
    private String model;
    private int number;
    private double maxAltitude;
    private double currentAltitude;
    private boolean landingGearEnabled;

    public Plane(String make, String model, int number,
                 double maxAltitude, double currentAltitude,
                 boolean landingGearEnabled) {
        if (maxAltitude < 100 ||
                currentAltitude < 0 ||
                currentAltitude > maxAltitude ||
                (landingGearEnabled && currentAltitude > 100) ||
                (!landingGearEnabled && currentAltitude < 100)) {
            throw new IllegalArgumentException("Invalid plane configuration");
        }

        this.make = make;
        this.model = model;
        this.number = number;
        this.maxAltitude = maxAltitude;
        this.currentAltitude = currentAltitude;
        this.landingGearEnabled = landingGearEnabled;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getNumber() {
        return number;
    }

    public double getMaxAltitude() {
        return maxAltitude;
    }

    public double getCurrentAltitude() {
        return currentAltitude;
    }

    public boolean getLandingGearEnabled() {
        return landingGearEnabled;
    }

    public boolean enableLandingGear() {
        if (currentAltitude > 100) {
            return false;
        }
        landingGearEnabled = true;
        return true;
    }

    public boolean disableLandingGear() {
        if (currentAltitude < 100) {
            return false;
        }
        landingGearEnabled = false;
        return true;
    }

    public boolean changeAltitude(double meters) {
        if (!Double.isFinite(meters)) {
            return false;
        }

        double requestedAltitude = currentAltitude + meters;
        double minimum = landingGearEnabled ? 0 : 100;
        double maximum = landingGearEnabled
                ? Math.min(100, maxAltitude)
                : maxAltitude;

        double newAltitude = Math.max(minimum,
                Math.min(requestedAltitude, maximum));

        currentAltitude = newAltitude;

        return requestedAltitude == newAltitude;
    }
}

