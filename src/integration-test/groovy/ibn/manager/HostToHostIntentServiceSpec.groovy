package ibn.manager

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class HostToHostIntentServiceSpec extends Specification {

    HostToHostIntentService hostToHostIntentService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new HostToHostIntent(...).save(flush: true, failOnError: true)
        //new HostToHostIntent(...).save(flush: true, failOnError: true)
        //HostToHostIntent hostToHostIntent = new HostToHostIntent(...).save(flush: true, failOnError: true)
        //new HostToHostIntent(...).save(flush: true, failOnError: true)
        //new HostToHostIntent(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //hostToHostIntent.id
    }

    void "test get"() {
        setupData()

        expect:
        hostToHostIntentService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<HostToHostIntent> hostToHostIntentList = hostToHostIntentService.list(max: 2, offset: 2)

        then:
        hostToHostIntentList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        hostToHostIntentService.count() == 5
    }

    void "test delete"() {
        Long hostToHostIntentId = setupData()

        expect:
        hostToHostIntentService.count() == 5

        when:
        hostToHostIntentService.delete(hostToHostIntentId)
        sessionFactory.currentSession.flush()

        then:
        hostToHostIntentService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        HostToHostIntent hostToHostIntent = new HostToHostIntent()
        hostToHostIntentService.save(hostToHostIntent)

        then:
        hostToHostIntent.id != null
    }
}
