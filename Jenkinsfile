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
        powershell 'ren C:\\Users\\dkokkonos\\AppData\\Local\\Jenkins\\.jenkins\\workspace\\AlbumCrudJenkinsMk1\\build\\libs\\AlbumCrudJenkinsMk1-0.1.war haha.war'
      }
    }

  }
}