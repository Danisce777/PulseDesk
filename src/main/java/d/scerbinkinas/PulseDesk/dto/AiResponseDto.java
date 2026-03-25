package d.scerbinkinas.PulseDesk.dto;

import lombok.Data;

@Data
public class AiResponseDto {
    private boolean isTicket;
    private String title;
    private String category;
    private String priority;
    private String summary;
}
