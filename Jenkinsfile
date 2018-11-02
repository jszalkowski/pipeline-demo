pipeline {
	tools { maven 'maven' }

	agent any

	stages {
		stage('docker build') {
			when { branch 'integration' }
			steps {
			sh 'env'
			sh 'docker build -t pipeline-demo .'
			}
		}
	}
}

