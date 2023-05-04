# DatabaseUppgift
Uppgift MongoDB

    
Projektet är ett program som sparar personer i en MongoDB-databas och kan ändra, ta bort, lägga till och visa dessa personer i databasen.

Databasen är en lokal databas som ansluts med en standard-anslutningssträng till server och port, samt ett standard DB-namn om användaren inte har eller vill ange det.

Personklassen är en abstrakt klass som har två subklasser: Customer och Worker. Eftersom det finns många metoder som ska användas i båda subklasserna, har jag också lagt till abstrakta metoder i Personklassen för att underlätta hantering och se till att alla metoder är @Override i underklasserna.

Jag använder en lokal MongoDB-databas med två samlingar (customer och worker) för att spara alla personer baserat på deras information, beroende på om de är arbetare eller kunder.

De CRUD-metoder jag har implementerat är följande:

två metoder som konverterar ett objekt till en Document (dokument) eller vice versa och sedan skickar eller tar imot dokumentet till/från databasen genom att använda insertOne(), find(), updateOne() eller deleteOne()-metoder beroende på användarens val.

Eftersom sökningen via nummer eller ålder (Väldigt krånglig) och måste vara exakt och inte inkludera personer med samma siffror i sina åldrar eller nummer, valde jag att skapa ett nytt dokument och söka efter workerNumber eller customerNumber beroende på användarens val.No regex here :)

Sökningen via namn och adress använder en regex-kod ".*" som tar en stränginput och söker efter allt som matchar i databasen.


Här har jag uppdaterat och skapat handlers för Person, som är en abstract klass.

Person innehåller metoder som är gemensamma för Worker och Customer, för att hantera data som ska läsas in och skrivas ut från databasen.

Jag har också skapat Customer Handler och Worker Handler som ärver från Person Handler, för att hantera data som är specifik för Customer och Worker.


