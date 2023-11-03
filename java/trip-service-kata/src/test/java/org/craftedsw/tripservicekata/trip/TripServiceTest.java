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

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {
    @InjectMocks
    private TripService tripService;

    @Test
    @DisplayName("AK 1: You need to be logged in to see the content")
    public void userShouldBeLoggedInToViewTheirTrips() {
        User user = new User();
        Trip trip = new Trip();
        user.addTrip(trip);
        UserSession userSession = mock(UserSession.class);
        tripService.userSession = userSession;
        when(userSession.getLoggedUser()).thenReturn(user);

        List<Trip> result = tripService.getTripsByUser(user);

        assertEquals(singletonList(trip), result);
    }

    @Test
    @DisplayName("AK 3: If you are not logged in, the code throws an exception")
    public void anonymousUserShouldReceiveAnException() {
        User anonymousUser = new User();
        UserSession userSession = mock(UserSession.class);
        when(userSession.getLoggedUser()).thenReturn(null);
        tripService.userSession = userSession;

        assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(anonymousUser));
    }

    @Test
    @DisplayName("AK 2: You need to be a friend to see someone else's trips")
    public void loggedInUserShouldBeFriendsToViewTrips() {
        User loggedInUser = new User();
        User loggedInUsersFriend = new User();
        Trip friendsTrip = new Trip();
        loggedInUsersFriend.addTrip(friendsTrip);
        loggedInUsersFriend.addFriend(loggedInUser);
        UserSession userSession = mock(UserSession.class);
        tripService.userSession = userSession;
        when(userSession.getLoggedUser()).thenReturn(loggedInUser);

        List<Trip> result = tripService.getTripsByUser(loggedInUsersFriend);

        assertEquals(singletonList(friendsTrip), result);
    }
}
