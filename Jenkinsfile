pipeline {
    agent any
    environment {
    registry = "alvaross147/my-payara-project"
    registryCredential = 'dockerhub'
    dockerImage = ''
    }
    tools {
        maven 'maven_3.6.3' 
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
        stage('Building image') {
            steps{
                script {
                dockerImage = docker.build registry + ":1.0"
                }
            }
        }
        stage('Uploading Image') {
            steps{
                script {
                    docker.withRegistry( '', registryCredential ) {
                    dockerImage.push()
                    }
                }
            }
        }
        stage('Deploy to kubernetes') {
            steps{
            withKubeConfig([credentialsId: 'mnkb', serverUrl: 'http://172.17.0.2:2375', contextName: 'minikube']) {
                sh "kubectl run 'Payara' --image='$registry:1.0' --port='8080'"
                }
            }
        }
        stage('Remove Unused docker image') {
            steps{
                sh "docker rmi $registry:1.0"
            }
        }
    }
}