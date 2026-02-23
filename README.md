# java_socket
# Projet : Application de Chat Client-Serveur en Java

## Membres du projet

* Abdoulaye Toure
* Daouda Diene Cisse


## Description du projet

Ce projet est une application de chat en réseau développée en Java.
Elle permet à plusieurs utilisateurs de communiquer en temps réel à travers un serveur central.

L’application utilise les sockets en Java et une interface graphique réalisée avec Swing pour permettre l’échange de messages entre plusieurs clients connectés.

## Fonctionnement du projet

Le système fonctionne selon une architecture client-serveur :

### 1. Serveur (ServeurChat)

* Le serveur démarre sur le port 5000
* Il attend la connexion des clients
* Il gère plusieurs utilisateurs simultanément
* Il reçoit les messages et les diffuse à tous les clients connectés (broadcast)

### 2. Gestion des clients (ClientHandler)

* Chaque client connecté est géré dans un thread séparé
* Les messages reçus sont horodatés (heure)
* Les messages sont ensuite envoyés à tous les autres utilisateurs

### 3. Client (ClientChat)

* Le client entre son nom au démarrage
* Une interface graphique s’ouvre (fenêtre de chat)
* L’utilisateur peut envoyer et recevoir des messages en temps réel
* Les messages affichent le nom de l’utilisateur et l’heure d’envoi

## Technologies utilisées

* Java
* Java Swing (Interface graphique)
* Sockets (Programmation réseau)
* Threads (Gestion de plusieurs clients)

## Comment exécuter le projet

1. Lancer d’abord le serveur : `ServeurChat`
2. Ensuite lancer le client : `ClientChat`
3. Entrer un nom d’utilisateur
4. Commencer à discuter entre plusieurs clients connectés

## Objectif pédagogique

Ce projet a pour objectif de comprendre :

* La communication réseau en Java
* L’architecture client-serveur
* La gestion des threads
* La création d’interfaces graphiques avec Swing
