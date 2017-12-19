# Mastermind
Projet OpenClassrooms
Réalisation du jeu Mastermind et d'un jeu de réflexion +/-.

**Lancement de l'application :**

Au démarrage de l'application, le menu principal s'affiche dans la console d'éxécution (voir l'image ci-dessous).

![Menu pricipal](https://img15.hostingpics.net/pics/678614Mastermindmenu.png)

La sélection du jeu s'effectue par une saisie au clavier du chiffre 1 ou 2 et en validant avec la touche "entrée". Une confirmation du jeu sélectionné apparaitra à l'écran.

Il vous sera ensuite demandé de sélectionner un mode de jeu parmis les trois proposés (voir l'image ci-dessous)

![Game_MODE](https://img15.hostingpics.net/pics/990166Mastermindmode.png)

Comment précédemment, la sélection du mode de jeu s'effectue par une saisie au clavier du chiffre 1, 2 ou 3 et en validant avec la touche "entrée". La confirmation du mode sélectionné apparaitra à l'écran.

**Intelligence artificielle et stratégie :**

Pour le mode de jeu "défenseur", l'application charge en mémoire les différentes combinaisons possible en permuttant l'ensemble des chiffres disponibles (voir le fichier de configuration [config.properties](https://github.com/Kybox/Mastermind/blob/master/src/main/resources/config.properties)).

Au fur et à mesure des indices fournis par l'utilisateur, l'application éjecte de sa liste les combinaisons qui ne correspondent pas à celle qui doit être trouvée.

Statistiquement, sur un ensemble de 10 parties jouées, l'application retrouve la combinaison en 10 coups de moyenne, ce qui laisse une chance à l'utilisateur de gagner. Cette moyenne pourra éventuellement être revue à la baisse avec l'ajout d'un menu de sélection de difficultée indépendant au fichier de configuration.