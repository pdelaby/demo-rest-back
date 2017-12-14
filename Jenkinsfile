pipeline {
    agent any
    tools {
        maven 'M3' 
        jdk 'jdk8u125'
    }
    environment {
      def props = readProperties  file:'/var/lib/jenkins/jobconf/tomcat.properties'
      def deployUrl= "${props['tomcat.deploy.url']}"
	  // obligatoire car il est impossible de resourdre workspace dans l'appel
      def warPath = "${workspace}/target"
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
        stage('Deploy'){
            steps{
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'tomcatdeploy', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
				    //  undeploy 
                    sh 'wget --http-user=$USERNAME --http-password=$PASSWORD "${deployUrl}/manager/text/undeploy?path=/demo-rest-back" -O -'
					
					// deploy
                    sh 'wget --http-user=$USERNAME --http-password=$PASSWORD "${deployUrl}/manager/text/deploy?war=file:${warPath}/demo-rest-back.war&path=/demo-rest-back" -O -'
                }
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