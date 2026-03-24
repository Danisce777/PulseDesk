package d.scerbinkinas.PulseDesk.controller;

import d.scerbinkinas.PulseDesk.dto.CommentRequestDto;
import d.scerbinkinas.PulseDesk.dto.CommentResponseDto;
import d.scerbinkinas.PulseDesk.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto addComment(@Valid @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.addComment(commentRequestDto);
    }

    @GetMapping
    public List<CommentResponseDto> getAllComments() {
        return commentService.getAllComments();
    }

}
