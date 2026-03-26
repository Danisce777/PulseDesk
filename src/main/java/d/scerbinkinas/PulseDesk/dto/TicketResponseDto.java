package d.scerbinkinas.PulseDesk.dto;


import d.scerbinkinas.PulseDesk.model.Ticket;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TicketResponseDto {

    String title;

    @Enumerated(EnumType.STRING)
    Ticket.Category category;

    @Enumerated(EnumType.STRING)
    Ticket.Priority priority;

    String summary;

    enum Category {
        BUG, FEATURE, BILLING, ACCOUNT, OTHER
    }

    enum Priority {
        HIGH, MEDIUM, LOW
    }

    public static TicketResponseDto from(Ticket ticket) {
        return TicketResponseDto.builder()
                .title(ticket.getTitle())
                .category(ticket.getCategory())
                .priority(ticket.getPriority())
                .summary(ticket.getSummary())
                .build();
    }
}
