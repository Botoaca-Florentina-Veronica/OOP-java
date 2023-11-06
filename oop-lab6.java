// Clasa de bază pentru toate tipurile de avioane
class Airplane {
    private String planeID;
    private int totalEnginePower;

    public Airplane(String planeID, int totalEnginePower) {
        this.planeID = planeID;
        this.totalEnginePower = totalEnginePower;
    }

    public String getPlaneID() {
        return planeID;
    }

    public int getTotalEnginePower() {
        return totalEnginePower;
    }

    public void takeOff() {
        System.out.println(planeID + " - Initiating takeoff procedure - Starting engines - Accelerating down the runway - Taking off - Retracting gear - Takeoff complete");
    }

    public void fly() {
        System.out.println(planeID + " - Flying");
    }

    public void land() {
        System.out.println(planeID + " - Initiating landing procedure - Enabling airbrakes - Lowering gear - Contacting runway - Decelerating - Stopping engines - Landing complete");
    }
}

// Clasa pentru avioanele de călători
class PassengerAirplane extends Airplane {
    private int maxPassengers;

    public PassengerAirplane(String planeID, int totalEnginePower, int maxPassengers) {
        super(planeID, totalEnginePower);
        this.maxPassengers = maxPassengers;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }
}

// Clasa pentru avioanele de călători de tip Concorde
class Concorde extends PassengerAirplane {
    public Concorde(String planeID, int totalEnginePower, int maxPassengers) {
        super(planeID, totalEnginePower, maxPassengers);
    }

    public void goSuperSonic() {
        System.out.println(getPlaneID() + " - Supersonic mode activated");
    }

    public void goSubSonic() {
        System.out.println(getPlaneID() + " - Supersonic mode deactivated");
    }
}

// Clasa pentru avioanele de luptă
class FighterJet extends Airplane {
    public FighterJet(String planeID, int totalEnginePower) {
        super(planeID, totalEnginePower);
    }

    public void launchMissile() {
        System.out.println(getPlaneID() + " - Initiating missile launch procedure - Acquiring target - Launching missile - Breaking away - Missile launch complete");
    }
}

// Clasa pentru avioanele Mig
class Mig extends FighterJet {
    public Mig(String planeID, int totalEnginePower) {
        super(planeID, totalEnginePower);
    }

    public void highSpeedGeometry() {
        System.out.println(getPlaneID() + " - High speed geometry selected");
    }

    public void normalGeometry() {
        System.out.println(getPlaneID() + " - Normal geometry selected");
    }
}

// Clasa pentru avioanele TomCat
class TomCat extends FighterJet {
    public TomCat(String planeID, int totalEnginePower) {
        super(planeID, totalEnginePower);
    }

    public void refuel() {
        System.out.println(getPlaneID() + " - Initiating refueling procedure - Locating refueller - Catching up - Refueling complete");
    }
}

class Main {
    public static void main(String[] args) {

        Airplane airplane1 = new Airplane("A1", 1000);
        PassengerAirplane airplane2 = new PassengerAirplane("P1", 1500, 200);
        Concorde concorde = new Concorde("C1", 2000, 100);
        FighterJet fighterJet = new FighterJet("F1", 1800);
        Mig mig = new Mig("M1", 1600);
        TomCat tomCat = new TomCat("T1", 1700);

        airplane1.takeOff();
        airplane2.fly();
        concorde.goSuperSonic();
        fighterJet.launchMissile();
        mig.highSpeedGeometry();
        tomCat.refuel();
    }
}
