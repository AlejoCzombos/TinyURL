pipeline {
    agent any

    environment {
            TRELLO_API_KEY = credentials('trello_api_key')
            TRELLO_TOKEN = credentials('trello_token')
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/AlejoCzombos/TinyURL.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Package') {
            steps {
                bat 'mvn package'
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
            echo 'api-key: %TRELLO_API_KEY%'
            echo 'api-key: ${TRELLO_API_KEY}'
            echo 'api-key: ${env.TRELLO_API_KEY}'
            echo 'api-key: $TRELLO_API_KEY'
            bat '''
                curl -X POST \
                "https://api.trello.com/1/cards?key=%TRELLO_API_KEY%&token=%TRELLO_TOKEN%&idList=66d70b9ed03f861e27e9fb2b&name=Build%20Success&desc=The%20build%20and%20deployment%20was%20successful!" \
                -H "Content-Type: application/json"
            '''
        }
        failure {
            echo 'Build or Deploy failed!'
            echo 'Build and Deploy succeeded!'
            echo 'api-key: %TRELLO_API_KEY%'
            echo 'api-key: ${TRELLO_API_KEY}'
            echo 'api-key: ${env.TRELLO_API_KEY}'
            echo 'api-key: $TRELLO_API_KEY'
            bat '''
                curl -X POST \
                "https://api.trello.com/1/cards?key=%TRELLO_API_KEY%&token=%TRELLO_TOKEN%&idList=66d70b9ed03f861e27e9fb2b&name=Build%20Failure&desc=The%20build%20or%20deployment%20failed.%20Please%20check%20the%20logs." \
                -H "Content-Type: application/json"
            '''
        }
    }

}