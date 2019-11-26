pipeline{
	agent any
	
	stages{
		stage('Maven Clean Package'){
			steps{
				withMaven(maven : 'maven 3.6.3'){
					sh 'mvn clean package'
				}
			}
		}
	}
}