package d.scerbinkinas.PulseDesk.service;

import d.scerbinkinas.PulseDesk.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
}
