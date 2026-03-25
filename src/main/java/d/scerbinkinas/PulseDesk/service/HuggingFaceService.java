package d.scerbinkinas.PulseDesk.service;

import d.scerbinkinas.PulseDesk.dto.AiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class HuggingFaceService {

    @Value("${huggingface.api.key}")
    private String apiKey;
    @Value("${huggingface.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public AiResponseDto analyzeComment(String description) {

        String prompt = """
                You are a support ticket classifier. Analyze the comment below.
                
                 Rules:
                 - isTicket = true if the comment describes a problem, error, bug, request, complaint, or anything needing support action
                 - isTicket = false ONLY for compliments, greetings, or completely irrelevant messages
                 - Be generous with isTicket = true when in doubt
                
                 Return ONLY this JSON, no explanation, no extra text:
                 {
                   "isTicket": true or false,
                   "title": "short title max 8 words",
                   "category": "BUG or FEATURE or BILLING or ACCOUNT or OTHER",
                   "priority": "HIGH or MEDIUM or LOW",
                   "summary": "one sentence summary"
                 }
                
                Comment: """ + description;

        String response = callApi(prompt);

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response, AiResponseDto.class);
        } catch (Exception e) {
            System.out.println("Parsing error: " + e.getMessage());
            return null;
        }
    }

    private String callApi(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "meta-llama/Llama-3.1-8B-Instruct");
        body.put("messages", List.of(
                Map.of("role", "user", "content", prompt)
        ));
        body.put("max_tokens", 200);
        body.put("temperature", 0.3);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, entity, Map.class);

            if (response.getBody() != null) {
                List<Map> choices = (List<Map>) response.getBody().get("choices");
                if (choices != null && !choices.isEmpty()) {
                    Map message = (Map) choices.get(0).get("message");

                    log.info((String) message.get("content"));

                    return (String) message.get("content");
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
        return null;
    }
}