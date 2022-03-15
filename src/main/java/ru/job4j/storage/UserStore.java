package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStore {

    @GuardedBy("userMap")
    private Map<Integer, User> userMap = new HashMap<>();

    public synchronized boolean add(Integer key, User user) {
        boolean result = false;
        if (!userMap.containsKey(key)) {
            userMap.put(key, user);
            result = true;

        }
        return result;
    }

    public synchronized boolean delete(Integer key, User user) {
        boolean result = false;
        if (userMap.containsKey(key)) {
            userMap.remove(key, user);
            result = true;
        }
        return result;
    }

    public synchronized boolean update(Integer key, User user) {
        boolean result = false;
        if (userMap.containsKey(key)) {
            delete(key, user);
            add(key, user);
            result = true;
        }
        return result;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean validTransfer = false;
        User sender = null;
        User consumer = null;
        for (User userIndex : userMap.values()) {
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
