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



### Mode de jeu challenger :

Dans ce mode de jeu, c'est à vous de découvrir la combinaison secrète que l'application a généré. Vous devrez saisir une première combinaison au hasard pour que l'application vous donne un premier indice. A vous de découvrir, en un minimum de coups, la combinaison secrète.

| Mastermind | Recherche + / - |
|------------|-----------------|
|![Challenger1](https://img15.hostingpics.net/pics/205380jeu1challenger.png)|![Challenger2](https://img15.hostingpics.net/pics/995840jeu2challenger.png)|



### Mode de jeu défenseur :

Dans ce mode de jeu, vous devrez tout d'abord saisir une combinaison secrète de votre choix (en fonction du [fichier de configuration](https://github.com/Kybox/Mastermind/blob/master/src/main/resources/config.properties)). C'est cette combinaison que l'application tentera de découvrir à l'aide des indices qui vous lui fournirez.

Pour le Mastermind, les indices devront être saisis dans le format suivant : chiffre virgule chiffre où le premier chiffre correspondra au nombre de chiffres-clés bien placés dans votre combinaison secrête, et où le second chiffre correspondra au nombre de chiffres-clés présents dans votre combinaison secrète mais mals placés.

Pour le jeu de recherche +/-, il vous suffira de renseigner, les signes + - ou = pour chaque chiffre-clé, si ces derniers sont inférieurs, supérieurs ou égaux à ceux de votre combinaison secrète.

| Mastermind | Recherche + / - |
|------------|-----------------|
|![Defender1](https://img15.hostingpics.net/pics/817525jeu1defender1.png)|![Defender2](https://img15.hostingpics.net/pics/800579jeu2defender1.png)|


Afin de ne pas induire en erreur l'application en saisissant des indices qui ne correspondraient pas à la proposition de combinaison faite par l'application, une vérification sera effectuée sur chaque indice que vous saisirez. Si une erreur est détectée, il vous sera alors demandé de revérifier votre indice et de le saisir à nouveau (voir l'image ci-dessous)



### Mastermind en mode duel :

Dans ce mode de jeu, vous serez en compétition avec l'application. Il vous sera demandé de saisir la combinaison secrète que l'application devra retrouver. De son côté, l'application va générer une combinaison secrète que vous devrez retrouver. Chacun jouera à tour de rôle. Celui qui aura retrouvé la combinaison secrète en premier gagnera la partie.

![Mastermind-Duel](https://img15.hostingpics.net/pics/538015Mastermindduel.png)
 


### Intelligence artificielle et stratégie :

Pour le mode de jeu "défenseur" du jeu Mastermind, l'application charge en mémoire les différentes combinaisons possible en permuttant l'ensemble des chiffres disponibles (voir le fichier de configuration [config.properties](https://github.com/Kybox/Mastermind/blob/master/src/main/resources/config.properties)).

Au fur et à mesure des indices fournis par l'utilisateur, l'application éjecte de sa liste les combinaisons qui ne correspondent pas à celle qui doit être trouvée.

Statistiquement, sur un ensemble de 10 parties jouées, l'application retrouve la combinaison en une moyenne de 10 coups (minimum 5 coups et maximum 13 coups sur l'ensemble des 10 parties jouées), ce qui laisse une chance à l'utilisateur de gagner. Cette moyenne pourra éventuellement être revue à la baisse avec l'ajout d'un menu de sélection de difficultées, indépendant du fichier de configuration.