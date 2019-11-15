pipeline {
    agent { docker { image 'maven:3.6.0' } }

    environment{
        build.number = BUILD_NUMBER
    }

    stages {
        stage('build') {
            steps {
                sh 'mvn install'
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