# 📬 Digital Mailbox – Spring Boot Backend

En robust, säker och skalbar backend-applikation byggd med **Spring Boot**, designad för att hantera digitala meddelanden, filer och kommunikation mellan användare. Projektet följer **MVC-arkitekturen** och använder **Spring Data JPA** för databasintegration med MySQL.

---

## 📌 Innehållsförteckning
- [Teknologier](#-teknologier)
- [Arkitektur](#-arkitektur)
- [MVC-Komponenter](#-mvc-komponenter)
- [RESTful API](#-restful-api)
- [Databasintegration](#-databasintegration)
- [Mailbox Service](#-mailbox-service)
- [Sammanfattning](#-sammanfattning)

---

## 🛠️ Teknologier

- **Spring Boot**
- **Spring MVC**
- **Spring Data JPA**
- **MySQL**
- **GitHub (Versionshantering)**
- **JPA / Hibernate**

---

## 🏗️ Arkitektur

Backend är byggd med **Spring Boot** och följer **Model–View–Controller (MVC)**, vilket gör projektet:
- Strukturierat  
- Lätt att underhålla  
- Skalbart  
- Robust  

Spring Boot tillhandahåller:
- Inbyggd server
- Automatiserad konfiguration
- Stöd för REST API
- Integration med databaser via JPA

---

## 🧩 MVC-Komponenter

### ✅ **Controller**
Kontrollerar inkommande HTTP-förfrågningar och skickar svar till klienten.

**Ansvarar för:**
- Ta emot requests (GET, POST, PUT)
- Använda annoteringar som:  
  - `@RestController`  
  - `@RequestMapping`  
  - `@PostMapping`, `@GetMapping`, `@PutMapping`
- Anropa Service-lagret
- Arbeta med DTO:er för dataformattering

---

### ✅ **Service**
Innehåller affärslogiken.

**Ansvarar för:**
- Databehandling och regler
- Transaktionshantering
- Interaktion med Entity-klasser
- Koppling mellan Controller och Repository

---

### ✅ **Entity**
Representerar databasens tabeller via JPA.

**Ansvarar för:**
- Datamodeller
- JPA-annoteringar som:  
  - `@Entity`  
  - `@Id`  
  - `@GeneratedValue`
- Relationer (OneToMany, ManyToOne, ManyToMany)
- Getters och setters

---

## 🔁 Samverkan mellan komponenterna

1. Klienten skickar en request  
2. Controller tar emot den  
3. Controller anropar Service  
4. Service hämtar/sparar data via JPA  
5. Controller skickar svar tillbaka till klienten  

Denna struktur gör projektet lätt att skala och underhålla.

---

## 🌐 RESTful API

Applikationen exponerar flera REST-endpoints som hanterar:

- Skapa och uppdatera Senders
- Hämta enskilda eller flera Senders
- Skicka meddelanden och filer
- Hämta mottagares mailbox
- Autentisering (om implementerat)

### Exempel på endpoint-typer:
| Typ | Syfte | Exempel |
|-----|-------|---------|
| **POST** | Skapa resurs | `/sender` |
| **PUT** | Uppdatera resurs | `/sender/{id}` |
| **GET** | Hämta resurser | `/sender`, `/sender/{id}` |

---

## 🗄️ Databasintegration

Applikationen använder **Spring Data JPA** för att koppla samman backend och MySQL-databasen.  
Detta gör databashantering enkel och effektiv.

### 📘 Relationsmodell:
#### ✅ Mailbox → Sender (**ManyToOne**)
- Flera mailbox-objekt kan ha samma avsändare

#### ✅ Mailbox ↔ Recipient (**ManyToMany**)  
- Ett mailbox-objekt kan skickas till flera mottagare  
- En mottagare kan ha flera mailbox-objekt

---

## ✉️ Mailbox Service

Mailbox-tjänsten hanterar all logik kring skickande och mottagande av meddelanden.

### Funktioner:
- **Skicka till specifik mottagare**
- **Skicka till alla mottagare**
- **Hämta filer för en mottagare**
- **Länka mailbox med sender och recipients**

---

## ✅ Sammanfattning

Detta projekt är en komplett backend för en digital brevlåda och erbjuder:

- Klar struktur (MVC)
- Kraftfull REST API
- Databasintegration med MySQL
- Relationer mellan avsändare, mottagare och mailbox
- Skalbar och modulär kod
- Enkelt samarbete via GitHub
---
<img width="1662" height="815" alt="Skärmavbild 2025-11-04 kl  15 23 37" src="https://github.com/user-attachments/assets/ac3ea4dc-e729-4f9b-998e-62b9e391625d" />
<img width="1692" height="597" alt="Skärmavbild 2025-11-04 kl  15 23 06" src="https://github.com/user-attachments/assets/a4ffceb4-968b-4d94-8bee-dce402b8487c" />
<img width="1663" height="758" alt="Skärmavbild 2025-11-04 kl  15 22 56" src="https://github.com/user-attachments/assets/bc45ece9-f95e-4521-ae31-a9d8a05b5887" />
<img width="1692" height="597" alt="Skärmavbild 2025-11-04 kl  15 22 41" src="https://github.com/user-attachments/assets/c0840e94-719a-4e25-9a42-ac4ad638527f" />


