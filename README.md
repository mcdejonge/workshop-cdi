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
Schrijf voor iedere methode uit de klasse `ItemResource` een unittest. Omdat deze methode gebruik maakt van de `ItemService`
en daar ook hard aan is gekopppeld, zal alleen de happy-flow getest kunnen worden. Voor nu is dat voldoende.

Waarschijnlijk zul je nog wel tegen een probleem aanlopen bij het testen van de methodes die een `Response` teruggeven.
Lees de stacktrace goed door en gebruik Google om een oplossing te vinden. Om je alvast in de goede richting te helpen: 
een implementatie `Response` wordt door je JavaEE Container aangeleverd. In de context van een 
unittest, maak je geen gebruik van je JavaEE Container. Je zult dus een bijbehorende `Exceptie` kunnen
verwachten, welke je kan terugvinden in de stacktrace. 

## 2: 
