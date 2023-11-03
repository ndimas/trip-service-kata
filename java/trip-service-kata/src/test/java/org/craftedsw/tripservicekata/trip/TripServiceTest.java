package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {
    @InjectMocks
    private TripService tripService;

    @Test
    public void anonymousUserShouldReceiveAnException() {
        User anonymousUser = new User();
        UserSession userSession = mock(UserSession.class);
        when(userSession.getLoggedUser()).thenReturn(null);
        tripService.userSession = userSession;

        assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(anonymousUser));
    }

    @Test
    public void userShouldBeFriendsWithHimselfToViewTrip() {
        User user = new User();
        user.addFriend(user);
        UserSession userSession = mock(UserSession.class);
        tripService.userSession = userSession;
        when(userSession.getLoggedUser()).thenReturn(user);

        assertThrows(CollaboratorCallException.class, () -> tripService.getTripsByUser(user));
    }
}
