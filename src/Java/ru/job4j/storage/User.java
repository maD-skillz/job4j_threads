package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;

public final class User {

    @GuardedBy("this")
    private final int id;

    @GuardedBy("this")
    private int amount;


    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    @GuardedBy("this")
    public int getId() {
        return id;
    }

    @GuardedBy("this")
    public int getAmount() {
        return amount;
    }

    @GuardedBy("this")
    public void setAmount(int amount) {
        this.amount = amount;
    }


}
