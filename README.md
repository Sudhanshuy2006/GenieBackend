## MailGenie Backend – AI Email Generation API

## MailGenie Backend is a production-ready Spring Boot REST API that generates AI-powered professional email replies.
## It serves as the core engine for both the web frontend and Chrome extension.

# ✨ Key Highlights :-
🤖 AI-powered email generation
⚡ Fast REST API response
🔐 Secure environment-based configuration
🐳 Dockerized for production
☁️ Deployed on Render Cloud
🌐 CORS handled via global config (enterprise-style)


# 🛠 Tech Stack:-
Java 21
Spring Boot
Spring Web MVC
Spring Data Jpa
REST APIs
Docker


# Render (Cloud Deployment):-
📐 Architecture
Client (Web / Extension)
        |
        
Spring Boot REST API
        |
        
AI Model (Email Generation)


# 🔗 API Endpoint:-
Generate Email Reply
POST /api/email/generate

# Request
{
  "emailContent": "Original email text",
  "tone": "Professional"
}

# Response
Generated email reply (plain text)


# 🔐 Environment Variables:-
GEMINI_API_URL=your_ai_api_url
GEMINI_API_KEY=your_ai_api_key
PORT=8080

 # No secrets hardcoded
 # Production-safe setup

# 🐳 Docker Support:-
FROM eclipse-temurin:21-jdk as builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]


# 🧪 Test API (cURL)
curl -X POST https://<backend-url>/api/email/generate \
-H "Content-Type: application/json" \
-d '{"emailContent":"Hello","tone":"Professional"}'

## Key Skill uses:-
Spring Boot · Java 21 · REST API · Docker · Cloud Deployment · Backend Engineering · Secure API Design
