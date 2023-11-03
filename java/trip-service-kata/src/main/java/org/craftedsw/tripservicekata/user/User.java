package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.Trip;
import org.craftedsw.tripservicekata.trip.Trips;

import java.util.List;

public class User {

    private final Trips trips = new Trips();
    private final Friends friends = new Friends();

    public void addFriend(User user) {
        friends.add(user);
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public List<Trip> trips() {
        return trips.getTrips();
    }

    public boolean isFriend(User user) {
        return friends.isFriend(user);
    }
}
