package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

public class Trips {
    private final List<Trip> tripList = new ArrayList<>();

    public void add(Trip trip) {
        tripList.add(trip);
    }
    public List<Trip> getTrips() {
        return tripList;
    }
}
