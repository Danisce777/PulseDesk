package d.scerbinkinas.PulseDesk.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
public class CommentRequestDto {

     @NotBlank
     String description;
}
