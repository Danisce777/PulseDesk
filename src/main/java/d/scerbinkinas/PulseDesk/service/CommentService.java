package d.scerbinkinas.PulseDesk.service;

import d.scerbinkinas.PulseDesk.dto.CommentRequestDto;
import d.scerbinkinas.PulseDesk.dto.CommentResponseDto;
import d.scerbinkinas.PulseDesk.model.Comment;
import d.scerbinkinas.PulseDesk.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentResponseDto addComment(CommentRequestDto request) {
        Comment comment = Comment.builder()
                .description(request.getDescription())
                .build();

        commentRepository.save(comment);
        return CommentResponseDto.from(comment);
    }

    public List<CommentResponseDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(CommentResponseDto::from).toList();
    }



}
