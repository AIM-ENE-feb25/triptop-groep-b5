### ADR prioriteit APIs

Datum: 2025-03-27

#### Status

In-Review

#### Context

De API wisselmethode is een manier om de API te laten kiezen welke data er teruggegeven moet worden.
Op welke manier de keuze wordt gemaakt is afhankelijk van de API en de data die deze teruggeeft.

#### Considered Options

| Forces                          | Op basis van prijs | Klant kiest zelf | Op basis van hoeveelheid resultaaten | 
|---------------------------------|--------------------|------------------|--------------------------------------|
| Flexibiliteit voor de gebruiker | 0                  | ++               | --                                   |
| Makkelijk te implementeren      | -                  | ++               | ++                                   |
| Schaalbaarheid                  | +                  | 0                | ++                                   |


#### Decision

De API wisselmethode zal gebaseerd worden op hoeveelheid resultaten. 
Dit betekent dat de gebruiker niks hoeft te kiezen en krijgt altijd de meest relevante resultaten.

#### Consequences

De keuze voor wisselmethode op basis van hoeveelheid resultaten maakt de API efficiënter en schaalbaarder door alleen de meest relevante data terug te sturen. 
Dit vermindert echter de controle voor gebruikers, waardoor sommige verwachte gegevens mogelijk ontbreken.


