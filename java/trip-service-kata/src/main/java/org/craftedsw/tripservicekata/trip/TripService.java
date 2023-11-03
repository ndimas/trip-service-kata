package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    UserSession userSession = UserSession.getInstance();

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<>();
        User loggedInUser = userSession.getLoggedInUser();
        boolean isFriend = false;
        for (User friend : user.getFriends()) {
            if (friend.equals(loggedInUser)) {
                isFriend = true;
                break;
            }
        }
        if (isFriend || loggedInUser.equals(user)) {
            tripList = TripDAO.findTripsByUser(user);
        }
        return tripList;
    }
}
