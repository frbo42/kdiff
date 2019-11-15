pipeline {
    agent { docker { image 'maven:3.6.0' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('test') {
            steps {
                sh 'mvn test'
            }
        }
    }
    post{
        always{
            junit 'build/reports/**/*.xml'
        }
    }
}