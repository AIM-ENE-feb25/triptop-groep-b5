# Pressure cooker

## Ontwerpvraag
**Modularity**

Hoe zorg je ervoor dat je makkelijk de ene externe service kan vervangen door een andere die ongeveer hetzelfde doet?

## Stap 1
Welke components zijn nodig?

* **Autorisatie Controller**
  * **Verantwoordelijkheid:** Inloggen, account informatie, etc.
  * **Principles:** Separation of concerns
* **Autorisatie Service**
  * **Verantwoordelijkheid:** Connectie met een Auth provider, account data ophalen vanuit klant database.
  * **Principles:** Separation of concerns
* **Reis Controller**
    * **Verantwoordelijkheid:** Geeft de optie om bouwstenen aan een reis toe te voegen, en kan de volledige reis ophalen.
    * **Principles:** Separation of concerns
* **Reis Service**
    * **Verantwoordelijkheid:** Connectie met meerdere providers, zoals hotels, vluchten, etc.
    * **Principles:** Separation of concerns
* **Bestelling Controller**
    * **Verantwoordelijkheid:** Overzicht van een bestelling ophalen en betalen
    * **Principles:** Separation of concerns

## Stap 2
Het beschrijven van de interfaces van de betrokken componenten.

* **Autorisatie Adapter**
    * Returnwaarde:
      * `tripTopGebruiker: TripTopGebruiker(Voornaam, Tussenvoegsel, Achternaam, Email)`
    * Methoden:
      * `authenticateUser(String authToken): boolean`
      * `authenticateUser(String authToken): TripTopGebruiker(Voornaam, Tussenvoegsel, Achternaam, Email)`
* **Hotel Adapter**
    * Returnwaarde:
      * `List<Hotel>`
    * Methoden:
      * `getHotels(String city, Date arrivalDate, Date departureDate, Double price): List<Hotel>`

## Stap 3
dynamic diagram