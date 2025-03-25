# Presentatie – Encapsulate What Varies

##  Introductie

Vandaag presenteer ik het designprincipe **Encapsulate What Varies**, in het Nederlands: **Inkapselen wat varieert**.  
Dit principe helpt om software flexibel, onderhoudbaar en uitbreidbaar te maken.

---

## Naam & Definitie

**Encapsulate What Varies** betekent:
Isoleer het deel van je code dat waarschijnlijk gaat veranderen, door het in te kapselen(encapsulate). Zo voorkom je dat de rest van je systeem wordt beïnvloed bij veranderingen.



---

## Gevolgen van het toepassen

Wanneer je het Encapsulate What Varies-principe toepast, maak je je code een stuk flexibeler en toekomst bestendig.
Stel je voor dat je een blog maakt waar elke post een ander soort inhoud kan bevatten. Het kan tekst, een afbeelding, een gif of een video zijn.

Door die variatie los te koppelen van de rest van de applicatie, hoef je in de toekomst niks te veranderen aan de hoofdcodestructuur als je iets nieuws toevoegt.

Bijvoorbeeld: als je later een PollContent of AudioContent wilt maken, hoef je alleen maar een nieuwe klasse te maken die de juiste interface implementeert. De bestaande Post-klasse hoeft je niet aan te passen.

Daarnaast zorgt deze aanpak voor lage koppeling/low coupling. Dit betekend dat onderdelen niet strak aan elkaar zijn vastgeplakt. De Post-klasse weet alleen: “ik werk met iets dat PostContent heet”, maar weet niet hoe die content eruitziet of zich gedraagt, dat hoeft Post niet te weten.

Ook de onderhoudbaarheid wordt veel beter. Want stel je voor dat je de opmaak van video-posts moet aanpassen: dan hoef je alleen in VideoContent.java te kijken.

Tot slot levert het je herbruikbaarheid op. Misschien werk je straks aan een andere app waar je weer contenttypes nodig hebt. In dat geval kun je je bestaande TextContent, GifContent, etc. gewoon hergebruiken.

---

## Voorbeeld code: Blogpost met verschillende inhoudstypen

### Situatie:
Een blogpost kan verschillende soorten content bevatten: tekst, afbeelding, video of gif.  
We isoleren dat variabele deel in een interface.

---

### `PostContent.java`

```java
package voorbeeldcode;

public interface PostContent {
    void render();
}
```

---

### `Implementaties`

```java
package voorbeeldcode;

public class TextContent implements PostContent {
    private String text;

    public TextContent(String text) {
        this.text = text;
    }

    public void render() {
        System.out.println("Tekst: " + text);
    }
}
```

```java
package voorbeeldcode;

public class ImageContent implements PostContent {
    private String imageUrl;

    public ImageContent(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void render() {
        System.out.println("Toon afbeelding vanaf: " + imageUrl);
    }
}
```

```java
package voorbeeldcode;

public class VideoContent implements PostContent {
    private String videoUrl;

    public VideoContent(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void render() {
        System.out.println("Speel video vanaf: " + videoUrl);
    }
}
```

```java
package voorbeeldcode;

public class GifContent implements PostContent {
    private String gifUrl;

    public GifContent(String gifUrl) {
        this.gifUrl = gifUrl;
    }

    public void render() {
        System.out.println("Toon GIF vanaf: " + gifUrl);
    }
}
```

---

### `Post.java`

```java
package voorbeeldcode;

public class Post {
    private String title;
    private PostContent content;

    public Post(String title, PostContent content) {
        this.title = title;
        this.content = content;
    }

    public void show() {
        System.out.println("Post: " + title);
        content.render();
    }
}
```

---

### `Main.java`

```java
package voorbeeldcode;

public class Main {
    public static void main(String[] args) {
        PostContent text = new TextContent("Hallo allemaal!");
        Post post = new Post("Mijn eerste blogpost", text);
        post.show();
    }
}
```

**Output:**

```
Post: Mijn eerste blogpost
Tekst: Hallo allemaal!
```

---

## Gebaseerd op welke Design Properties?

Het principe **Encapsulate What Varies** is gebaseerd op vijf fundamentele ontwerpprincipes in softwareontwikkeling. 

Door elk veranderlijk onderdeel in een eigen klasse onder te zetten, bevorder je **modulariteit**: de code wordt opgebouwd uit losse, herbruikbare delen. 

Daarbij zorgt het ervoor dat elk onderdeel een duidelijke taak krijgt, wat aansluit bij het idee van **separation of concerns**: één klasse doet één ding. 

Omdat je programmeert tegen een interface in plaats van concrete implementaties, ontstaat er **low coupling**: de afhankelijkheid tussen onderdelen is minimaal. 

Dat maakt het systeem flexibel en uitbreidbaar, oftewel: je verhoogt de **flexibility** doordat je nieuwe variaties kunt toevoegen zonder bestaande code te wijzigen. 

En ten slotte draagt dit alles bij aan de **maintainability** van je project, omdat veranderingen slechts op één plek hoeven te gebeuren, zonder onbedoelde gevolgen voor de rest van het systeem.


---

## Conclusie

**Encapsulate What Varies** zorgt ervoor dat je software toekomstbestendig is.  
Door verwachte veranderingen te isoleren, hou je je codebase overzichtelijk, testbaar en uitbreidbaar.

---

## Bedankt voor jullie aandacht!

Zijn er nog vragen? :D
