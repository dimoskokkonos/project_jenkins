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

    stage('Rename and Unzip') {
      steps {
        powershell 'cd C:\\Users\\dkokkonos\\AppData\\Local\\Jenkins\\.jenkins\\workspace\\AlbumCrudJenkinsMk1\\build\\libs\\'
        dir(path: 'C:\\Users\\dkokkonos\\AppData\\Local\\Jenkins\\.jenkins\\workspace\\AlbumCrudJenkinsMk1\\build\\libs\\') {
          powershell 'ren AlbumCrudJenkinsMk1-0.1.war AlbumCrudJenkinsMk1.war'
          powershell ' 7z x AlbumCrudJenkinsMk1.warr -oAlbumCrudJenkinsMk1'
        }

      }
    }

    stage('Delete Libs') {
      steps {
        dir(path: 'C:\\Users\\dkokkonos\\AppData\\Local\\Jenkins\\.jenkins\\workspace\\AlbumCrudJenkinsMk1\\build\\libs\\') {
          powershell 'echo asd'
        }

      }
    }

  }
}