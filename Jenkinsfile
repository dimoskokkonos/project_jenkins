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

    stage('Changing Working Directory') {
      steps {
        powershell 'cd C:\\Users\\dkokkonos\\AppData\\Local\\Jenkins\\.jenkins\\workspace\\AlbumCrudJenkinsMk1\\build\\libs\\'
        powershell 'get-location'
        powershell 'ren AlbumCrudJenkinsMk1-0.1.war AlbumCrudJenkinsMk1.war'
      }
    }

    stage('Renaming war file') {
      steps {
        powershell 'ren AlbumCrudJenkinsMk1-0.1.war AlbumCrudJenkinsMk1.war'
      }
    }

  }
}