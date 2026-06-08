pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/thanhtuyen1902/opencart-automation-framework.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test -DsuiteFile=testng-test.xml'
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
        }
    }
}