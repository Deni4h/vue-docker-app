pipeline {
    agent any
    environment {
        DOCKER_HUB = "denidkr24"
        BRANCH_NAME = "${env.GIT_BRANCH?.replaceAll('/', '-') ?: 'latest'}"
        BUILD_TIMESTAMP = "${new Date().format('yyyyMMddHHmmss')}"
        BUILD_TAG = "${BRANCH_NAME}-${BUILD_TIMESTAMP}" // Kombinasi branch dan timestamp
    }
    stages {
        stage('Clone Repository') {
            steps {
                checkout scm
            }
        }
        stage('Debug Build Tag') {
            steps {
                echo "Branch Name: ${BRANCH_NAME}"
                echo "Build Tag: ${BUILD_TAG}"
                sh 'env | grep BUILD_TAG || true'
            }
        }
        stage('Build Frontend Image') {
            steps {
                dir('frontend') {
                    sh """
                    docker build -t ${DOCKER_HUB}/frontend-vue:${BUILD_TAG} .
                    """
                }
            }
        }
        stage('Build Backend Image') {
            steps {
                dir('spring-backend/backend-java/') {
                    sh 'mvn clean package -DskipTests'
                    sh """
                    docker build -t ${DOCKER_HUB}/be-java-app:${BUILD_TAG} .
                    """
                }
            }
        }
        stage('Push Frontend Image') {
            steps {
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                    sh """
                    docker push ${DOCKER_HUB}/frontend-vue:${BUILD_TAG}
                    """
                }
            }
        }
        stage('Push Backend Image') {
            steps {
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                    sh """
                    docker push ${DOCKER_HUB}/be-java-app:${BUILD_TAG}
                    """
                }
            }
        }
    }
}
