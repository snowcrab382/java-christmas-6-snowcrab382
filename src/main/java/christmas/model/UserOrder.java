package christmas.model;

import java.util.LinkedHashMap;

public class UserOrder {

    private int reservationDate;
    private LinkedHashMap<String, Integer> reservationOrder;

    public UserOrder(int reservationDate, LinkedHashMap<String, Integer> reservationOrder) {
        this.reservationDate = reservationDate;
        this.reservationOrder = reservationOrder;
    }

    public int getReservationDate() {
        return reservationDate;
    }

    public LinkedHashMap<String, Integer> getReservationOrder() {
        return reservationOrder;
    }

}
