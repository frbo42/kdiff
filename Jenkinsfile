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
            archiveArtifacts artifacts: 'target/**/*'
            junit 'target/surfire-reports/**/*.xml'
        }
    }
}