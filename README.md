# Microservices avec Spring Boot, Eureka et API Gateway

## 📌 Introduction

Ce projet est une architecture **microservices** basée sur **Spring Boot**, **Eureka Server** et **API Gateway**.

## 🚀 Architecture

### 🔹 **Eureka Server (Service Discovery)**

Eureka est un **registre centralisé** où les microservices s'inscrivent et se découvrent dynamiquement.

- 📍 **Rôle** : Permet aux services de communiquer sans connaître leurs adresses exactes.
- 📌 **URL du dashboard Eureka** : `http://localhost:8761`

### 🔹 **API Gateway (Filtrage & Sécurité)**

L'API Gateway est le **point d'entrée unique** pour toutes les requêtes vers les microservices.

- 📍 **Rôle** :
  - **Redirige** les requêtes vers les bons microservices.
  - **Gère la sécurité** (JWT, authentification, filtrage).
  - **Unifie les accès** aux services.
- 📌 **Exemple d’URL via Gateway** : `http://localhost:8080/{service}/{endpoint}`

---

## 📌 **Endpoints des Microservices**

### 🔹 **Auth Service **

- `POST /auth/register` → Inscription d'un utilisateur
- `POST /auth/login` → Connexion d'un utilisateur

### 🔹 **Student Service **

- `GET /students` → Récupérer tous les étudiants
- `GET /students/{id}` → Détails d’un étudiant
- `DELETE /students/{id}` → Supprimer un étudiant
- `POST /students` → Ajouter un étudiant

### 🔹 **School Service **

- `GET /school` → Liste des écoles
- `GET /school/{id}` → Détails d’une école
- `DELETE /school/{id}` → Supprimer une école
- `POST /school` → Ajouter une école

### 🌍 **Accès via API Gateway (`http://localhost:8080`)**

Grâce au **Gateway**, les requêtes passent par `http://localhost:8080` :

- `http://localhost:8080/auth/register`
- `http://localhost:8080/students`
- `http://localhost:8080/schools`

---

## 📌 **Lancer le projet**

1. **Démarrer Eureka Server** (`EurekaApplication.java`)
2. **Démarrer API Gateway** (`GatewayApplication.java`)
3. **Démarrer chaque microservice (`AuthService`, `StudentService`, `SchoolService`)**

Vérifiez que **Eureka (`http://localhost:8761`)** affiche tous les services enregistrés. ✅

🚀 **Votre système est prêt à l'emploi !** 🎉
