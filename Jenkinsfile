pipeline {
    agent any

    environment {
            TRELLO_API_KEY = credentials('trello-api-key')
            TRELLO_TOKEN = credentials('trello-token')
            TRELLO_LIST_ID = '66d70b9ed03f861e27e9fb2b'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/AlejoCzombos/TinyURL.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
        stage('Deploy') {
            steps {
                echo "Deploying..."
            }
        }
    }

    post {
        success {
            echo 'Build and Deploy succeeded!'
            withCredentials([string(credentialsId: 'trello_api_key', variable: 'TRELLO_API_KEY'), string(credentialsId: 'trello_token', variable: 'TRELLO_TOKEN')]) {
                sh """
                    curl -X POST \
                    'https://api.trello.com/1/cards?key=${TRELLO_API_KEY}&token=${TRELLO_TOKEN}&idList=${TRELLO_LIST_ID}&name=Build%20Success&desc=The%20build%20and%20deployment%20was%20successful!' \
                    -H 'Content-Type: application/json'
                """
               }
        }
        failure {
            echo 'Build or Deploy failed!'
            sh """
                curl -X POST \
                'https://api.trello.com/1/cards?key=${TRELLO_API_KEY}&token=${TRELLO_TOKEN}&idList=${TRELLO_LIST_ID}&name=Build%20Failure&desc=The%20build%20or%20deployment%20failed.%20Please%20check%20the%20logs.' \
                -H 'Content-Type: application/json'
            """
        }
    }
}