package ru.ftptpf;

import ru.ftptpf.dao.TicketDao;
import ru.ftptpf.dto.TicketFilter;
import ru.ftptpf.entity.Ticket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class DaoRunner {

    public static void main(String[] args) {
        /*        saveTest();*/
        /*        deleteTest();*/
        /*        updateTest();*/
        /*        findAllTest();*/
        /*        findAllWithFilterTest();*/
        findJoinTest();
    }

    private static void findJoinTest() {
        TicketDao ticketDao = TicketDao.getInstance();
        Optional<Ticket> maybeTicket = ticketDao.findById(7L);
        System.out.println(maybeTicket);
    }

    private static void findAllWithFilterTest() {
        TicketDao ticketDao = TicketDao.getInstance();
        TicketFilter ticketFilter = new TicketFilter(2, 1, "A1", null);
        List<Ticket> tickets = ticketDao.findAll(ticketFilter);
        System.out.println(tickets);
    }

    private static void findAllTest() {
        TicketDao ticketDao = TicketDao.getInstance();
        List<Ticket> tickets = ticketDao.findAll();
        System.out.println(tickets);
    }

    private static void updateTest() {
        TicketDao ticketDao = TicketDao.getInstance();
        Optional<Ticket> maybeTicket = ticketDao.findById(7L);
        System.out.println(maybeTicket);
        maybeTicket.ifPresent(ticket -> {
            ticket.setCost(BigDecimal.valueOf(222L));
            ticketDao.update(ticket);
        });
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
//        ticket.setFlight(11L);
        ticket.setSeatNo("D2");
        ticket.setCost(BigDecimal.valueOf(350));

        Ticket savedTicket = ticketDao.save(ticket);
        System.out.println(savedTicket);
    }
}
