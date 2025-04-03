package ru.ftptpf.dto;

public record TicketFilter(int limit, int offset, String seatNo, String passengerName) {
}
