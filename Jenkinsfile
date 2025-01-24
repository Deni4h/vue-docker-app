pipeline {
    agent any
    environment {
        DOCKER_HUB = "denidkr24" // Nama akun Docker Hub kamu
        BRANCH_NAME = "${env.BRANCH_NAME}" // Nama branch otomatis dari Jenkins
    }
    stages {
        stage('Clone Repository') {
            steps {
                checkout scm
            }
        }
        stage('Build Frontend Image') {
            steps {
                dir('frontend') {
                    sh """
                    docker build -t ${DOCKER_HUB}/frontend-vue:${BRANCH_NAME} .
                    """
                }
            }
        }
        stage('Build Backend Image') {
            steps {
                dir('spring-backend') {
                    sh 'mvn clean package -DskipTests'
                    sh """
                    docker build -t ${DOCKER_HUB}/be-java-app:${BRANCH_NAME} .
                    """
                }
            }
        }
        stage('Push Frontend Image') {
            steps {
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                    sh """
                    docker push ${DOCKER_HUB}/frontend-vue:${BRANCH_NAME}
                    """
                }
            }
        }
        stage('Push Backend Image') {
            steps {
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                    sh """
                    docker push ${DOCKER_HUB}/be-java-app:${BRANCH_NAME}
                    """
                }
            }
        }
        stage('Deploy Application') {
            steps {
                sh """
                docker-compose down
                docker-compose up -d
                """
            }
        }
    }
}
