package ibn_manager

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class LoadBalancingIntentServiceSpec extends Specification {

    LoadBalancingIntentService loadBalancingIntentService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new LoadBalancingIntent(...).save(flush: true, failOnError: true)
        //new LoadBalancingIntent(...).save(flush: true, failOnError: true)
        //LoadBalancingIntent loadBalancingIntent = new LoadBalancingIntent(...).save(flush: true, failOnError: true)
        //new LoadBalancingIntent(...).save(flush: true, failOnError: true)
        //new LoadBalancingIntent(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //loadBalancingIntent.id
    }

    void "test get"() {
        setupData()

        expect:
        loadBalancingIntentService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<LoadBalancingIntent> loadBalancingIntentList = loadBalancingIntentService.list(max: 2, offset: 2)

        then:
        loadBalancingIntentList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        loadBalancingIntentService.count() == 5
    }

    void "test delete"() {
        Long loadBalancingIntentId = setupData()

        expect:
        loadBalancingIntentService.count() == 5

        when:
        loadBalancingIntentService.delete(loadBalancingIntentId)
        sessionFactory.currentSession.flush()

        then:
        loadBalancingIntentService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        LoadBalancingIntent loadBalancingIntent = new LoadBalancingIntent()
        loadBalancingIntentService.save(loadBalancingIntent)

        then:
        loadBalancingIntent.id != null
    }
}
