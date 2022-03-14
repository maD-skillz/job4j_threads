package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStore {

    @GuardedBy("this")
    private List<User> userList = new ArrayList<>();

    public synchronized boolean add(User user) {
        boolean result = false;
        if (!userList.contains(user)) {
            result = userList.add(user);

        }
        return result;
    }

    public synchronized boolean delete(User user) {
        boolean result = false;
        if (userList.contains(user)) {
            result = userList.remove(user);
        }
        return result;
    }

    public synchronized boolean update(User user) {
        boolean result = false;
        if (userList.contains(user)) {
            delete(user);
            result = userList.add(user);
        }
        return result;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean validTransfer = false;
        User sender = null;
        User consumer = null;
        for (User userIndex : userList) {
            if (userIndex.getId() == fromId) {
                sender = userIndex;
            }
            if (userIndex.getId() == toId) {
                consumer = userIndex;
            }
        }
        if (sender != null && consumer != null) {
            sender.setAmount(sender.getAmount() - amount);
            consumer.setAmount(sender.getAmount() + amount);
            validTransfer = true;
        }
        return validTransfer;
    }

}
