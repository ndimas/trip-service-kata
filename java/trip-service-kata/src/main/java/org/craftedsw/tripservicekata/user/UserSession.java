package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.springframework.lang.NonNull;

import java.util.Optional;

public class UserSession {

    private static final UserSession userSession = new UserSession();
    private User loggedInUser;

    public UserSession() {
    }
    public UserSession(User user) {
        loggedInUser = user;
    }

    public static UserSession getInstance() {
        return userSession;
    }

    @NonNull
    public User getLoggedInUser() {
        return Optional.ofNullable(loggedInUser).orElseThrow(UserNotLoggedInException::new);
    }

}
