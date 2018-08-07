package groovy

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
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

            def res = restBuilder.post("$url/onos/v1/intents"){
                auth("$username","$password")
                contentType("application/json")
                accept("application/json")
                json {
                    type = "PointToPointIntent"
                    appId = "${pointToPointIntent.applicationId}"
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
