pipeline {
  agent any
  stages {
    stage('Check Dir') {
      parallel {
        stage('Check Dir') {
          steps {
            pwd()
          }
        }

        stage('') {
          steps {
            retry(count: 23) {
              echo 'temp'
            }

          }
        }

      }
    }

    stage('') {
      steps {
        echo 'asdasdas'
        build(quietPeriod: 4, wait: true, job: 'grails run-app')
      }
    }

  }
}