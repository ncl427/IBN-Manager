package groovy

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import ibn_manager.HostToHostIntent
import ibn_manager.LoadBalancingIntent
import ibn_manager.PointToPointIntent
import org.springframework.web.client.RestClientException

class ClientONOS {

    RestBuilder restBuilder

    String url
    String username
    String password

    ClientONOS(String onosUrl, String onosUsername, String onosPassword){

        restBuilder = new RestBuilder()
        url = onosUrl
        username = onosUsername
        password = onosPassword
    }

    RestResponse createHostToHostIntent(HostToHostIntent hostToHostIntent){

        try {

            def res = restBuilder.post("$url/onos/v1/intents"){
                auth("$username","$password")
                contentType("application/json")
                accept("application/json")
                json {
                    type = "HostToHostIntent"
                    appId = "${hostToHostIntent.applicationId.trim()}"
                    priority = hostToHostIntent.priority
                    one = "${hostToHostIntent.macAddressSrc.trim()}"
                    two = "${hostToHostIntent.macAddressDes.trim()}"
                }
            }

            return res

        } catch (RestClientException error){

            println("RestClientException" + error)
            return null
        }
    }

    RestResponse createPathComputingIntent(PointToPointIntent pointToPointIntent){

        try {

            def res = restBuilder.post("$url/onos/v1/intents"){
                auth("$username","$password")
                contentType("application/json")
                accept("application/json")
                json {
                    type = "PointToPointIntent"
                    appId = "${pointToPointIntent.applicationId.trim()}"
                    priority = pointToPointIntent.priority

                    if(!pointToPointIntent.getMacAddressDes().isEmpty()){

                        selector = {
                            criteria = [
                                    {
                                        type = "ETH_SRC"
                                        mac = "${pointToPointIntent.macAddressSrc.trim()}"
                                    },
                                    {
                                        type = "ETH_DST"
                                        mac = "${pointToPointIntent.macAddressDes.trim()}"
                                    }
                            ]
                        }
                    } else
                        selector = {
                            criteria = [
                                    {
                                        type = "ETH_SRC"
                                        mac = "${pointToPointIntent.macAddress.trim()}"
                                    }
                            ]
                        }

                    ingressPoint = {
                        device = "${pointToPointIntent.deviceId.trim()}"
                        port = "${pointToPointIntent.ingressPort}"
                    }
                    egressPoint = {
                        device = "${pointToPointIntent.deviceId.trim()}"
                        port = "${pointToPointIntent.egressPort}"
                    }
                }
            }

            return res

        } catch (RestClientException error){

            println("RestClientException" + error)
            return null
        }
    }

    RestResponse createSlicingIntent(PointToPointIntent pointToPointIntent){

        try {

            def res = restBuilder.post("$url/onos/v1/intents"){
                auth("$username","$password")
                contentType("application/json")
                accept("application/json")
                json {
                    type = "PointToPointIntent"
                    appId = "${pointToPointIntent.applicationId.trim()}"
                    priority = pointToPointIntent.priority
                    selector = {
                        criteria = [
                                {
                                    type = "ETH_SRC"
                                    mac = "${pointToPointIntent.macAddressSrc.trim()}"
                                }
                        ]
                    }
                    treatment = {
                        instructions = [
                                {
                                    type = "L2MODIFICATION"
                                    subtype = "VLAN_PUSH"
                                },
                                {
                                    type = "L2MODIFICATION"
                                    subtype = "VLAN_ID"
                                    vlanId = pointToPointIntent.sliceId
                                }
                        ]
                    }
                    ingressPoint = {
                        device = "${pointToPointIntent.deviceId.trim()}"
                        port = "${pointToPointIntent.ingressPort}"
                    }
                    egressPoint = {
                        device = "${pointToPointIntent.deviceId.trim()}"
                        port = "${pointToPointIntent.egressPort}"
                    }
                }
            }

            return res

        } catch (RestClientException error){

            println("RestClientException" + error)
            return null
        }
    }

    RestResponse deleteIntent(String applicationId, String key){

        try {

            def res  = restBuilder.delete("$url/onos/v1/intents/${applicationId}/${key.trim()}") {
                auth("$username","$password")
                accept("application/json")
            }

            return res

        } catch (RestClientException error){

            println("RestClientException" + error)
            return null
        }
    }

    RestResponse createLoadBalancingIntent(LoadBalancingIntent loadBalancingIntent){

        try {

            def res = restBuilder.post("$url/onos/spm/intent/create"){
                auth("$username","$password")
                contentType("application/json")
                accept("application/json")
                json {
                    hostMac = "${loadBalancingIntent.macAddressSrc.trim()}"
                    switchID = "${loadBalancingIntent.deviceId.trim()}"
                    ingressPort = "${loadBalancingIntent.ingressPort}"
                }
            }

            return res

        } catch (RestClientException error){

            println("RestClientException" + error)
            return null
        }
    }

    RestResponse deleteLoadBalancingIntent(String applicationId, String macAddressSrc){

        try {

            def res  = restBuilder.delete("$url/onos/spm/${applicationId.trim()}/${macAddressSrc.trim()}") {
                auth("$username","$password")
                accept("application/json")
            }

            return res

        } catch (RestClientException error){

            println("RestClientException" + error)
            return null
        }
    }
}
