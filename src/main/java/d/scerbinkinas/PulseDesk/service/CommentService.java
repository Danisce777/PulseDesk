package d.scerbinkinas.PulseDesk.service;

import d.scerbinkinas.PulseDesk.dto.AiResponseDto;
import d.scerbinkinas.PulseDesk.dto.CommentRequestDto;
import d.scerbinkinas.PulseDesk.dto.CommentResponseDto;
import d.scerbinkinas.PulseDesk.model.Comment;
import d.scerbinkinas.PulseDesk.model.Ticket;
import d.scerbinkinas.PulseDesk.repository.CommentRepository;
import d.scerbinkinas.PulseDesk.repository.TicketRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final HuggingFaceService huggingFaceService;
    private final TicketRepository ticketRepository;

    public CommentResponseDto addComment(CommentRequestDto request) {
        Comment comment = Comment.builder()
                .description(request.getDescription())
                .build();

        commentRepository.save(comment);

        AiResponseDto response = huggingFaceService.analyzeComment(request.getDescription());

        if (response != null && response.isNeedsTicket()) {
            Ticket ticket = Ticket.builder()
                    .title(response.getTitle())
                    .category(parseCategory(response.getCategory()))
                    .priority(parsePriority(response.getPriority()))
                    .summary(response.getSummary())
                    .comment(comment)
                    .build();

            ticketRepository.save(ticket);
            comment.setTicket(ticket);
            commentRepository.save(comment);
        }
        return CommentResponseDto.from(comment);
    }

    public List<CommentResponseDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(CommentResponseDto::from).toList();
    }

    private Ticket.Category parseCategory(String raw) {
        if (raw == null) return Ticket.Category.OTHER;
        try {
            return Ticket.Category.valueOf(raw.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return Ticket.Category.OTHER;
        }
    }

    private Ticket.Priority parsePriority(String raw) {
        if (raw == null) return Ticket.Priority.LOW;
        try {
            return Ticket.Priority.valueOf(raw.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return Ticket.Priority.LOW;
        }
    }
}
