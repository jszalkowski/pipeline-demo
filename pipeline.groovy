pipeline {
	tools { maven 'maven' }

	agent any

	stages {
		stage('docker build') {
			when { branch 'integration' }
			sh 'docker build -t pipeline-demo .'
		}
	}
}

