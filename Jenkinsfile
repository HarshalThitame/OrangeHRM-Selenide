pipeline {
    agent any

    environment {
        MAVEN_OPTS = "-Dmaven.repo.local=.m2/repository"
        ENV = "dev"
    }

    parameters {
        choice(name: 'ENV', choices: ['dev', 'qa', 'staging'], description: 'Choose test environment')
    }

    tools {
        maven 'Maven_3'
        jdk 'JDK_17'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/HarshalThitame/OrangeHRM-Selenide.git', branch: 'master'
            }
        }

        stage('Build and Compile') {
            steps {
                bat 'mvn clean compile -Denv=%ENV%'
            }
        }

        stage('Execute Tests') {
            steps {
                bat 'mvn test -Denv=%ENV%'
            }
        }

        stage('Generate Allure Report') {
            steps {
                bat 'mvn allure:report'
                allure includeProperties: false, jdk: '', reportBuildPolicy: 'ALWAYS', results: [[path: 'target\\allure-results']]
            }
        }

        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: 'target\\allure-report\\**', fingerprint: true
            }
        }
    }

    post {
        always {
            junit 'target\\surefire-reports\\*.xml'
            cleanWs()
        }

        failure {
            mail to: 'qa-team@example.com',
                 subject: "❌ Jenkins Test Failure: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "Check Jenkins for more details: ${env.BUILD_URL}"
        }
    }
}
