package ibn.manager

import grails.plugins.rest.client.RestResponse
import grails.validation.ValidationException
import groovy.ClientONOS

import grails.config.Config

import static org.springframework.http.HttpStatus.*

class PointToPointIntentController {

    PointToPointIntentService pointToPointIntentService

    String url
    String username
    String password


    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond pointToPointIntentService.list(params), model:[pointToPointIntentCount: pointToPointIntentService.count()]
    }

    def show(Long id) {
        respond pointToPointIntentService.get(id)
    }

    def create() {
        respond new PointToPointIntent(params)
    }

    def slice() {
        respond new PointToPointIntent(params)
    }

    def save(PointToPointIntent pointToPointIntent) {

        if (pointToPointIntent == null) {
            notFound()
            return
        }

        try {

            readPropertyFile();
            ClientONOS clientONOS = new ClientONOS(url, username, password)
            RestResponse response

            if (pointToPointIntent.getSliceId() == null)
                response = clientONOS.createPathComputingIntent(pointToPointIntent)
            else
                response = clientONOS.createSlicingIntent(pointToPointIntent)
            
            pointToPointIntent.intentKey = response.getHeaders().getLocation().toString().split("/")[response.getHeaders().getLocation().toString().split("/").length-1]

            pointToPointIntentService.save(pointToPointIntent)

            // code used for assigning same path for two-way traffic

//                    int tempIngressPort
//                    tempIngressPort = pointToPointIntent.getIngressPort();
//                    pointToPointIntent.setIngressPort(pointToPointIntent.getEgressPort());
//                    pointToPointIntent.setEgressPort(tempIngressPort);
//                    RestResponse response = clientONOS.createPointToPointIntent(pointToPointIntent, true);
//                    pointToPointIntent.intentKey = response.getHeaders().getLocation().toString().split("/")[response.getHeaders().getLocation().toString().split("/").length-1]
//
//                    pointToPointIntentService.save(new PointToPointIntent(pointToPointIntent.properties))


        } catch (ValidationException e) {
            respond pointToPointIntent.errors, view:'path'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pointToPointIntent.label', default: 'Point To Point Intent'), ""])
                redirect action:"index", method:"GET"
            }
            '*' { respond pointToPointIntent, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond pointToPointIntentService.get(id)
    }

    def update(PointToPointIntent pointToPointIntent) {
        if (pointToPointIntent == null) {
            notFound()
            return
        }

        try {
            pointToPointIntentService.save(pointToPointIntent)
        } catch (ValidationException e) {
            respond pointToPointIntent.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pointToPointIntent.label', default: 'Point To Point Intent'), pointToPointIntent.id])
                redirect pointToPointIntent
            }
            '*'{ respond pointToPointIntent, [status: OK] }
        }
    }

    def delete(Long id) {

        if (id == null) {
            notFound()
            return
        }

        readPropertyFile();
        ClientONOS clientONOS = new ClientONOS(url, username, password);
        clientONOS.deleteIntent("org.onosproject.cli","${pointToPointIntentService.get(id).intentKey}")

        pointToPointIntentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pointToPointIntent.label', default: ' Point To Point Intent')])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pointToPointIntent.label', default: 'Point To Point Intent'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    void readPropertyFile (){

        Config config = grailsApplication.config
        url = config.getProperty('onos.url')
        username = config.getProperty('onos.username')
        password = config.getProperty('onos.password')
    }
}
