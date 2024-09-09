pipeline {
    agent any

    stages {
        stage('Checkout Git Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/AlejoCzombos/TinyURL.git'
            }
        }
        stage('Build Project') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('Run Tests and Generate Package') {
            steps {
                bat 'mvn package'
            }
        }
        stage('Commit Package') {
            steps {
                // Agregar el package al repositorio
                bat 'git add target/*'
                bat 'git commit -m "Agregar el package generado por Jenkins"'
            }
        }
        stage('Push to Git Repository'){
            steps {
                withCredentials([gitUsernamePassword(credentialsId: 'github-token', gitToolName: 'Default')]) {
                    bat 'git push origin main'
                }
            }
        }
        stage('Deploy to Render') {
            steps {
                withCredentials([string(credentialsId: 'deploy-render-hook', variable: 'DEPLOY_RENDER_HOOK')]) {
                    // Curl al hook de Render para ejecutar el deploy
                    bat '''
                        curl -X POST \
                        "%DEPLOY_RENDER_HOOK%"
                    '''
                }
            }
        }
    }

    post {
        success {
            echo 'Build and Deploy succeeded!'
            withCredentials([string(credentialsId: 'trello-api-key', variable: 'TRELLO_API_KEY'), string(credentialsId: 'trello-token', variable: 'TRELLO_TOKEN')]) {
                bat '''
                    curl -X POST \
                    "https://api.trello.com/1/cards?key=%TRELLO_API_KEY%&token=%TRELLO_TOKEN%&idList=66d70b9ed03f861e27e9fb2b&name=Build%20Success&desc=The%20build%20and%20deployment%20was%20successful!" \
                    -H "Content-Type: application/json"
                '''
            }
        }
        failure {
            echo 'Build or Deploy failed!'
            withCredentials([string(credentialsId: 'trello-api-key', variable: 'TRELLO_API_KEY'), string(credentialsId: 'trello-token', variable: 'TRELLO_TOKEN')]) {
                bat '''
                    curl -X POST \
                    "https://api.trello.com/1/cards?key=%TRELLO_API_KEY%&token=%TRELLO_TOKEN%&idList=66d70b9ed03f861e27e9fb2b&name=Build%20Failure&desc=The%20build%20or%20deployment%20failed.%20Please%20check%20the%20logs" \
                    -H "Content-Type: application/json"
                '''
            }
        }
    }

}