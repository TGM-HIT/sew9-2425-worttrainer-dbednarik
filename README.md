# Worttrainer - Rechtschreibtraining mit Bildunterstützung

## Über das Projekt

Der Worttrainer ist ein Rechtschreibtrainer, der den Benutzern hilft, neue Wörter zu lernen und deren Rechtschreibung zu üben. Das Programm verwendet eine benutzerfreundliche grafische Benutzeroberfläche (GUI) mit visueller Unterstützung, um das Lernen zu erleichtern. Zu jedem Wort wird ein passendes Bild angezeigt, sodass der Benutzer das Wort sowohl visuell als auch textuell lernt. Das Programm verfolgt die Benutzerleistung in Bezug auf Versuche und korrekte Antworten und bietet Funktionen zum Speichern und Laden des Fortschritts.

Das Hauptziel des Programms ist es, das Lernen für Benutzer, insbesondere Kinder, durch den Einsatz von Visualisierungen interaktiver und effektiver zu gestalten.

## Hauptfunktionen

Wörter üben: Der Benutzer kann die Rechtschreibung von Wörtern üben, wobei jedes Wort von einem Bild begleitet wird, das die Bedeutung visualisiert.

Statistik: Das Programm verfolgt die Anzahl der Versuche und korrekten Antworten und zeigt die Erfolgsquote in Prozent an.

Speichern und Laden: Benutzer haben die Möglichkeit, ihren Lernfortschritt in einer Datei zu speichern und zu einem späteren Zeitpunkt weiterzümachen.

Visuelle Unterstützung: Zu jedem Wort wird ein passendes Bild angezeigt, das das Lernen unterstützt.

Benutzerfreundliche GUI: Die Anwendung bietet eine einfache Benutzeroberfläche mit klarer Anordnung, sodass sie auch von weniger technikaffinen Benutzern einfach genutzt werden kann.

## Technologien

Das Projekt wurde unter Verwendung folgender Technologien umgesetzt:

Java 17: Die Kernsprache des Projekts. Java bietet eine stabile Umgebung für die Entwicklung von GUI-Anwendungen.

Swing: Für die Erstellung der grafischen Benutzeroberfläche wurde das Swing-Framework von Java verwendet. Es bietet Widgets wie Buttons, Labels und Panels, um eine benutzerfreundliche Schnittstelle zu gestalten.

JSON: Für die Speicherung des Lernfortschritts wird das JSON-Format verwendet. Dies ermöglicht eine menschenlesbare Struktur der Daten und eine einfache Integration.

Gradle: Das Projekt wurde mit Gradle aufgebaut, einem Build-Tool, das die Verwaltung von Abhängigkeiten und das Erstellen des Projekts vereinfacht.

## Installation und Ausführung

Um das Projekt lokal auszuführen, folgen Sie diesen Schritten:

Gradle installieren: Stellen Sie sicher, dass Gradle installiert ist und korrekt konfiguriert wurde.

Projekt klonen: Klonen Sie das Projekt-Repository auf Ihren lokalen Rechner.

Bauen und Ausführen: Verwenden Sie das Kommando gradle build, um das Projekt zu bauen, und gradle run, um die Anwendung zu starten.

## Nutzung des Programms

Startbildschirm: Nach dem Start des Programms erscheint der Startbildschirm, auf dem der Benutzer entweder ein neues Spiel starten oder ein gespeichertes Spiel laden kann.

Spielmodus: Nachdem das Spiel gestartet wurde, wird ein Wort mit einem Bild angezeigt. Der Benutzer muss das Wort korrekt in das Textfeld eingeben und kann es dann überprüfen.

Speichern und Laden: Der Benutzer kann jederzeit seinen Fortschritt speichern, indem er auf die Schaltfläche "Save" klickt. Zum Laden des Fortschritts klickt man auf "Load".

## Besonderheiten für Anwender

Internetverbindung: Eine aktive Internetverbindung ist erforderlich, um die Bilder der Wörter korrekt anzuzeigen, da die URLs der Bilder im Internet gehostet sind.

Ungültige URL: Falls ein Wort mit einer ungültigen URL angegeben wird, wird das Wort nicht geladen. Dies verhindert Abstürze des Programms, wenn die URL nicht erreichbar ist.

Dateizugriff: Das Programm speichert den Fortschritt in einer JSON-Datei. Der Benutzer sollte sicherstellen, dass er Zugriff auf das Verzeichnis hat, in dem diese Datei gespeichert wird.

## Autor

David Bednarik, Version 1.0