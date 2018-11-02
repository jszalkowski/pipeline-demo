pipeline {
	tools { maven 'maven' }

	agent any
	parameters {
		string(name: 'BRANCH', defaultValue: 'master', description: 'Specify the branch to build')
	}
	stages {
		stage ('scm') {
			git branch: "${BRANCH}", url: 'https://github.com/jszalkowski/pipeline-demo.git'
		}
		stage('docker build') {
			steps{
				when { branch 'integration' }
				sh 'docker build -t pipeline-demo .'
			}
		}
		stage('docker build') {
			steps{
				when { branch 'PR-' }
				sh 'docker build -t pipeline-demo .'
			}
		}
	}
}

