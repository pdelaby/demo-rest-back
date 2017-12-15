pipeline {
    agent any
    tools {
        maven 'M3' 
        jdk 'jdk8u125'
    }
    environment {
      def props = readProperties  file:'/var/lib/jenkins/jobconf/tomcat.properties'
      def deployUrl= "${props['tomcat.deploy.url']}"
      def tcatPath= "${props['tomcat.path']}"
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
		
		stage('Archivage des artefacts') {
            steps{
                archiveArtifacts artifacts: 'target/*.war'
            }
        }
        
		stage('Integration'){
            steps{
                sh "mvn integration-test"
            }
        }
    
        
        stage('Javadoc'){
            steps{
                sh "mvn javadoc:javadoc"
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
        
        stage('Deploy Documentation'){
            parallel {
                stage('Deploy asciidoc'){
                    steps{
                        sh "cp target/generated-docs/* ${tcatPath}/webapps/demo-rest-back-doc"
                    }
                }
                stage('Deploy javadoc'){
                    steps{
                        sh "cp -R target/site/apidocs/* ${tcatPath}/webapps/demo-rest-back-javadoc"
                    }
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
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'target/cucumber', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: 'Rapport de tests cucumbers'])
            
            // publish la doc
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'target/generated-docs', reportFiles: 'demo-rest-back.html', reportName: 'Doc', reportTitles: 'documentation'])
            
            // publish la javadoc
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'target/site/apidocs', reportFiles: 'index.html', reportName: 'JavaDoc', reportTitles: 'JavaDoc'])
        }
    }
}