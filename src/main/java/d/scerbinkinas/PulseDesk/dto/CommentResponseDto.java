package d.scerbinkinas.PulseDesk.dto;

import d.scerbinkinas.PulseDesk.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Builder
@Data
public class CommentResponseDto {

    Long id;
    OffsetDateTime dateCreated;
    String description;

    public static CommentResponseDto from(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .dateCreated(comment.getDateCreated())
                .description(comment.getDescription())
                .build();
    }
}
