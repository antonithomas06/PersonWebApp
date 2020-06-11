pipeline {
	agent any
	
	tools {
		maven "Maven 3.6"   /* may need to be adapted */
	}
    
    parameters {
        booleanParam(
			name: "Extended",
			description: "run extended tests",
			defaultValue: false
		)
    }
    
	stages {
		stage("Build") {
			steps {				
				bat "mvn clean package"
			}
		}
		
		stage("Test") {
			when {
				expression {
					return params.Extended
				}
			}
			steps {
				bat "mvn verify -P exclude-unit-tests,include-extended-tests"
			}
		}

		stage("Analyze") {
			steps {
				script {
					def sonarQubeScannerHome = tool "SonarQube Scanner 4.3"   /* may need to be adapted */
					withSonarQubeEnv("SonarQube 7.9") {   /* may need to be adapted */
						bat "${sonarQubeScannerHome}/bin/sonar-scanner"
					}
				}
			}
		}
	}
	
	post {
		always {
			junit allowEmptyResults: true, testResults: "target/surefire-reports/*.xml,target/failsafe-reports/*.xml"
			jacoco()
		}
	}
}