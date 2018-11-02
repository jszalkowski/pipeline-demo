pipeline {
	tools { maven 'maven' }

	agent any
	parameters {
		string(name: 'BRANCH', description: 'Specify the branch to build')
	}
	stages {
		stage ('scm') {
			steps{
			git branch: "${BRANCH}", url: 'https://github.com/jszalkowski/pipeline-demo.git'
		}
		}
		stage('docker build') {
			steps{
				when {
				  environment name: 'BRANCH', value: 'integration'
				}
				sh 'docker build -t pipeline-demo .'
			}
		}
		stage('docker build2') {
			steps{
				when {
				  environment name: 'BRANCH', value: 'PR-'
				}
				sh 'docker build -t pipeline-demo .'
			}
		}
	}
}
