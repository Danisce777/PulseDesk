# PulseDesk - Backend

Spring Boot REST API that accepts user comments and uses Hugging Face AI to decide whether a comment should become a support ticket.

## Live API
https://pulsedesk-backend-brc8.onrender.com/api

## Tech Stack
- Java 21, Spring Boot, Spring Data JPA, H2, Hugging Face Inference API

## Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/comments` | Submit a comment |
| `GET` | `/api/comments` | Get all comments |
| `GET` | `/api/tickets` | Get all tickets |
| `GET` | `/api/tickets/{id}` | Get ticket by ID |

## Running Locally
```bash
export HUGGINGFACE_API_KEY=hf_your_token
export HUGGINGFACE_API_URL=https://router.huggingface.co/v1/chat/completions
./mvnw spring-boot:run
```
