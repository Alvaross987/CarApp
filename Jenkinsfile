pipeline {
    agent any
    environment {
    registry = "alvaross147/docker-test"
    registryCredential = 'dockerhub'
    }
    tools {
        maven 'maven_6.3.6' 
    }
        stages {
            
        stage('Cloning Git') {
            steps {
                git 'https://github.com/Alvaross987/CarApp.git'
            }
        }
        stage('Maven Clean Package') {
            steps {
                sh "mvn clean package" 
            }
        }
        stage('Preparing Dockerfile') {
            steps {
                sh "mv /var/jenkins_home/workspace/CarAppPipe/target/app-0.0.1-SNAPSHOT.war /var/jenkins_home/workspace/CarAppPipe/target/app.war"
                sh "mv /var/jenkins_home/workspace/CarAppPipe/target/app.war /var/jenkins_home/workspace/CarAppPipe/app.war"
            }
        }
    }
}