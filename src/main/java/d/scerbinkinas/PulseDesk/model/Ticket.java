package d.scerbinkinas.PulseDesk.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private String summary;

    public enum Category {
        BUG, FEATURE, BILLING, ACCOUNT, OTHER
    }

    public enum Priority {
        HIGH, MEDIUM, LOW
    }

    @OneToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;
}
