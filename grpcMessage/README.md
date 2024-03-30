# Service de messagerie

Ce projet est une implémentation d'un service de messagerie utilisant gRPC en Java. Il permet l'envoi de messages texte à un destinataire spécifié et la récupération des messages reçus pour un utilisateur donné.

-Clonez le dépôt Git du projet

-Avant d'exécuter le projet, assurez-vous d'installer les dépendances Maven et compiler le code en exécutant les commandes suivantes :

```bash
  mvn install

```

```bash
  mvn clean compile
```

-Démarrez le serveur en exécutant la commande suivante dans votre terminal ou invite de commande :

```bash
  mvn exec:java@server-execution
```

-Ensuite, démarrez le client en exécutant la commande suivante dans un autre terminal ou invite de commande :

```bash
mvn exec:java@client-execution
```
