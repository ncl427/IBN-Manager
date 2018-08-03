package ibn.manager

import grails.plugins.rest.client.RestResponse
import grails.validation.ValidationException
import groovy.ClientONOS

import static org.springframework.http.HttpStatus.*

class HostToHostIntentController {

    HostToHostIntentService hostToHostIntentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond hostToHostIntentService.list(params), model:[hostToHostIntentCount: hostToHostIntentService.count()]
    }

    def show(Long id) {
        respond hostToHostIntentService.get(id)
    }

    def create() {
        respond new HostToHostIntent(params)
    }

    def save(HostToHostIntent hostToHostIntent) {
        if (hostToHostIntent == null) {
            notFound()
            return
        }

        try {

            ClientONOS clientONOS = new ClientONOS();
            RestResponse response = clientONOS.createHostToHostIntent(hostToHostIntent);
            hostToHostIntent.intentKey = response.getHeaders().getLocation().toString().split("/")[response.getHeaders().getLocation().toString().split("/").length-1]

            hostToHostIntentService.save(hostToHostIntent)

        } catch (ValidationException e) {
            respond hostToHostIntent.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'hostToHostIntent.label', default: 'Host To Host Intent'), hostToHostIntent.id])
                redirect action:"index", method:"GET"
            }
            '*' { respond hostToHostIntent, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond hostToHostIntentService.get(id)
    }

    def update(HostToHostIntent hostToHostIntent) {
        if (hostToHostIntent == null) {
            notFound()
            return
        }

        try {
            hostToHostIntentService.save(hostToHostIntent)
        } catch (ValidationException e) {
            respond hostToHostIntent.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'hostToHostIntent.label', default: 'Host To Host Intent'), hostToHostIntent.id])
                redirect hostToHostIntent
            }
            '*'{ respond hostToHostIntent, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        ClientONOS clientONOS = new ClientONOS();
        clientONOS.deleteIntent("org.onosproject.cli","${hostToHostIntentService.get(id).intentKey}")

        hostToHostIntentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'hostToHostIntent.label', default: 'Host To Host Intent')])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'hostToHostIntent.label', default: 'Host To Host Intent'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
