pipeline {
    agent any
    environment {
        DOCKER_HUB = "denidkr24" // Nama akun Docker Hub kamu
    }
    stages {
        stage('Prepare Environment') {
            steps {
                script {
                    BRANCH_NAME = env.BRANCH_NAME?.replaceAll('/', '-').toLowerCase() ?: 'latest'
                    IMAGE_TAG_FE = "${BRANCH_NAME}-${BUILD_NUMBER}"
                    IMAGE_TAG_BE = "${BRANCH_NAME}-${BUILD_NUMBER}"
                }
            }
        }
        stage('Clone Repository') {
            steps {
                checkout scm
            }
        }
        stage('Build Frontend Image') {
            steps {
                dir('frontend') {
                    script {
                        sh """
                        docker build -t ${DOCKER_HUB}/frontend-vue:${IMAGE_TAG_FE} .
                        """
                    }
                }
            }
        }
        stage('Build Backend Image') {
            steps {
                dir('spring-backend/backend-java/') {
                    sh 'mvn clean package -DskipTests'
                    script {
                        sh """
                        docker build -t ${DOCKER_HUB}/be-java-app:${IMAGE_TAG_BE} .
                        """
                    }
                }
            }
        }
        stage('Push Frontend Image') {
            steps {
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                    script {
                        sh """
                        docker push ${DOCKER_HUB}/frontend-vue:${IMAGE_TAG_FE}
                        """
                    }
                }
            }
        }
        stage('Push Backend Image') {
            steps {
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                    script {
                        sh """
                        docker push ${DOCKER_HUB}/be-java-app:${IMAGE_TAG_BE}
                        """
                    }
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
