package groovy

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import ibn.manager.HostToHostIntent
import ibn.manager.PointToPointIntent
import org.springframework.web.client.RestClientException

class ClientONOS {

    RestBuilder restBuilder

    ClientONOS(){
        restBuilder = new RestBuilder()
    }

    RestResponse createHostToHostIntent(HostToHostIntent hostToHostIntent){

        try {

            def res = restBuilder.post("http://117.17.102.192:8181/onos/v1/intents"){
                auth("karaf","karaf")
                contentType("application/json")
                accept("application/json")
                json {
                    type = "HostToHostIntent"
                    appId = "org.onosproject.cli"
                    priority = 100
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

    RestResponse createPointToPointIntent(PointToPointIntent pointToPointIntent, boolean first){

        try {

            def res = restBuilder.post("http://117.17.102.192:8181/onos/v1/intents"){
                auth("karaf","karaf")
                contentType("application/json")
                accept("application/json")
                json {
                    type = "PointToPointIntent"
                    appId = "org.onosproject.cli"
                    priority = 100
                    if (first){
                        selector = {
                            criteria = [
                                    {
                                        type = "ETH_SRC"
                                        mac = "${pointToPointIntent.macAddress}"
                                    }
                            ]
                        }
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

            def res  = restBuilder.delete("http://117.17.102.192:8181/onos/v1/intents/${applicationId}/${key}") {
                auth("karaf", "karaf")
                accept("application/json")
            }

            return res

        } catch (RestClientException error){

            println("RestClientException" + error)
            return null
        }
    }
}
