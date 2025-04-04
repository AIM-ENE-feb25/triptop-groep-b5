# Software Guidebook Triptop

## 1. Introduction

Dit software guidebook geeft een overzicht van de Triptop-applicatie. Het bevat een samenvatting van het volgende:

1. De vereisten, beperkingen en principes.
1. De software-architectuur, met inbegrip van de technologiekeuzes op hoog niveau en de structuur van de software.
1. De ontwerp- en codebeslissingen die zijn genomen om de software te realiseren.
1. De architectuur van de infrastructuur en hoe de software kan worden geinstalleerd.

## 2. Context

![context diagram](../opdracht-diagrammen/1Context/C4_Context_Diagram.png)

[//]: # (Toelichting op de context van de software inclusief System Context Diagram:)

[//]: # (* Functionaliteit)

[//]: # (* Gebruikers)

[//]: # (* Externe systemen)

Triptop is een online vakantieplanner waarmee reizigers zelfstandig hun vakantie kunnen samenstellen, boeken en beheren
zonder afhankelijk te zijn van een traditioneel reisbureau.

## Functionaliteit

De kernfunctionaliteiten van Triptop zijn:

* zelfstandige een vakantie samenstellen op basis van diverse variabelen (bouwstenen) zoals duurzaamheid, budget,
  reistijd etc.
* boeken en betalen van de geplande reis
* annuleren van de geboekte reis
* bewaren van de reisstatus
* flexibel uitbreiden van de bouwstenen van de reis

## Gebruikers

* Reizigers: de primaire gebruikers van Triptop zijn reizigers die zelfstandig hun vakantie willen samenstellen, boeken
  en beheren.
* Medewerkers: de secundaire gebruikers van Triptop zijn medewerkers die de reizigers ondersteunen bij het samenstellen,
  boeken en beheren van hun vakantie.

## Externe systemen:

* Overnachting Service (Booking.com Api)
* Vervoer Service
* Routeplanner Service (Google maps Api)
* Identity Service
* Autoverhuur Service
* Excursie Service
* Accommodatie Service

## 3. Functional Overview

Om de belangrijkste features toe te lichten zijn er user stories en twee domain stories gemaakt en een overzicht van het
domein in de vorm van een domeinmodel. Op deze plek staat typisch een user story map maar die ontbreekt in dit
voorbeeld.

### 3.1 User Stories

#### 3.1.1 User Story 1: Reis plannen

Als gebruiker wil ik een zelfstandig op basis van diverse variabelen (bouwstenen) een reis kunnen plannen op basis van
mijn reisvoorkeuren (wel/niet duurzaam reizen, budget/prijsklasse, 's nachts reizen of overdag etc.) zodat ik op
vakantie kan gaan zonder dat hiervoor een reisbureau benodigd is.

#### 3.1.2 User Story 2: Reis boeken

Als gebruiker wil ik een geplande reis als geheel of per variabele (bouwsteen) boeken en betalen zodat ik op vakantie
kan gaan zonder dat hiervoor een reisbureau benodigd is.

#### 3.1.3 User Story 3: Reis cancelen

Als gebruiker wil ik een geboekte reis, of delen daarvan, kunnen annuleren zodat ik mijn geld terug kan krijgen zonder
inmenging van een intermediair zoals een reisbureau.

#### 3.1.4 User Story 4: Reisstatus bewaren

Als gebruiker wil ik mijn reisstatus kunnen bewaren zonder dat ik een extra account hoef aan te maken zodat ik mijn reis
kan volgen zonder dat ik daarvoor extra handelingen moet verrichten.

#### 3.1.5 User Story 5: Bouwstenen flexibel uitbreiden

Als gebruiker wil ik de bouwstenen van mijn reis flexibel kunnen uitbreiden met een zelf te managen stap (bijv. met
providers die niet standaard worden aangeboden zoals een andere reisorganisatie, hotelketen etc.) zodat ik mijn reis
helemaal kan aanpassen aan mijn wensen.

### 3.2 Domain Story Reis Boeken (AS IS)

![Domain Story Reis Boeken AS IS](../opdracht-diagrammen/reis-boeken-asis-coursegrained_2024-06-11.egn.svg)

### 3.3 Domain Story Reis Boeken (TO BE)

![Domain Story Reis Boeken TO BE](../opdracht-diagrammen/reis-boeken-tobe-coursegrained_2024-06-11.egn.svg)

### 3.4 Domain Model

![Domain Model](../opdracht-diagrammen/Domain%20Model.png)

## 4. Quality Attributes

Voordat deze casusomschrijving tot stand kwam, heeft de opdrachtgever de volgende ISO 25010 kwaliteitsattributen benoemd
als belangrijk:

* Compatibility -> Interoperability (Degree to which a system, product or component can exchange information with other
  products and mutually use the information that has been exchanged)
* Reliability -> Fault Tolerance (Degree to which a system or component operates as intended despite the presence of
  hardware or software faults)
* Maintainability -> Modularity (Degree to which a system or computer program is composed of discrete components such
  that a change to one component has minimal impact on other components)
* Maintainability -> Modifiability (Degree to which a product or system can be effectively and efficiently modified
  without introducing defects or degrading existing product quality)
* Security -> Integrity (Degree to which a system, product or component ensures that the state of its system and data
  are protected from unauthorized modification or deletion either by malicious action or computer error)
* Security -> Confidentiality (Degree to which a system, product or component ensures that data are accessible only to
  those authorized to have access)

## 5. Constraints

> [!TIP]
> Beschrijf zelf de beperkingen die op voorhand bekend zijn die invloed hebben op keuzes die wel of niet gemaakt kunnen
> of mogen worden.

### 5.1. Externe API's eisen

De applicatie moet voldoen aan de functionele en technische eisen van de externe API's.

#### Impact:

* Er moet rekening gehouden worden met rate limits van bepaalde API's.
* Beschikbaarheid checken van een API (Prototypes maken).

### 5.2 Authenticatie

De applicatie moet gebruik maken van een externe authenticatie service.

#### Impact:

* De applicatie moet voldoen aan de eisen van de externe authenticatie service.

#### 5.3 Randvoorwaarden:

* De persoonlijke API keys van RapidAPI worden niet opgeslagen in de applicatie en worden gezet in de gitignore file.
* De API keys worden in de applicatie geplaatst via een environment variabele.
* De API keys mogen niet zomaar hardcoded in de controller of iets dergelijks worden geplaatst.

## 6. Principles

### 6.1. Architecture Principles

#### Gebruik maken van API:

Om ontwikkeltijd te besparen en onderhoud te minimaliseren, maken we gebruik van RapidAPI om externe API's te vinden en
te integreren in onze applicatie. Dit stelt ons in staat om bestaande services te benutten in plaats van deze zelf te
bouwen en onderhouden.

Er zijn hier een aantal voordelen voor:

* Snellere ontwikkeltijd
* Minder onderhoud
* Flexibiliteit in het gebruik van externe services

### 6.2. Design Principles

We hebben gekozen om zo veel mogelijk Single responsibility, Encapsulation en open close principes te gebruiken.

Voor design patterns Zie "8.6. ADR-005 Pattern keuze"

## 7. Software Architecture

### 7.1. Containers

![Container Diagram](../opdracht-diagrammen/2Container/C4_Container_Diagram.png)

In dit diagram zie je hoe de containers in dit systeem met elkaar en de externe system werken.
De frontend maakt gebruik van google maps, omdat het geen API keys bevat en geen veiligheidsrisico geeft als de gebruiker toegang heeft tot de methodes die deze API gebruiken.
De backend heeft connectie met de rest van de APIs omdat hier wel secret API keys gebruikt worden voor de connectie.
De backend zorgt ervoor dat de connectie met de externe services op de juiste manier wordt gedaan, op aanvraag van de frontend.

#### Dynamic container diagram: toevoegen punt in een reis

![Dynamic Container Diagram Plannen](../opdracht-diagrammen/2Container/C4_Container_Dynamic_Plannen.png)

### 7.2. Components

#### 7.2.1 Frontend
![Frontend container](../opdracht-diagrammen/3Component/Frontend-components.png)

#### 7.2.2 Backend
![Backend container](../opdracht-diagrammen/3Component/Backend-components.png)

### 7.3. Design & Code

> [!IMPORTANT]
> Voeg toe: Per ontwerpvraag een Class Diagram plus een Sequence Diagram van een aantal scenario's inclusief
> begeleidende tekst.

#### Brian
**Modularity**

Hoe zorg je ervoor dat je makkelijk de ene externe service kan vervangen door een andere die ongeveer hetzelfde doet?

![Class diagram](../opdracht-diagrammen/diagrammen-Brian/classdiagram.png)

![Sequence Diagram](../opdracht-diagrammen/diagrammen-Brian/sequentiediagram.png)
In het diagram hierboven wordt aangegeven hoe hotels worden opgehaald via twee verschillende api's.

#### Yousif
**Confidentiality**

Hoe zorg je ervoor dat authenticatie en autorisatie consistent worden toegepast bij het communiceren met verschillende externe APIs?

![Class diagram](../opdracht-diagrammen/diagrammen-yousif/klassen-diagram/klassendiagram-Confidentiality-Klassendiagram_voor_Confidentiality_ontwerpvraag.png)

![Sequence Diagram](../opdracht-diagrammen/diagrammen-yousif/sequence-diagram/sequence-confidentiality.png)
In het diagram hierboven wordt aangegeven hoe een externe API met authenticatie en autorisatie wordt aangeroepen.

#### Cassey
**Interoperability**

Wie roept een specifieke externe service aan, gebeurt dat vanuit de front-end of vanuit de back-end? Welke redenen zijn er om voor de ene of de andere aanpak te kiezen?
![Class diagram](../opdracht-diagrammen/diagrammen-Cassey/class.png)

![Sequence diagram](../opdracht-diagrammen/diagrammen-Cassey/Sequentie.png)

In de diagram hierboven wordt aangegeven hoe via de backend een externe api wordt aangeroepen.

#### Stijn
**Fault tolerance**

Hoe ga je om met aanroepen van externe services die niet beschikbaar zijn en toch verwacht wordt dat er waardevolle output gegeven wordt?

![Class diagram](../opdracht-diagrammen/diagrammen-Stijn/classdiagram.png)

![Sequence diagram](../opdracht-diagrammen/diagrammen-Stijn/sequentiediagram.png)

![Sequence diagram](../opdracht-diagrammen/diagrammen-Stijn/sequentiediagram-fout.png)

Hierboven staan twee diagrammen waar bij de eerste de login van de gebruiker helemaal goed gaat en dat het systeem de rol terug krijgt en in de tweede situatie als de login niet verkeerd gaat om wat voor reden dan ook wordt er een foutmelding netjes getoont aan de eindgebruiker.

## 8. Architectural Decision Records

### 8.1. ADR-001 Maps API

Datum: 2025-03-21

#### Status

Accepted

#### Context

Voor het plannen van een reis is het belangrijk om in de buurt van bepaalde locaties, interessante locaties te kunnen
vinden.
Hiervoor hebben we een Maps API nodig. Deze Maps API moet een "center point" kunnen ontvangen met een radius en een
zoekactie, waarna hij locaties geeft met een link en overige belangrijke informatie.

#### Considered Options

| Forces                        | Maps data (rapidapi) | Google Maps Places (rapidapi) |
|-------------------------------|----------------------|-------------------------------|
| Gebruik endpoint              | -                    | ++                            |
| Structuur van output          | 0                    | ++                            |
| Details van output            | -                    | ++                            |
| Rate limit bij gratis gebruik | +                    | -                             |
| Documentatie                  | ++                   | +                             |

#### Decision

Op basis van de options en de beoordeling daarop hebben we gekozen voor Google Maps Places.
Ondanks dat de Maps data betere documentatie heeft, heeft de structuur van de invoer van de gegevens en de details van
de uitvoer van Google Maps Places de doorslag gegeven.

#### Consequences

We maken gebruik van de Google Maps Places API voor het zoeken van locaties in de buurt van een bepaalde locatie.
Een nadeel hiervan is dat zonder te betalen, er een rate limit is van 100 requests per dag aan gekoppeld is.

### 8.2. ADR-002 Secret keys

Datum: 2025-03-21

#### Status

Accepted

#### Context

In deze applicatie maken we gebruik van meerdere externe services. Om deze te kunnen gebruiken zijn secret keys nodig
voor de meeste APIs.
Deze secret keys mogen niet gelekt worden aan de buitenwereld, omdat deze aan een rate limit vast zitten en in sommige
gevallen ook kosten met zich meebrengen.
Het is dus belangrijk dat deze ook niet in git terecht komen.

#### Considered Options

| Forces                     | .env | application.properties | direct in code |
|----------------------------|------|------------------------|----------------|
| Geheimhouding              | ++   | +                      | --             |
| Makkelijkheid van gebruik  | 0    | +                      | ++             |

#### Decision

We hebben gekozen voor application.properties. Het lijkt in dit geval het meest geschrikt voor het prototypen.

#### Consequences

De secret keys zullen niet in git terecht komen, omdat deze veilig in de application.properties file staan.
Wel heeft dit als impact dat als er iets veranderd in de application.properties zou elk teamlid hierom ook individueel dit moeten instellen met de nieuwe aanpassingen en hun eigen API keys.

### 8.3. ADR-003 OAuth2 Provider

Datum: 2025-03-27

#### Context

Als eis voor de applicatie is gesteld dat er gebruik gemaakt moet worden van een externe authenticatie service.
Er zijn verschillende providers, deze hebben allemaal hun eigen voor- en nadelen. Het is belangrijk om een provider te
kiezen die past bij de applicatie en de eisen die gesteld zijn.

#### Considered Options

| Forces                              | WireMock | Google OAuth2 | Eigen Authenticatie |
|-------------------------------------|----------|---------------|---------------------|
| Voldoet aan eisen van de applicatie | +        | ++            | --                  |
| Moeilijkheid implementatie          | ++       | --            | -                   |
| Documentatie                        | 0        | +             | -                   |

#### Decision

We hebben besloten om gebruik te maken van WireMock. Dit is een makkelijke optie om te implementeren.
Het voldoet aan de eisen van de applicatie en is makkelijk te implementeren.
Voor een prototype is dit een goede keuze, op een later moment kan er altijd nog gekozen worden voor een andere provider.

#### Status

Accepted

#### Consequences

Het is makkelijk te implementeren en zorgt er daarmee voor dat er niet te veel tijd verspilt wordt aan het implementeren van authenticatie.
Omdat het een mock is, biedt het geen veiligheid of echte authenticatie. Het is dus belangrijk om dit niet te gebruiken in productie.

### 8.4. ADR-004 prioriteit APIs

Datum: 2025-03-27

#### Status

Accepted

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

### 8.5. ADR-005 Database keuze

#### Context

Voor de applicatie is een database nodig om data op te slaan. Er zijn verschillende databases die gebruikt kunnen worden.
Het is belangrijk om een database te kiezen die past bij de applicatie en de eisen die gesteld zijn.

#### Considered Options

| Forces                              | H2 | MySQL | PostgreSQL | MongoDB |
|-------------------------------------|----|------|------------|---------|
| Snelheid                            | ++ | 0    | 0          | +       |
| Schaalbaarheid                      | -  | +    | ++         | ++      |
| Documentatie                        | 0  | +    | ++         | +       |

#### Decision

We hebben besloten om H2 te gaan gebruiken. Dit is een makkelijke optie om te implementeren.
Het is snel en makkelijk te implementeren. Voor een prototype is dit een goede keuze, op een later moment kan er altijd nog gekozen worden voor een andere database.

#### Status

Accepted

#### Consequences

Dit zorgt ervoor dat de database elke keer opnieuw wordt opgebouwd en er geen data wordt opgeslagen.
Voor productie is dit niet handig, maar voor een prototype is dit prima.
 
### 8.6. ADR-006 Pattern keuze

#### Context

Tijdens de ontwikkeling van de applicatie moeten we minimaal 3 patterns gebruiken.
Er zijn vijf patterns die we kunnen gebruiken.

#### Considered Options

| Forces                              | State | Strategy | Adapter | Facade | Factory Method |
|-------------------------------------|-------|----------|---------|--------|----------------|
| Makkelijkheid implementatie         | +     | ++       | +       | ++     | -              |
| Flexibiliteit                       | ++    | +        | -       | 0      | +              |
| Documentatie                        | 0     | +        | +       | ++     | -              |

#### Decision

We hebben gekozen om gebruik te gaan maken van de Strategy, Adapter en Facade patterns.

#### Status

Accepted

#### Consequences

Dit zorgt ervoor dat de code makkelijker te onderhouden is en dat er minder code herhaald hoeft te worden.
Het zorgt er ook voor dat de code makkelijker te begrijpen is voor andere developers.
Het kan zijn dat het later blijkt dat een pattern niet nodig is, of een ander pattern beter is. Deze keuze kan altijd nog aangepast worden.

### 8.5. ADR-005 TITLE

> [!TIP]
> These documents have names that are short noun phrases. For example, "ADR 1: Deployment on Ruby on Rails 3.0.10" or "
> ADR 9: LDAP for Multitenant Integration". The whole ADR should be one or two pages long. We will write each ADR as if it
> is a conversation with a future developer. This requires good writing style, with full sentences organized into
> paragraphs. Bullets are acceptable only for visual style, not as an excuse for writing sentence fragments. (Bullets kill
> people, even PowerPoint bullets.)

#### Context

> [!TIP]
> This section describes the forces at play, including technological, political, social, and project local. These forces
> are probably in tension, and should be called out as such. The language in this section is value-neutral. It is simply
> describing facts about the problem we're facing and points out factors to take into account or to weigh when making the
> final decision.

#### Considered Options

> [!TIP]
> This section describes the options that were considered, and gives some indication as to why the chosen option was
> selected.

#### Decision

> [!TIP]
> This section describes our response to the forces/problem. It is stated in full sentences, with active voice. "We
> will …"

#### Status

> [!TIP]
> A decision may be "proposed" if the project stakeholders haven't agreed with it yet, or "accepted" once it is agreed.
> If a later ADR changes or reverses a decision, it may be marked as "deprecated" or "superseded" with a reference to its
> replacement.

#### Consequences

> [!TIP]
> This section describes the resulting context, after applying the decision. All consequences should be listed here, not
> just the "positive" ones. A particular decision may have positive, negative, and neutral consequences, but all of them
> affect the team and project in the future.

## 9. Deployment, Operation and Support

> [!TIP]
> Zelf beschrijven van wat je moet doen om de software te installeren en te kunnen runnen.
