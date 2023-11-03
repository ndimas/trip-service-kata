package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {
    @InjectMocks
    private TripService tripService;

    @Test
    @DisplayName("AK 1: You need to be logged in to see the content")
    void userShouldBeLoggedInToViewTheirTrips() {
        User loggedInUser = new User();
        Trip trip = new Trip();
        loggedInUser.addTrip(trip);
        tripService.userSession = new UserSession(loggedInUser);

        List<Trip> result = tripService.getTripsByUser(loggedInUser);

        assertEquals(singletonList(trip), result);
    }

    @Test
    @DisplayName("AK 2: You need to be a friend to see someone else's trips")
    void loggedInUserShouldBeFriendsToViewTrips() {
        User loggedInUser = new User();
        User loggedInUsersFriend = new User();
        Trip friendsTrip = new Trip();
        loggedInUsersFriend.addTrip(friendsTrip);
        loggedInUsersFriend.addFriend(loggedInUser);
        tripService.userSession = new UserSession(loggedInUser);

        List<Trip> result = tripService.getTripsByUser(loggedInUsersFriend);

        assertEquals(singletonList(friendsTrip), result);
    }

    @Test
    @DisplayName("A logged in user cannot see other users trips, if they are not friends")
    void loggedInUserShouldNotViewOtherUsersTrips() {
        User loggedInUser = new User();
        User otherUser = new User();
        Trip otherUserTrip = new Trip();
        otherUser.addTrip(otherUserTrip);
        tripService.userSession = new UserSession(loggedInUser);

        List<Trip> result = tripService.getTripsByUser(otherUser);

        assertEquals(emptyList(), result);
    }

    @Test
    @DisplayName("AK 3: If you are not logged in, the code throws an exception")
    void anonymousUserShouldReceiveAnException() {
        User anonymousUser = new User();

        assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(anonymousUser));
    }
}
