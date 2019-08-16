# Introductie
Deze workshop is deel van de DEA Course aan de Hogeschool Arnhem/Nijmegen. 
Onderwerp is het bekend raken met CDI en het unittesten van een JavaEE applicatie.

# Oefening
Deze oefening is het vervolg op de oefening [Simple the Rest](https://github.com/HANICA-DEA/workshop-simply-the-rest). 
De startcode van deze oefening is de eindcode van [Simple the Rest](https://github.com/HANICA-DEA/workshop-simply-the-rest).

## 1: Unittests toevoegen
We zullen beginnen met het toevoegen van unittests voor de klassen `HealthCheckResource` en `ItemResource`

### 1.1: `HealthCheckResource`
Voeg aan je project een dependency op jUnit toe en schrijf een unittest voor de methode uit de klasse ``HealthCheckResource``.

### 1.2: `ItemResource`
Schrijf voor iedere methode uit de klasse `ItemResource` een zinnige unittest. Omdat deze methode gebruik maakt van de 
`ItemService` en daar ook hard aan is gekopppeld, zal alleen de happy-flow getest kunnen worden. Voor nu is dat voldoende.

Waarschijnlijk zul je nog wel tegen een probleem aanlopen bij het testen van de methodes die een `Response` teruggeven.
Lees de stacktrace goed door en gebruik Google om een oplossing te vinden. Om je alvast in de goede richting te helpen: 
een implementatie `Response` wordt door je JavaEE Container aangeleverd. In de context van een 
unittest, maak je geen gebruik van je JavaEE Container. Je zult dus een bijbehorende `Exceptie` kunnen
verwachten, welke je kan terugvinden in de stacktrace. 

## 2: Toevoegen Dependency Injection
In het vorige onderdeel heb je unittests geschreven en mogelijk liep je tegen het probleem aan dat je niet goed wist
wat je nu eigenlijk moest testen. Waarschijnlijk kwam je niet veel verder dan het testen of het `Response` de te verwachtte
status-code bevatte. Maar of ook daadwerkelijk de juiste methodes op de `ItemService`, met de juiste parameters werden aangeroepen, 
heb je vast niet getest.

In de huidige vorm is dat ook uitzonderlijk lastig te testen. Dit komt omdat de `ItemService` hard gekoppeld is
aan de `ItemResource`. Wanneer je nu de `ItemResource` test, test je eigenlijk ook de `ItemService`. Buiten het probleem dat je
nu niet eens kan testen of de `ItemResource` correct gebruik maakt van de `ItemService`, heb je daarmee ook het probleem
dat je, wanneer een test faalt, niet goed weet of de fout nu in `ItemResource` of in de `ItemService` zit.

We gaan dit probleem oplossen door de `ItemService` los te koppelen van de `ItemResource`, met behulp van Dependency Injection.
Hiermee kunnen we de code beter laten voldoen aan de **D** van **SOLID**, het **D**pendency Inversion Principle. In het volgende
onderdeel kunnen we vervolgens laten zien hoe we nu wel enkel de `ItemResource` kunnen testen.

### 2.1: Het omkeren van de afhankelijkheid (Dependency Inversion)
Momenteel is de `ItemResource` afhankelijk van de `ItemService`. Stel dat de methode `getAll()` uit de `ItemService` van
naam zal veranderen, dan zal ook de `ItemResource` aangepast moeten worden. Dit gaan we aanpassen; we gaan 
eerst een interface maken die vast zal leggen welke methodes de `ItemResource` zal verwachten. Het type van de 
instantievariabele van de `ItemService` in de `ItemResource` wordt daarna aangepast naar de Interface. En Tot slot
moet de `ItemService` de interface implementeren.
* Hernoem de `ItemService` naar `HardCodedItemService`. De waarden zijn tenslotte hard-coded en het geeft dus beter
aan wat we van de klasse kunnen verwachten.
* Creëer een interface op basis van de `HardCodedItemService` en noem deze `ItemService`. Gebruik bij voorkeur de
refactor-functies van je IDE.
* Zorg dat de `HardCodedItemService` de interface `ItemService` implementeerd.
* Pas het type van de instantievariabele op de `ItemResource` aan.

### 2.2: Toepassen Dependency Injection
In de constructor van de `ItemResource` wordt een nieuwe instantie van de `HardCodedItemService` geïnstantieerd
en aan een instatievariabele toegewezen. Deze verantwoordelijkheid gaan we overdragen aan de JavaEE container, door 
gebruik te maken van CDI.
* Verwijder de volledige constructor van de `ItemResource`.
* Creëer een setter voor de instantievariabele genaamd `itemService`.
* Annoteer de setter met `@Inject`
* Om CDI 'aan' te zetten is het nog nodig om een *beans.xml* bestand op de juiste plek te zetten. Lees dit 
artikel voor meer informatie hierover: [An Introduction to CDI ](https://www.baeldung.com/java-ee-cdi)

## 3: Repareren van de unittests
Door het toevoegen van Dependency Injection zullen de unittests nu `NullPointerExceptions` gaan opleveren. Snap
je ook waarom?

### 3.1: 
In iedere unittest zal nu eerst niet alleen een instantie gemaakt moeten worden van de  


### 4: Injecteren van een alternatieve `ItemService`
