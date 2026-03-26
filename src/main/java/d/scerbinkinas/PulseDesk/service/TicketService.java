package d.scerbinkinas.PulseDesk.service;

import d.scerbinkinas.PulseDesk.dto.TicketResponseDto;
import d.scerbinkinas.PulseDesk.exception.TicketNotFoundException;
import d.scerbinkinas.PulseDesk.model.Ticket;
import d.scerbinkinas.PulseDesk.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketResponseDto getTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException("Ticket not found with id " + id));
        return TicketResponseDto.from(ticket);
    }

    public List<TicketResponseDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(TicketResponseDto::from).toList();
    }
}
