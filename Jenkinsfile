pipeline {
  agent any
  stages {
    stage('Building Project') {
      steps {
        build(job: 'AlbumCrudJenkinsMk1', quietPeriod: 5, wait: true)
      }
    }

    stage('Check if war files is created') {
      steps {
        fileExists 'AlbumCrud Jenkins Mk1-0.1.war'
      }
    }

    stage('Renaming') {
      steps {
        sh 'ren .\\build\\libs\\AlbumCrudJenkinsMk1-0.1.war AlbumCrudJenkinsMk1.war'
      }
    }

  }
}