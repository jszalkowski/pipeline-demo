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
				tool name: 'default', type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
				sh "env && echo ${default}"
				sh "echo docker build -t pipeline-demo ."
			}
		}
		stage('pr') {
			when {
				environment name: 'BRANCH', value: '**/PR-*'
			}
			steps{
				checkout([$class: 'GitSCM', branches: [[name: '${BRANCH}']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'PreBuildMerge', options: [mergeRemote: 'https://github.com/jszalkowski/pipeline-demo.git', mergeTarget: 'master']]], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/jszalkowski/pipeline-demo.git']]])
				tool name: 'default', type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
        sh 'docker run -it --rm --name pipeline-demo -v "$(pwd)":mvn clean install'
			}
		}

		stage('master') {
			when {
				environment name: 'BRANCH', value: 'master'
			}
			steps{
				tool name: 'default', type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
				sh 'docker build -t pipeline-demo .'
			}
		}
	}
}
