package org.russell.experiences.pilot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChallengeTest {

    private Subscription plan;
    private User[] users;

    @BeforeEach
    void beforeEach() {
        plan = new Subscription(1, 1, 5000);
        users = new User[]{
                new User(1, "Employee #1", LocalDate.of(2019, 1, 1), null, 1),
                new User(2, "Employee #2", LocalDate.of(2019, 1, 1), null, 1)
        };
    }

    @Test
    public void worksWhenNoUsersAreActive() {
        assertEquals(0, Challenge.monthlyCharge("2018-10", plan, users));
    }

    @Test
    public void worksWhenTheActiveUsersAreActiveTheEntireMonth() {
        float expectedUserCount = 2;
        assertEquals(expectedUserCount * 5000, Challenge.monthlyCharge("2020-12", plan, users), 1);
    }
}
