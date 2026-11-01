# FURPS – vores filmsamling

| | Krav | Opfyldt? | Hvor |
|---|---|---|---|
| F | Man kan søge på en del af en titel | Ja | US4, `MovieCollection.searchMovies` |
| U |Programmet er let at bruge. |Ja | Menuerne er gennemskuelige og nemme at bruge|
| R |Programmet går ikke ned ved bogstaver i årstal | Nej – del 6 |  |
| P |Samlingen gemmes kun, når data er ændret | Ja| US14, `Controller.saveMoviesIfChanged()` |
| S |Progammet er let at vedligeholde og ændre |Ja |Al logik uden for userInterface er dækket af unit test |