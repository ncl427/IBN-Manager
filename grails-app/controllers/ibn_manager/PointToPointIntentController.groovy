package ibn_manager

import grails.config.Config
import grails.plugins.rest.client.RestResponse
import grails.validation.ValidationException
import groovy.ClientONOS

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

        } catch (ValidationException e) {
            respond pointToPointIntent.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pointToPointIntent.label', default: 'PointToPointIntent'), pointToPointIntent.id])
                redirect pointToPointIntent
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
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pointToPointIntent.label', default: 'PointToPointIntent'), pointToPointIntent.id])
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
        clientONOS.deleteIntent("${pointToPointIntentService.get(id).applicationId}","${pointToPointIntentService.get(id).intentKey}")

        pointToPointIntentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pointToPointIntent.label', default: 'PointToPointIntent'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pointToPointIntent.label', default: 'PointToPointIntent'), params.id])
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
