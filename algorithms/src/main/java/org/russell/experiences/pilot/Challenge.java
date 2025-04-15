package org.russell.experiences.pilot;

import java.time.LocalDate;

public class Challenge {

    public static int monthlyCharge(String month, Subscription subscription, User[] users) {
        if (users.length == 0) {
            return 0;
        }
        final String[] split = month.split("-");
        final int year = Integer.parseInt(split[0]);
        final int monthNum = Integer.parseInt(split[1]);
        final LocalDate firstDayOfMonth = firstDayOfMonth(LocalDate.of(year, monthNum, 1));
        final LocalDate lastDayOfMonth = lastDayOfMonth(LocalDate.of(year, monthNum, 1));

        final int daysOfMonth = firstDayOfMonth.lengthOfMonth();
        final int dailyRate = subscription.monthlyPriceInCents / daysOfMonth;
        int total = 0;
        for(final User user : users) {
            final int activeSubscriptionDays = activeSubscriptionDays(user, firstDayOfMonth, lastDayOfMonth);
            if (activeSubscriptionDays == daysOfMonth) {
                total = total + subscription.monthlyPriceInCents;
            } else {
                total = total + (activeSubscriptionDays) * dailyRate;
            }
        }
        return total;
    }

    public static int activeSubscriptionDays(User user, LocalDate firstDayOfMonth, LocalDate lastDayOfMonth) {
        if (user.activatedOn.isAfter(lastDayOfMonth) || (user.deactivatedOn != null && user.deactivatedOn.isBefore(firstDayOfMonth))) {
            // user was not active during the month
            return 0;
        }

        final LocalDate periodStartDay = !user.activatedOn.isAfter(firstDayOfMonth) ? firstDayOfMonth : user.activatedOn;
        LocalDate periodEndDay;
        if (user.deactivatedOn == null) {
            periodEndDay = lastDayOfMonth;
        } else {
            periodEndDay = !user.deactivatedOn.isBefore(lastDayOfMonth) ? lastDayOfMonth : user.deactivatedOn;
        }

        return periodEndDay.compareTo(periodStartDay) + 1;
    }


    /*******************
     * Helper functions *
     *******************/

    /**
     Takes a LocalDate object and returns a LocalDate which is the first day
     of that month. For example:

     firstDayOfMonth(LocalDate.of(2022, 3, 17)) // => LocalDate.of(2022, 3, 1)
     **/
    public static LocalDate firstDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(1);
    }

    /**
     Takes a LocalDate object and returns a LocalDate which is the last day
     of that month. For example:

     lastDayOfMonth(LocalDate.of(2022, 3, 17)) // => LocalDate.of(2022, 3, 31)
     **/
    public static LocalDate lastDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(date.lengthOfMonth());
    }

    /**
     Takes a LocalDate object and returns a LocalDate which is the next day.
     For example:

     nextDay(LocalDate.of(2022, 3, 17)) // => LocalDate.of(2022, 3, 18)
     nextDay(LocalDate.of(2022, 3, 31)) // => LocalDate.of(2022, 4, 1)
     **/
    private static LocalDate nextDay(LocalDate date) {
        return date.plusDays(1);
    }
}




