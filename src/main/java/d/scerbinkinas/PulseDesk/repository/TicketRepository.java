package d.scerbinkinas.PulseDesk.repository;

import d.scerbinkinas.PulseDesk.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
