pipeline {
    agent { docker { image 'maven:3.6.0' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
    }
    post{
        always{
            echo 'frbo done'
        }
    }
}