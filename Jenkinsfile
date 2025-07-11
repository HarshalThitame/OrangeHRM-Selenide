pipeline {
    agent any

    environment {
        MAVEN_OPTS = "-Dmaven.repo.local=.m2/repository"
        ENV = "qa"
    }

    parameters {
        choice(name: 'ENV', choices: ['dev', 'qa', 'staging'], description: 'Choose test environment')
    }

    tools {
        maven 'Maven_3'
        jdk 'JDK_17'
    }

   stages {
      stage('Checkout') {
        steps {
          git 'https://github.com/HarshalThitame/OrangeHrmAllure.git'
        }
      }

      stage('Start Selenium Grid via Docker') {
        steps {
          bat 'docker-compose -f selenium-grid-docker\\docker-compose.yml up -d'
        }
      }

      stage('Build Project') {
        steps {
          bat 'mvn clean compile'
        }
      }

      stage('Run Tests') {
        steps {
          bat 'mvn test -Dbrowser=chrome -Denv=%ENV%'
        }
      }

      stage('Generate Allure Report') {
        steps {
          bat 'allure generate target\\allure-results --clean -o allure-report'
        }
      }

      stage('Publish Allure Report') {
        steps {
          allure includeProperties: false, jdk: '', commandline: 'Allure', results: [[path: 'target/allure-results']]
        }
      }
    }

    post {
      always {
        echo "Cleaning up containers..."
        bat 'docker-compose -f selenium-grid-docker\\docker-compose.yml down'
      }
      success {
        echo "Build and tests successful!"
      }
      failure {
        echo "Build failed!"
      }
    }
  }
