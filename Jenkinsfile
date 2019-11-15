pipeline {
    agent { docker { image 'maven:3.6.0' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn test'
            }
        }
    }
    post{
        always{
            junit 'target/surfire-reports/**/*.xml'
        }
    }
}