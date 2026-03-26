package d.scerbinkinas.PulseDesk.controller;

import d.scerbinkinas.PulseDesk.dto.TicketResponseDto;
import d.scerbinkinas.PulseDesk.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public List<TicketResponseDto> getTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public TicketResponseDto getTicketById(@PathVariable("id") Long id) {
        return ticketService.getTicket(id);
    }
}
