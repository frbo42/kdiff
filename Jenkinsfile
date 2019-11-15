pipeline {
    agent { docker { image 'maven:3.6.0' } }

//     environment{
//         BUILD_NUMBER = "${env.BUILD_NUMBER}"
//     }

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
            echo "build number 5 ${env.BUILD_NUMBER}"
//             echo "build number 25 ${BUILD_NUMBER}"
        }
    }
}