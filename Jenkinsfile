pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'develop',
                    url: 'https://github.com/thanhtuyen1902/opencart-automation-framework.git'
            }
        }

        stage('Build & Test') {
            steps {
//                 bat 'mvn clean test -DsuiteFile=testng-test.xml'
                   bat 'mvn clean test -DsuiteFile=testng-smoke.xml'
                   
            }
        }
    }
    post {

        success {
            echo 'BUILD SUCCESS'
        }

        failure {
            echo 'BUILD FAILED'
        }

        always {
            echo 'Done'
            junit allowEmptyResults: true,
                  testResults: 'target/surefire-reports/*.xml'

//             publishHTML([
//                 allowMissing: true,
//                 alwaysLinkToLastBuild: true,
//                 keepAll: true,
//                 reportDir: 'reports',
//                 reportFiles: 'TestReport.html',
//                 reportName: 'Extent Report'
//             ])
        }
    }
}