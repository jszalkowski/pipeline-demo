pipeline {
	tools { 'docker' }

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
			when {
				environment name: 'BRANCH', value: 'integration'
			}
			steps{

				sh 'docker build -t pipeline-demo .'
			}
		}
		stage('docker build2') {
			when {
				environment name: 'BRANCH', value: 'PR-'
			}
			steps{

				sh 'docker build -t pipeline-demo .'
			}
		}
	}
}
