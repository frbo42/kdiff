pipeline {
    agent {
        docker {
            image 'maven:3.6.0'
            args '-v /root/.m2:/root/.m2'
        }
    }

    stages {
        stage('build') {
            steps {
                sh 'mvn -B -DskipTests clean package -Dbuild.number=${BUILD_NUMBER}'
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
            archiveArtifacts artifacts: 'target/**/*', fingerprint: true
            junit 'target/surefire-reports/*.xml'
        }
    }
}