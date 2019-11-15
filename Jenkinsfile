pipeline {
    agent { docker { image 'maven:3.6.0' } }

    stages {
        stage('build') {
            steps {
                sh 'mvn install -Dbuild.number=${BUILD_NUMBER}'
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