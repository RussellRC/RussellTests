package org.russell.experiences.pilot;

public class Subscription {
    public Subscription() {}
    public Subscription(int id, int customerId, int monthlyPriceInCents) {
        this.id = id;
        this.customerId = customerId;
        this.monthlyPriceInCents = monthlyPriceInCents;
    }

    public int id;
    public int customerId;
    public int monthlyPriceInCents;
}