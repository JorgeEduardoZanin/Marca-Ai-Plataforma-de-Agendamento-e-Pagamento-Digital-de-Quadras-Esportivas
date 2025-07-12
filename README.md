# Marca‑Ai

Plataforma de agendamento e pagamento digital de quadras esportivas, conectando clientes e empresas de forma rápida, segura e sem burocracia.

🚧 Status: Projeto em andamento - em fase de conclusão e codificação.

---

## 📋 Descrição

O **Marca‑Ai** é um esboço de plataforma pensado para facilitar o aluguel de quadras de futebol. Permite que:

- Empresas publiquem horários disponíveis de suas quadras.  
- Usuários agendem e paguem on‑line sem necessidade de confirmação manual.  
- Todo o fluxo de agendamento, pagamento e validação ocorra de forma automatizada e segura.

Este projeto tem foco em demonstrar competências em **desenvolvimento backend** com a stack Java/Spring, arquitetura hexagonal e boas práticas de segurança, testes e desempenho.

---

## 🚀 Funcionalidades Principais

- **Cadastro e autenticação** de empresas e usuários (OAuth2 + JWT).  
- **Publicação** de horários disponíveis pelas empresas.  
- **Agendamento** instantâneo de horários pelo usuário.  
- **Pagamento digital** integrado ao fluxo de reserva.  
- **Pré‑validação de CNPJ** via API ReceitaWs (Spring Cloud OpenFeign).  
- **Consultas SQL customizadas** para otimizar performance de buscas e carregamento de dados.  
- **Documentação automática** com Swagger/OpenAPI.  
- **Testes unitários** com JUnit 5 e Mockito.  

---

## 🏗️ Arquitetura e Tecnologias

- **Java 21**  
- **Spring Boot 3** (com wrapper Maven incluído)  
- **Spring Security 6** (OAuth2 + JWT)  
- **Spring Cloud OpenFeign** (integração ReceitaWs)  
- **Arquitetura Hexagonal** (Ports & Adapters)  
- **Docker Compose** (orquestração de PostgreSQL)  
- **Swagger / OpenAPI**  
- **JUnit 5 + Mockito** (testes unitários)  

---

## 📦 Pré‑requisitos

- [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)  
- [Docker & Docker Compose](https://docs.docker.com/compose/install/)  

---

## ⚙️ Como Executar

1. **Clone este repositório:**  
   ```bash
   git clone git@github.com:<SEU_USUARIO>/Marca-Ai-Plataforma-de-Agendamento-e-Pagamento-Digital-de-Quadras-Esportivas.git
   cd Marca-Ai-Plataforma-de-Agendamento-e-Pagamento-Digital-de-Quadras-Esportivas
