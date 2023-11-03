package org.craftedsw.tripservicekata.user;

import java.util.ArrayList;
import java.util.List;

public class Friends {
    private final List<User> friendList = new ArrayList<>();

    public void add(User user) {
        friendList.add(user);
    }

    public boolean isFriend(User user) {
        return friendList //
                .stream()  //
                .anyMatch(friend -> friend.equals(user));
    }

}
