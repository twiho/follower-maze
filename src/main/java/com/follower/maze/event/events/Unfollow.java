package com.follower.maze.event.events;

import com.follower.maze.users.User;

import java.io.IOException;
import java.util.Map;

public class Unfollow extends Event {

    private final int sequenceNumber;
    private final int fromUserId;
    private final int toUserId;

    private final String event;

    public Unfollow(int sequenceNumber, String event, int fromUserId, int toUserId) {
        this.sequenceNumber = sequenceNumber;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.event = event;
    }

    @Override
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    @Override
    public void notifyUsers(Map<Integer, User> users) throws IOException {
        final User fromUser = users.get(fromUserId);
        final User toUser = users.get(toUserId);
        if (toUser != null) {
            toUser.removeFollower(fromUser);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Unfollow unfollow = (Unfollow) o;

        if (fromUserId != unfollow.fromUserId) return false;
        if (sequenceNumber != unfollow.sequenceNumber) return false;
        if (toUserId != unfollow.toUserId) return false;
        if (event != null ? !event.equals(unfollow.event) : unfollow.event != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sequenceNumber;
        result = 31 * result + fromUserId;
        result = 31 * result + toUserId;
        result = 31 * result + (event != null ? event.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Unfollow{" +
                "sequenceNumber=" + sequenceNumber +
                ", fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                ", event='" + event + '\'' +
                '}';
    }
}
