package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ThreadSafe
public class UserStore {

    @GuardedBy("userMap")
    private Map<Integer, User> userMap = new HashMap<>();

    public synchronized User put(Integer key, User value) {
        User user = userMap.get(key);
        if (user == null) {
            user = userMap.put(key, value);
        }
        return user;
    }

    public synchronized boolean delete(Integer key, User value) {
        boolean result = false;
        if (userMap.containsKey(key) && Objects.equals(userMap.get(key), value)) {
            userMap.remove(key);
            result = true;
        }
        return result;
    }

    public synchronized User update(Integer key, User value) {
        if (userMap.containsKey(key)) {
            return userMap.put(key, value);
        }
        return null;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean validTransfer = false;
        User sender = userMap.get(fromId);
        User consumer = userMap.get(toId);
        if (sender != null && consumer != null && sender.getAmount() != 0) {
            sender.setAmount(sender.getAmount() - amount);
            consumer.setAmount(consumer.getAmount() + amount);
            validTransfer = true;
        }
        return validTransfer;
    }

}
