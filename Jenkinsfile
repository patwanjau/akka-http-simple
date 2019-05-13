pipeline {
    agent { docker { image 'openjdk:8u181-jre-stretch' } }
    stages {
        stage('setup'){
            steps {
                apt-get update
            }
        }
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
        stage('test') {
            steps {
                echo 'Testing...'
            }
        }
    }
}