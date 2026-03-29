package d.scerbinkinas.PulseDesk.repository;

import d.scerbinkinas.PulseDesk.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
