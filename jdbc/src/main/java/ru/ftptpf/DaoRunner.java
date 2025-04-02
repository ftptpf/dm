package ru.ftptpf;

import ru.ftptpf.dao.TicketDao;
import ru.ftptpf.entity.Ticket;

import java.math.BigDecimal;

public class DaoRunner {

    public static void main(String[] args) {
/*        saveTest();*/
        deleteTest();
    }

    private static void deleteTest() {
        TicketDao ticketDao = TicketDao.getInstance();
        boolean result = ticketDao.delete(12L);
        System.out.println(result);
    }

    private static void saveTest() {
        TicketDao ticketDao = TicketDao.getInstance();
        Ticket ticket = new Ticket();
        ticket.setPassengerNo("1234567890");
        ticket.setPassengerName("Кузьма Фомич");
        ticket.setFlightId(11L);
        ticket.setSeatNo("D2");
        ticket.setCost(BigDecimal.valueOf(350));

        Ticket savedTicket = ticketDao.save(ticket);
        System.out.println(savedTicket);
    }
}
