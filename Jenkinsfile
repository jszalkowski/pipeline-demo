pipeline {
//	tools { 'docker' }

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
		stage('integration') {
			when {
				environment name: 'BRANCH', value: 'integration'
			}
			steps{

				sh 'echo docker build -t pipeline-demo .'
			}
		}
		stage('pr') {
			when {
				environment name: 'BRANCH', value: '**/PR-*'
			}
			steps{
				checkout([$class: 'GitSCM', branches: [[name: '${BRANCH}']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'PreBuildMerge', options: [mergeRemote: 'https://github.com/jszalkowski/pipeline-demo.git', mergeTarget: 'master']]], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/jszalkowski/pipeline-demo.git']]])
        sh 'echo docker run -it --rm --name pipeline-demo -v "$(pwd)":mvn clean install'
			}
		}

		stage('master') {
			when {
				environment name: 'BRANCH', value: 'master'
			}
			steps{

				sh 'echo docker build -t pipeline-demo .'
			}
		}
	}
}
