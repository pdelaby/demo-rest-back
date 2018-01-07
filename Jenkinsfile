pipeline {
    agent any
    tools {
        maven 'M3' 
        jdk 'jdk8u125'
    }
    environment {
      def tcatProps = readProperties  file:'/var/lib/jenkins/jobconf/tomcat.properties'
      def deployUrl= "${tcatProps['tomcat.deploy.url']}"
      def tcatPath= "${tcatProps['tomcat.path']}"
	  
	  def apacheProps = readProperties  file:'/var/lib/jenkins/jobconf/apache.properties'
	  def publicHtmlPath = "${apacheProps['apache.docpath']}"
	  
	  // obligatoire car il est impossible de resourdre workspace dans l'appel
      def warPath = "${workspace}/target"
    }
	
    stages { 
                        
        stage('Test'){
            steps{
                sh "mvn clean test"
            }
        }
        
        stage('Package'){
            steps{
                sh "mvn package -DskipTests=true"
            }
			post{
				always{
					archiveArtifacts artifacts: 'target/*.war'
				}
			}
        }		
           		
		stage('Analyse SonarQube') {
			steps{
				withSonarQubeEnv('sonarqube') {      
					sh "mvn sonar:sonar"
				}
			}
		}
		
		stage('Integration'){
            steps{
				// passe les tests d'integration
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
        
        stage('Documentation'){
            parallel {
                stage('Deploy asciidoc'){
                    steps{
						sh "mkdir -p ${publicHtmlPath}/demo-rest-back-doc"
                        sh "cp target/generated-docs/* ${publicHtmlPath}/demo-rest-back-doc"
                    }
                }
                stage('Deploy javadoc'){
                    steps{
						sh "mkdir -p ${publicHtmlPath}/demo-rest-back-javadoc"
                        sh "cp -R target/site/apidocs/* ${publicHtmlPath}/demo-rest-back-javadoc"
                    }
                }
            
                stage('Publish asciidoc'){
                    steps{
                        publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'target/generated-docs', reportFiles: 'demo-rest-back.html', reportName: 'AsciiDoc', reportTitles: 'documentation'])
                    }
                }
                stage('Publish javadoc'){
                    steps{
                        publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'target/site/apidocs', reportFiles: 'index.html', reportName: 'JavaDoc', reportTitles: 'JavaDoc'])
                    }
                }
            }
        }
    }
    
    
    post{
        always{
            // enregistre toujours les rapports de test
            junit 'target/surefire-reports/TEST-*.xml'           
			
			
			// enregistre toujours les rapports JSON pour le build (pour voir les échecs)
			cucumber '**/*.json'
		
			// enregistre les rapports HTML (pour voir les échecs)
			publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'target/cucumber', reportFiles: 'index.html', reportName: 'Rapports cucumber', reportTitles: 'Rapport de tests cucumbers'])
			
			
			// TOUJOURS TOUJORUS nettoyer le workspace
			cleanWs()
        }
    }
}