package groovy

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import ibn.manager.HostToHostIntent
import ibn.manager.PointToPointIntent
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
                    appId = "${hostToHostIntent.applicationId}"
                    priority = hostToHostIntent.priority
                    one = "${hostToHostIntent.macAddressSrc}"
                    two = "${hostToHostIntent.macAddressDes}"
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
                    appId = "${pointToPointIntent.applicationId}"
                    priority = pointToPointIntent.priority
                    selector = {
                        criteria = [
                                {
                                    type = "ETH_SRC"
                                    mac = "${pointToPointIntent.macAddress}"
                                }
                        ]
                    }
                    ingressPoint = {
                        device = "${pointToPointIntent.deviceId}"
                        port = "${pointToPointIntent.ingressPort}"
                    }
                    egressPoint = {
                        device = "${pointToPointIntent.deviceId}"
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
                    appId = "${pointToPointIntent.applicationId}"
                    priority = pointToPointIntent.priority
                    selector = {
                        criteria = [
                                {
                                    type = "ETH_SRC"
                                    mac = "${pointToPointIntent.macAddress}"
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
                        device = "${pointToPointIntent.deviceId}"
                        port = "${pointToPointIntent.ingressPort}"
                    }
                    egressPoint = {
                        device = "${pointToPointIntent.deviceId}"
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

            def res  = restBuilder.delete("$url/onos/v1/intents/${applicationId}/${key}") {
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
