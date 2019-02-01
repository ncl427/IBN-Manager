package ibn_manager

import grails.config.Config
import grails.plugins.rest.client.RestResponse
import grails.validation.ValidationException
import groovy.ClientONOS

import static org.springframework.http.HttpStatus.*

class LoadBalancingIntentController {

    LoadBalancingIntentService loadBalancingIntentService

    String url
    String username
    String password

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond loadBalancingIntentService.list(params), model:[loadBalancingIntentCount: loadBalancingIntentService.count()]
    }

    def show(Long id) {
        respond loadBalancingIntentService.get(id)
    }

    def create() {
        respond new LoadBalancingIntent(params)
    }

    def save(LoadBalancingIntent loadBalancingIntent) {
        if (loadBalancingIntent == null) {
            notFound()
            return
        }

        try {

            readPropertyFile();
            ClientONOS clientONOS = new ClientONOS(url, username, password)

            RestResponse response = clientONOS.createLoadBalancingIntent(loadBalancingIntent)

            if (response.getStatus() == 200) {

                loadBalancingIntent.applicationId = "org.spm.app"
                loadBalancingIntent.priority = 60000

                loadBalancingIntentService.save(loadBalancingIntent)
            }

        } catch (ValidationException e) {
            respond loadBalancingIntent.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'loadBalancingIntent.label', default: 'LoadBalancingIntent'), loadBalancingIntent.id])
                redirect loadBalancingIntent
            }
            '*' { respond loadBalancingIntent, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond loadBalancingIntentService.get(id)
    }

    def update(LoadBalancingIntent loadBalancingIntent) {
        if (loadBalancingIntent == null) {
            notFound()
            return
        }

        try {
            loadBalancingIntentService.save(loadBalancingIntent)
        } catch (ValidationException e) {
            respond loadBalancingIntent.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'loadBalancingIntent.label', default: 'LoadBalancingIntent'), loadBalancingIntent.id])
                redirect loadBalancingIntent
            }
            '*'{ respond loadBalancingIntent, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        readPropertyFile();
        ClientONOS clientONOS = new ClientONOS(url, username, password);
        RestResponse response = clientONOS.deleteLoadBalancingIntent("${loadBalancingIntentService.get(id).applicationId}","${loadBalancingIntentService.get(id).macAddressSrc}")

        if (response.getStatus() == 204)
            loadBalancingIntentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'loadBalancingIntent.label', default: 'LoadBalancingIntent'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'loadBalancingIntent.label', default: 'LoadBalancingIntent'), params.id])
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
