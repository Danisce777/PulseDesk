package d.scerbinkinas.PulseDesk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AiResponseDto {

    @JsonProperty("isTicket")
    private boolean needsTicket;
    private String title;
    private String category;
    private String priority;
    private String summary;
}
