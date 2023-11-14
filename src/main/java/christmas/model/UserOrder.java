package christmas.model;

import java.util.Map;

public class UserOrder {

    private int reservationDate;
    private Map<String, Integer> reservationOrder;

    public UserOrder(int reservationDate, Map<String, Integer> reservationOrder) {
        this.reservationDate = reservationDate;
        this.reservationOrder = reservationOrder;
    }

    public int getReservationDate() {
        return reservationDate;
    }

    public Map<String, Integer> getReservationOrder() {
        return reservationOrder;
    }

}
