---
author: Lektion 6
date: MMMM dd, YYYY
paging: "%d / %d"
---

# Teams lektion 6

Hej och välkommen!

## Agenda

1. Frågor och repetition
2. Introduktion till Spring Security
3. Fortsättning projektbygge
   - Med potentiell frontend
4. Eget arbete med handledning
5. Avstämning och quiz

---

# Fråga

Vad är skillnaden på DTO och Payload?

# Svar

Payload är ett annat namn för DTO.

---

# Fråga

Hej, i din video om controllers säger du att man inte behöver har @Autowired om man har @RequiredArgsConstructor på klassen, det verkar dock som att det funkar bara med Controllerklasser (med Service klass injection) men inte med Serviceklasser (med Repository klass injection). Kan du bekräfta / förklara / korrigera?

# Svar

Det skall fungera på alla sortera beans (e.g. services & controllers). Glöm inte att ha med `final` på variabeln.

---

# Fråga

Ska alla http requests göras med en json body eller ska man använda sig av parametrar osv?

# Svar

Detta handlar om ett designval: vad är det mest effektiva sättet att få in data på? Oftast är det genom en JSON body men ibland passar parametrar bättre.

Typiskt sätt:

- JSON body (DTO) för 3+ värden.
- Path variable för 1 enkelt värde (int, string, uuid)
- Request parameter för 1-3 enkla värden (int, string, uuid)

---

# Fråga

Kan du förklara relationer lite mer? Som exempel om man har en user och de kan ha vänner och de kan ha vänner. Hur gör man när man ska göra en ny user där man behöver lägga i vänner där de kanske inte har det än osv. Hoppas det går och förstå frågan.

# Svar

Då pratar vi mer om relationer!

---

# Fråga

Finns det någon preferens av hur man ska lösa relationer i ett objekt, ska det vara en lista, ett object, eller ett sorts id?

# Svar

Det beror på vilken typ av relation det är.

- För One-to-One: Object referens i båda modeller (till varandra)
- För One-to-Many: Lista av andra modellen
- För Many-to-One: Object referens till andra modellen
- För Many-to-Many: Lista av andra modellen, i båda modeller

---

# Frågor

1. Hej, det är flera i min grupp som undrar när vi kommer att lära oss om hur man skapar mappar och skicka filer för den individuella uppgiften. När kommer vi att gå igenom det?
2. Ska man med hjälp av Bruno skicka in information i filer som ska ligga i mappar och sen skicka en request för att få informationen i de filerna i respektive mappar?

# Svar

Vi kan prata kort om hur mappar och filer kan hanteras med Spring och repositories.

---

# Avstämning

- Vad kan vi repetera?
- Vad vill ni se mer av?
- Vad är svårt?
- Vad är enkelt?

---

# Quiz frågor

- Vad kallas funktioner som tar emot HTTP requests i ett webb API?
- Vad kallas klasser som kommunicerar med databaser?
- I vilken fil lägger man sina databasuppgifter?
- Vad kallas data som skickas mellan client och server?
- Vad är en URI?
- Vad innebär 'autentisering'/'authentication'?
- Vad innebär 'auktorisering'/'authorization'?
- Vad står 'JWT' för?
- Vad är ett filter?
- Vad heter interfacet som ansvarar för password hashing?
- Vad gör en login-endpoint som fungerar med JWT?
