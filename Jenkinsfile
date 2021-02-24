pipeline {
  agent any
  stages {
    stage('Building Project') {
      steps {
        build(job: 'AlbumCrud Jenkins Mk1', quietPeriod: 5, wait: true)
      }
    }

    stage('Check if war files is created') {
      steps {
        fileExists 'AlbumCrud Jenkins Mk1-0.1.war'
      }
    }

    stage('') {
      steps {
        pwd()
      }
    }

  }
}