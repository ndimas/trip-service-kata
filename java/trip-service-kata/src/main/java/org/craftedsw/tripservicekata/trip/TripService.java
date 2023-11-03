package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    UserSession userSession = UserSession.getInstance();

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedInUser = userSession.getLoggedInUser();
        boolean isLoggedInUserAFriend = user.isFriend(loggedInUser);
        boolean allowedToViewTrips = isLoggedInUserAFriend || loggedInUser.equals(user);
        if (allowedToViewTrips) {
            return TripDAO.findTripsByUser(user);
        }
        return new ArrayList<>();
    }
}
