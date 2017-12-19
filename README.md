# Mastermind
Projet OpenClassrooms

Réalisation du jeu Mastermind et d'un jeu de réflexion +/-.



### Lancement de l'application :

Au démarrage de l'application, le menu principal s'affiche dans la console d'éxécution (voir l'image ci-dessous).

![Menu pricipal](https://img15.hostingpics.net/pics/678614Mastermindmenu.png)

La sélection du jeu s'effectue par une saisie au clavier du chiffre 1 ou 2 et en validant avec la touche "entrée". Une confirmation du jeu sélectionné apparaitra à l'écran.

Il vous sera ensuite demandé de sélectionner un mode de jeu parmis les trois proposés (voir l'image ci-dessous)

![Game_MODE](https://img15.hostingpics.net/pics/990166Mastermindmode.png)

Comment précédemment, la sélection du mode de jeu s'effectue par une saisie au clavier du chiffre 1, 2 ou 3 et en validant avec la touche "entrée". La confirmation du mode sélectionné apparaitra à l'écran.



### Mastermind en mode challenger :

Dans ce mode de jeu, c'est à vous de découvrir la combinaison secrète que l'application a généré. Vous devrez saisir une première combinaison au hasard pour que l'application vous donne un premier indice. A vous de découvrir en un minimum de coups la combinaison secrète.

![Challenger](https://img15.hostingpics.net/pics/912131Mastermindchallenger.png)



### Mastermind en mode défenseur :

Dans ce mode de jeu, vous devrez tout d'abord saisir une combinaison secrète de votre choix (en fonction du [fichier de configuration](https://github.com/Kybox/Mastermind/blob/master/src/main/resources/config.properties)). C'est cette combinaison que l'application tentera de découvrir à l'aide des indices qui vous lui fournirez. Les indices devront être saisis avec le formation suivant : chiffre virgule chiffre.

![Defender](https://img15.hostingpics.net/pics/999122Masterminddefender.png)

Le premier chiffre correspondra au nombre de chiffres bien placés dans votre combinaison secrète. Le second chiffre correspondra au nombre de chiffres présent dans votre combinaison secrète mais mals placés.

Exemple :
>1,2

(1 bien placé et 2 présents)

Afin de ne pas induire en érreur l'application en saisissant des indices qui ne correspondraient pas à la proposition de combinaison faite par l'application, une vérification sera effectuée sur chaque indice que vous saisirez. Si une erreur est détectée, il vous sera alors demandé de revérifier votre indice et de le saisir à nouveau (voir l'image ci-dessous)

![ERROR](https://img15.hostingpics.net/pics/255866Masterminderreur.png)



### Intelligence artificielle et stratégie :

Pour le mode de jeu "défenseur" du jeu Mastermind, l'application charge en mémoire les différentes combinaisons possible en permuttant l'ensemble des chiffres disponibles (voir le fichier de configuration [config.properties](https://github.com/Kybox/Mastermind/blob/master/src/main/resources/config.properties)).

Au fur et à mesure des indices fournis par l'utilisateur, l'application éjecte de sa liste les combinaisons qui ne correspondent pas à celle qui doit être trouvée.

Statistiquement, sur un ensemble de 10 parties jouées, l'application retrouve la combinaison en 10 coups de moyenne, ce qui laisse une chance à l'utilisateur de gagner. Cette moyenne pourra éventuellement être revue à la baisse avec l'ajout d'un menu de sélection de difficultée indépendant au fichier de configuration.