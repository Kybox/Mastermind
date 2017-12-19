# Mastermind
Projet OpenClassrooms

![Menu pricipal](https://www.hostingpics.net/viewer.php?id=678614Mastermindmenu.png][IMG]https://img15.hostingpics.net/pics/678614Mastermindmenu.png)

**Intelligence artificielle et stratégie :**

Pour le mode de jeu "défenseur", l'application charge en mémoire les différentes combinaisons possible en permuttant l'ensemble des chiffres disponibles (voir le fichier de configuration [config.properties](https://github.com/Kybox/Mastermind/blob/master/src/main/resources/config.properties)).

Au fur et à mesure des indices fournis par l'utilisateur, l'application éjecte de sa liste les combinaisons qui ne correspondent pas à celle qui doit être trouvée.

Statistiquement, sur un ensemble de 10 parties jouées, l'application retrouve la combinaison en 10 coups de moyenne, ce qui laisse une chance à l'utilisateur de gagner. Cette moyenne pourra éventuellement être revue à la baisse avec l'ajout d'un menu de sélection de difficultée indépendant au fichier de configuration.