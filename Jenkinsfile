pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Using branch: ' + env.BRANCH_NAME
        sh 'mvn clean compile'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test'
        junit '**/target/surefire-reports/TEST-*.xml'
      }
    }
    stage('Quality Analysis') {
      steps {
        sh 'mvn sonar:sonar -Dsonar.branch.name=' + env.BRANCH_NAME
      } 
    }	
    stage('Package') {
      when { branch "master" }
        steps {
          sh 'mvn dependency:copy-dependencies package -Dmaven.test.skip=true'
          archiveArtifacts artifacts: 'target/rpm/msb-xman/RPMS/noarch/*.rpm', fingerprint: true
          archiveArtifacts artifacts: 'target/msb-xman*.jar', fingerprint: true
        }
    }
    /*stage('Deploy') {
      when { branch "master" }
        steps {
          sh 'mvn deploy'
        }
    } */
  }
  tools {
    maven 'Maven 3.5.2'
    jdk 'jdk 1.8.162'
  }
}
