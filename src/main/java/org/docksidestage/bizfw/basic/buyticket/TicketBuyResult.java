package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult {
    private final int handedMoney;
    private int price;

    public TicketBuyResult(int Money,int price) {
        this.handedMoney = Money;
        this.price = price;
    }
    public Ticket getTicket() {
        Ticket x=new Ticket(price);
        return x;
    }

    public int getChange() {
        return handedMoney-price;
    }
}
