pipeline {
    agent any
    tools {
        maven 'M3' 
        jdk 'jdk8u125'
    }
    stages {      
        stage('Clean'){
            steps{
                sh "mvn clean"
            }
        }
        
        stage('Test'){
            steps{
                sh "mvn test"
            }
        }
        
        stage('Package'){
            steps{
                sh "mvn package"
            }
        }
		
		stage('Integration'){
            steps{
                sh "mvn integration-test"
            }
        }
        
        stage('Results') {
            steps{
                archiveArtifacts artifacts: 'target/*.war'
            }
        }
    }
    post{
        always{
            // enregistre les rapports de test
            junit 'target/surefire-reports/TEST-*.xml'
            
            // enregistre les rapports JSON pour le build
            cucumber '**/*.json'
            
            // enregistre les rapports HTML
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'target/cucumber', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: 'test'])
        }
    }
}