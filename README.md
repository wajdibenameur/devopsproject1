Parfait ! Je peux te créer un **README.md** clair et structuré pour ton projet Jenkins + CI/CD avec GitHub, ngrok et gestion des repos publics/privés. Voici une version complète et professionnelle :

---

# 🚀 DevOps Project 1 – Jenkins CI/CD Pipeline

## 📘 Description du projet

Ce projet met en place une **pipeline CI/CD avec Jenkins** pour automatiser le déploiement et les tests de vos applications à chaque push sur GitHub.
Le pipeline est capable de traiter **des dépôts publics et privés** via des **credentials Jenkins** et expose les applications avec **ngrok** pour un accès externe temporaire.

---

## ⚙️ Fonctionnalités principales

* Déclenchement automatique de la pipeline Jenkins à chaque push sur GitHub (webhook).
* Support des **dépôts publics et privés** grâce à l’utilisation de credentials Jenkins.
* Déploiement et test automatisés des applications.
* Exposition des applications via **ngrok** pour un accès externe sécurisé et temporaire.
* Visualisation des étapes et logs de la pipeline directement dans Jenkins.

---

## 🧩 Architecture de la pipeline

1. **GitHub Webhook** : déclenche la pipeline Jenkins à chaque push.
2. **Jenkins Server** : récupère le code, utilise les credentials si nécessaire, et lance les jobs.
3. **Build & Test** : compilation et tests automatiques de l’application.
4. **ngrok** : expose localement l’application pour test ou démonstration externe.

---

## 🛠️ Technologies utilisées

* **CI/CD** : Jenkins
* **Versioning** : Git / GitHub
* **Tunnel externe** : ngrok
* **Langages** : selon projet (Angular, Spring Boot, Node.js, etc.)
* **Gestion des credentials** : Jenkins Credentials pour les repos privés

---

## 🚀 Installation et utilisation

### 1️⃣ Cloner le projet

```bash
git clone https://github.com/wajdibenameur/devopsproject1.git
cd devopsproject1
```

### 2️⃣ Configurer Jenkins

1. Installer Jenkins sur votre machine ou serveur.
2. Ajouter les **credentials** pour accéder aux dépôts privés (GitHub username/token ou SSH key).
3. Installer les plugins nécessaires :

   * GitHub plugin
   * Pipeline plugin
   * ngrok plugin (ou configurer ngrok via script)

### 3️⃣ Configurer le webhook GitHub

* Dans ton dépôt GitHub, aller dans **Settings → Webhooks → Add webhook**
* URL : `http://<JENKINS_URL>/github-webhook/`
* Content type : `application/json`
* Trigger : *Just the push event*

### 4️⃣ Lancer la pipeline Jenkins

* Créer un **Jenkinsfile** à la racine du projet (exemple minimal) :

```groovy
pipeline {
    agent any

    environment {
        NGROK_TOKEN = credentials('ngrok-token')
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/wajdibenameur/devopsproject1.git', branch: 'main', credentialsId: 'github-credentials'
            }
        }
        stage('Build') {
            steps {
                sh 'echo "Build your application here"'
            }
        }
        stage('Test') {
            steps {
                sh 'echo "Run tests here"'
            }
        }
        stage('Expose with ngrok') {
            steps {
                sh 'ngrok http 8080 --authtoken $NGROK_TOKEN &'
            }
        }
    }
}
```

---

## 💡 Points forts

* CI/CD automatisé à chaque push sur GitHub.
* Support complet pour dépôts **publics et privés**.
* Exposition rapide et sécurisée via ngrok.
* Pipeline entièrement configurable et extensible pour différents projets.

---

## 👨‍💻 Auteur

**Wajdi Ben Ameur**
📧 [wajdibenameur@gmail.com](mailto:wajdibenameur@gmail.com)
💼 [GitHub - wajdibenameur](https://github.com/wajdibenameur)

---

