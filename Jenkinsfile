#!/usr/bin/env groovy
// please look at: https://jenkins.io/doc/book/pipeline/syntax/
BranchName = env.BRANCH_NAME

def nodes = 'test'
if (BranchName == "develop") {
    nodes = nodes + " && TICS"
}

pipeline {
    agent {
        node {
            label nodes
        }
    }
    environment {
        EPOCH_TIME = sh(script: 'date +%s', returnStdout: true).trim()
    }
    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '24'))
        skipDefaultCheckout(true)
    }
    stages {
        stage('Initialize') {
            steps {
                echo "Node labels: ${nodes}"
                sh 'printenv'
                deleteDir()
                sh """
                    if [ -d ~/workspace/master ]; then
                        git clone ~/workspace/master ${WORKSPACE}
                    fi
                """
                checkout([$class: 'GitSCM', branches: [[name: '*/'+env.BRANCH_NAME]], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'CloneOption', depth: 0, honorRefspec: true, noTags: false, reference: '', shallow: false, timeout: 20]], userRemoteConfigs: [[credentialsId: 'd51576c2-35b7-4136-a1fa-5a638fa03b01', url: 'ssh://tfsemea1.ta.philips.com:22/tfs/TPC_Region02/Innersource/_git/csw-android', refspec: '+refs/heads/'+env.BRANCH_NAME+':refs/remotes/origin/'+env.BRANCH_NAME]]])
                sh 'printenv'
                InitialiseBuild()
            }
        }

        stage('Commit') {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    BuildAndUnitTest()
                }
            }
            post {
                always{
                    PublishUnitTestsResults()
                }
            }
        }

        stage('Publish to artifactory') {
            when {
                    anyOf { branch 'master'; branch 'develop*'; branch 'release/platform_*' }
            }
            steps {
                sh '''#!/bin/bash -l
                    set -e
                    ./gradlew --full-stacktrace saveResDep saveAllResolvedDependenciesGradleFormat zipDocuments artifactoryPublish
                '''
            }
        }

        stage('Lint+Jacoco') {
            steps {
                BuildLint()
            }
        }

    }
    post {
        always{
            deleteDir()
            notifyBuild(currentBuild.result)

        }
    }
}
def notifyBuild(String buildStatus = 'STARTED') {
    // build status of null means successful
    buildStatus =  buildStatus ?: 'aborted' || 'failure' || 'fixed' || 'unstable'
   // Default values
   def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
   def details = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]': Check console output at ${env.BUILD_URL}"

    emailext (
        subject: subject,
        body: details,
        to: "dl_iet_amaron@philips.com, dl_iet_exide@philips.com, rallapalli.prasad@philips.com"
    )
}

def InitialiseBuild() {
    committerName = sh (script: "git show -s --format='%an' HEAD", returnStdout: true).trim()
    currentBuild.description = "Submitter: " + committerName + ";Node: ${env.NODE_NAME}"
    echo currentBuild.description
}

def BuildAndUnitTest() {
    sh '''#!/bin/bash -l
        set -e
        chmod -R 755 .
        ./gradlew --refresh-dependencies --full-stacktrace clean assembleRelease

    '''
}

def GenerateJavaDocs(){
    sh '''#!/bin/bash -l
        set -e
        chmod -R 755 .
        ./gradlew :csw:generateJavadocPublicApi
'''
}


def BuildLint() {
    sh '''#!/bin/bash -l
        set -e
        #do not use -PenvCode=${JENKINS_ENV} since the option 'opa' is hardcoded in the archive
        ./gradlew  \
         :csw:lint
    '''
}

def PublishUnitTestsResults() {
    junit allowEmptyResults: true, testResults: '/csw/build/test-results/testReleaseUnitTest/*.xml'
    publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'csw/build/reports/tests/testReleaseUnitTest', reportFiles: 'index.html', reportName: 'csw unit test release'])
}


