pipeline {
    agent none
    stages {
       stage('Build') {
           agent {
               docker {
                   image 'maven:3.5.4'
                   args '--network=plumbing_default'
               }
           }
           steps {
               configFileProvider(
                       [configFile(fileId: 'nexus', variable: 'MAVEN_SETTINGS')]) {
                   sh 'mvn -s $MAVEN_SETTINGS clean install deploy -B'
               }
           }
       }
       stage('Deploy to Tomcat') {
           agent {
               docker {
                   image 'alpine'
               }
           }
           steps {
               sh 'cp target/academio.war /usr/share/jenkins/ref/academio/academio.war'
           }
       }
       stage('Sonar') {
           agent  {
               docker {
                   image 'sebp/sonar-runner'
                   args '--network=plumbing_default'
               }
           }
           steps {
               sh '/opt/sonar-runner-2.4/bin/sonar-runner'
           }
       }
    }
}