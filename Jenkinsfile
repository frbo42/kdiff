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
            archiveArtifacts artifacts: 'target/**/*', fingerprint: true
            junit 'target/surefire-reports/**/*.xml'
        }
    }
}