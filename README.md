# Projet_6_QUI_PREND


Dans ce projet, nous devons programmer le jeu  “6-qui-prend” afin de permettre à des joueurs de réaliser une partie de bout en bout . 
Ce dernier est un jeu de cartes dont une partie peut opposer de 2 à 10 joueurs, créé en 1994  par Wolfgang Kramer. 
Il comporte 104 cartes portant le numéro de 1 à 104 et un nombre de “tête de boeufs" compris entre 1 et 7 , ce nombre représente une pénalité associée à la carte. Le nombre de têtes de bœuf se distribue de cette façon, les cartes se terminant par 5 valent 2, celles terminant par 0 valent 3, celles dont son numéro est formé par deux chiffres égaux valent 5 et la carte de numéro 55 vaut 7 car elle vaut 2 et 5 en même temps. 
Une partie de jeu se déroule de cette façon, le paquet de cartes est mélangé et 10 cartes sont attribuées à chaque joueur. Les quatres premières restantes sont disposées sur la table de face visibles sous la forme d’une colonne formant quatre séries. Les cartes restantes seront mises de côté et ne seront pas utilisées.
A chaque tour de jeu, chaque joueur choisit une de ses cartes et la pose face cachée devant lui. Une fois que tous les joueurs ont posé leur carte, on les retourne pour les rendre visible et les cartes sont posées sur les quatre séries selon l'ordre croissant des numéros des cartes posé par les joueurs.


Pour poser une carte, dans une série on doit suivre ces quatre régle :
1. Les cartes d’une même série sont toujours de valeurs croissantes. Une nouvelle carte ne peut être posée à droite d’une série que si sa valeur est supérieure à la dernière carte de la série.
2. Une carte doit toujours être déposée dans la série où la différence entre sa valeur et celle de la dernière carte de la série est la plus faible.
3. Lorsqu’une sixième carte doit être déposée dans une série (en comportant donc déjà 5), le joueur ramasse les cartes de la série (c’est sa pénalité) et la sixième forme alors le début d’une nouvelle série.
4. Si la carte devant être déposée par un joueur a une valeur si faible qu’elle ne peut aller dans aucune série, le joueur ramasse toutes les cartes d’une série de son choix et la carte du joueur forme alors le début d’une nouvelle série.

Une fois que tous les joueurs ont placé leur carte, un nouveau tour commence et cela jusqu'à ce que toutes les cartes distribuées ont été placées sur la table. d’autre part, les cartes ramassées par les joueurs ne vont pas dans leur main mais sont mises de côté. 
A la fin de la partie, le score de chaque joueur correspond à la somme des têtes de bœuf qu'il aura ramassées durant le jeu. Le joueur en ayant le moins est le vainqueur.
