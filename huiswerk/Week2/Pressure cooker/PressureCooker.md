# Ontwerpvraag
**Wie roept een specifieke externe service aan, gebeurt dat vanuit de front-end of vanuit de back-end? Welke redenen zijn er om voor de ene of de andere aanpak te kiezen?**

---

# Pressure Cooker / Design Sprint (90 minuten + 10 min voorbereiding)
Voor de trip top casus.

---

## Stap 1 (20 min): Componenten + verantwoordelijkheden
**Toegepaste principes:**
- Encapsulate What Varies
- Single Responsibility Principle
- Cohesion
- Separation of Concerns

| Component            | Verantwoordelijkheid                                                    |
|----------------------|-------------------------------------------------------------------------|
| Frontend             | Verzamelt input van gebruiker, toont kaarten, trips en feedback         |
| Backend              | Verwerkt verzoeken van frontend, bevat business logica, valideert input |
| Database             | Slaat gebruikersdata, routes en reserveringen op                        |
| Routeplanner Service | Externe API voor het genereren van routes tussen locaties               |
| Overnachting Service | Externe API voor het boeken van hotels/overnachtingen                   |
| Uber Service         | Externe API voor vervoer                                                |
| Opentable API        | Externe API voor restaurantreserveringen                                |
| Identity Provider    | Externe authenticatie                                                   |

---

## Stap 2 (20 min): Interfaces (Program to an Interface)
**Toegepaste principes:**
- Program to an Interface
- Low Coupling
- Information Hiding

```java
interface RouteplannerService {
  MapImage getMap(List<Location> route);
}

interface BookingService {
  BookingResponse searchHotels(SearchQuery query);
}

interface VervoerService {
  RideOptions getRides(Location from, Location to);
}

interface RestaurantService {
  RestaurantOptions getRestaurants(Location location);
  ReservationResponse reserve(Restaurant r);
}

interface AuthService {
  Token validateCredentials(String username, String password);
}
```

---

## Stap 3 (20 min): Samenwerking tussen componenten (Coupling & Dynamic Flow)

### Dynamic Diagram 

Zie DynamicDiagram.puml

---

## Stap 4 (30 min): Class/functie-opdeling (class diagram)

Zie ClassDiagram.puml

---

## Eindantwoord op de ontwerpvraag

### Wie roept externe services aan?
Meestal de backend, soms de frontend.

---

### Wanneer roept de backend aan?

| Voorbeeld                      | Reden backend-aanroep                                               |
|--------------------------------|----------------------------------------------------------------------|
| Overnachtingen (Booking.com)   | Bevat gevoelige data, vereist validatie en foutafhandeling          |
| Reserveringen (OpenTable)      | Centrale controle en logging                                        |
| Transport (Uber API)           | Vereist berekeningen, statuscontrole, en retry-mogelijkheden        |
| Authenticatie (OAuth)          | Tokens en sessies zijn veiliger te beheren in backend               |

---

### Wanneer roept de frontend aan?

| Voorbeeld                  | Reden frontend-aanroep                                               |
|----------------------------|----------------------------------------------------------------------|
| Routeplanner (Google Maps) | Publieke API, snelle visuele feedback, geen gevoelige gegevens       |
| Statische content ophalen  | Geen logica of validatie vereist                                     |

---

### Conclusie

Gebruik de frontend alleen voor externe services die:
- Geen gevoelige data verwerken
- Geen validatie of business logica vereisen
- Snel resultaat moeten tonen aan de gebruiker (zoals maps)

Gebruik de backend voor externe services die:
- Authenticatie, betalingen of reserveringen bevatten
- Afhankelijk zijn van interne status of domeinlogica
- Veiligheid en onderhoudbaarheid vereisen
