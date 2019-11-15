pipeline {
    agent { docker { image 'maven:3.6.0' } }

    environment{
        build_number = "${env.BUILD_NUMBER}"
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
            echo 'build number 1 "${env.BUILD_NUMBER}"'
            echo 'build number 2 "${env.BUILD_NUMBER}"'
            echo 'build number 3 ${env.BUILD_NUMBER}'
            echo "build number 5 ${env.BUILD_NUMBER}"
            echo 'build number 21 "${build_number}"'
            echo 'build number 22 "${build_number}"'
            echo 'build number 23 ${build_number}'
            echo "build number 25 ${build_number}"
        }
    }
}