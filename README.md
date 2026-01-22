# Gaming Chair List

Une plateforme sociale de découverte et de critique de jeux vidéo, alimentée par l'API Steam.

## Description

**Gaming Chair List** est une application web permettant aux joueurs de noter, critiquer et organiser leur collection de jeux vidéo. L'objectif est de proposer une alternative communautaire aux magasins de jeux, en mettant l'accent sur les avis utilisateurs et la curation de listes.

Le projet repose sur une architecture technique hybride :

1. Les métadonnées des jeux (titre, image, date de sortie) sont récupérées via la **Steam Web API**.
2. Les données sociales (utilisateurs, critiques, listes, abonnements) sont gérées indépendamment dans notre propre base de données pour garantir l'intégrité de la communauté.

## Fonctionnalités Clés

* **Catalogue Hybride :** Recherche de jeux via Steam et mise en cache automatique des données dans la base locale.
* **Système de Notation :** Les utilisateurs peuvent attribuer une note (/5) et rédiger une critique détaillée.
* **Listes Personnalisées :** Création de listes de jeux (ex: "Mes attentes 2025", "Jeux finis") avec gestion de l'ordre.
* **Réseau Social :** Système d'abonnement (Follow) permettant de suivre l'activité d'autres utilisateurs.
* **Gestion de Profil :** Tableau de bord utilisateur regroupant statistiques, dernières critiques et listes publiques.

## Architecture Technique

Le projet suit une architecture séparée (Headless) :

* **Frontend :** Application Vue.js qui communique uniquement avec notre API Backend.
* **Backend :** API RESTful développée avec Spring Boot. Elle agit comme un orchestrateur entre le client, la base de données et l'API externe Steam.
* **Base de Données :** PostgreSQL pour la persistance des données relationnelles.

## Stack Technique

**Backend**

* **Langage :** Java 21
* **Framework :** Spring Boot (Spring Web, Spring Data JPA, Spring Security)
* **Build Tool :** Maven
* **Authentification :** JWT (JSON Web Tokens)

**Frontend**

* **Framework :** Vue.js 3 (Composition API)
* **Routing :** Vue Router
* **State Management :** Pinia
* **Client HTTP :** Axios

**Données & Externe**

* **Base de données :** PostgreSQL
* **API Tierce :** Steam Web API & Steam Storefront API

## Installation et Démarrage

### Prérequis

* Java 21 (JDK)
* Node.js et npm
* PostgreSQL installé et en cours d'exécution

### 1. Configuration de la Base de Données

Créez une base de données locale nommée `gaming_chair_list` dans PostgreSQL.
Configurez vos identifiants dans le fichier `src/main/resources/application.properties` du backend.

### 2. Démarrage du Backend (Spring Boot)

```bash
cd backend
./mvnw spring-boot:run

```

L'API sera accessible sur `http://localhost:8080`.

### 3. Démarrage du Frontend (Vue.js)

```bash
cd frontend
npm install
npm run dev

```

L'application sera accessible sur `http://localhost:5173` (ou le port indiqué par Vite).

## Auteurs

* **Anton Dion** - Projet Solo