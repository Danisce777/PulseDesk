package d.scerbinkinas.PulseDesk.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@Table(name = "tickets")
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private Category category;
    private Priority priority;
    private String summary;

    public enum Category {
        BUG, FEATURE, BILLING, ACCOUNT, OTHER
    }

    public enum Priority {
        HIGH, MEDIUM, LOW
    }
}
