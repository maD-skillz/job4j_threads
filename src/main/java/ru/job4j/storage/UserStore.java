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

    public synchronized boolean put(Integer key, User value) {
        return userMap.putIfAbsent(key, value) == null;
    }

    public synchronized boolean delete(Integer key, User value) {
        return userMap.remove(key, value);
    }

    public synchronized boolean update(Integer key, User value) {
        return userMap.replace(key, value) != null;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean validTransfer = false;
        User sender = userMap.get(fromId);
        User consumer = userMap.get(toId);
        if (sender != null && consumer != null && sender.getAmount() >= 1 && sender.getAmount() <= amount) {
            sender.setAmount(sender.getAmount() - amount);
            consumer.setAmount(consumer.getAmount() + amount);
            validTransfer = true;
        }
        return validTransfer;
    }

}
