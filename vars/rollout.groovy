#!/usr/bin/groovy
import io.fabric8.openshift.client.DefaultOpenShiftClient
import io.fabric8.openshift.client.OpenShiftClient

/**
 * Deploys the latest version of the deploymentconfig
 * @param parameters
 * @return
 */
def call(Map parameters = [:]) {

    def deploymentConfig = parameters.get('deploymentConfig', 'unknown')
    def namespace = parameters.get('namespace', 'ipaas-staging')

    openshiftDeploy(depCfg: "${deploymentConfig}", namespace: "${namespace}")

    // The snippet below is broken, as it fails when run inside a pod. Possibly a bug in `oc`.
    // Update: This is due to permission issues.
    //container(name: 'openshift') {
    //    sh "oc rollout latest ${deploymentConfig} -n ${namespace}"
    //}
}