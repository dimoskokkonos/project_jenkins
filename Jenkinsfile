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

        stage('The temp stage') {
          steps {
            retry(count: 23) {
              echo 'temp'
            }

          }
        }

      }
    }

    stage('The testing stage') {
      steps {
        echo 'asdasdas'
        mail(subject: 'The Mail Subject', body: 'Not spam', from: 'dkokkonos@knowledge.com', to: 'dimoskokkonos20@gmail.com')
      }
    }

  }
}