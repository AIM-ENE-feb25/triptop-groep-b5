### 8.5. ADR-005 Beveiliging van Verouderde Externe APIs 
Datum: 2025-03-28  

### Status 
Accepted

### Context

Sommige externe APIs die we moeten integreren, ondersteunen geen moderne beveiligingsprotocollen zoals OAuth 2.0, TLS
1.3, of API keys met strikte toegangscontroles. Dit vormt een risico voor gegevensintegriteit en vertrouwelijkheid,
vooral wanneer gevoelige data wordt uitgewisseld. Er is behoefte aan een strategie om deze interacties op een zo veilig
mogelijke manier te beheren.

### Considered Options

| Forces                | API Gateway met beveiliging | Data Proxy met encryptie | Isolatie via middleware |
|-----------------------|-----------------------------|--------------------------|-------------------------|
| **Beveiliging**       | ++                          | ++                       | +                       |
| **Complexiteit**      | --                          | -                        | ++                      |
| **Onderhoudbaarheid** | 0                           | -                        | +                       |
| **Schaalbaarheid**    | +                           | +                        | 0                       |

### Decision

De interactie met verouderde externe APIs wordt beveiligd via een **API Gateway met beveiliging**. Deze gateway zal:

- Alleen toegestane verzoeken doorlaten en valideren via toegangscontrole.
- Dataverkeer inspecteren en indien mogelijk versleutelen (bijvoorbeeld via TLS-terminatie als de API dit niet
  ondersteunt).
- Verouderde authenticatiemethoden compenseren door credentials te beheren en te abstraheren.
- Logging en monitoring toepassen om afwijkend gedrag op te sporen.

### Consequences

De keuze voor een API Gateway verhoogt de beveiliging aanzienlijk en zorgt ervoor dat integratie met verouderde APIs
veilig blijft. Echter, het introduceert extra infrastructuur en complexiteit, wat onderhoud en beheer vereist.
