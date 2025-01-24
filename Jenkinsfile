pipeline {
    agent any
    environment {
        DOCKER_HUB = "denidkr24" // Nama akun Docker Hub kamu
        BRANCH_NAME = env.BRANCH_NAME?.replaceAll('/', '-').toLowerCase() ?: 'latest' // Nama branch otomatis dari Jenkins
        IMAGE_TAG_FE = "${env.BRANCH_NAME?.replaceAll('/', '-').toLowerCase() ?: 'latest'}-${BUILD_NUMBER}"
        IMAGE_TAG_BE = "${env.BRANCH_NAME?.replaceAll('/', '-').toLowerCase() ?: 'latest'}-${BUILD_NUMBER}"
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
                    docker build -t ${DOCKER_HUB}/frontend-vue:${IMAGE_TAG_FE} .
                    """
                }
            }
        }
        stage('Build Backend Image') {
            steps {
                dir('spring-backend/backend-java/') {
                    sh 'mvn clean package -DskipTests'
                    sh """
                    docker build -t ${DOCKER_HUB}/be-java-app:${IMAGE_TAG_BE} .
                    """
                }
            }
        }
        stage('Push Frontend Image') {
            steps {
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                    sh """
                    docker push ${DOCKER_HUB}/frontend-vue:${IMAGE_TAG_FE}
                    """
                }
            }
        }
        stage('Push Backend Image') {
            steps {
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                    sh """
                    docker push ${DOCKER_HUB}/be-java-app:${IMAGE_TAG_BE}
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
