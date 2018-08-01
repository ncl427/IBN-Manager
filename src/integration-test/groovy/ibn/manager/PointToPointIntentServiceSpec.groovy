package ibn.manager

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PointToPointIntentServiceSpec extends Specification {

    PointToPointIntentService pointToPointIntentService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new PointToPointIntent(...).save(flush: true, failOnError: true)
        //new PointToPointIntent(...).save(flush: true, failOnError: true)
        //PointToPointIntent pointToPointIntent = new PointToPointIntent(...).save(flush: true, failOnError: true)
        //new PointToPointIntent(...).save(flush: true, failOnError: true)
        //new PointToPointIntent(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //pointToPointIntent.id
    }

    void "test get"() {
        setupData()

        expect:
        pointToPointIntentService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<PointToPointIntent> pointToPointIntentList = pointToPointIntentService.list(max: 2, offset: 2)

        then:
        pointToPointIntentList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        pointToPointIntentService.count() == 5
    }

    void "test delete"() {
        Long pointToPointIntentId = setupData()

        expect:
        pointToPointIntentService.count() == 5

        when:
        pointToPointIntentService.delete(pointToPointIntentId)
        sessionFactory.currentSession.flush()

        then:
        pointToPointIntentService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        PointToPointIntent pointToPointIntent = new PointToPointIntent()
        pointToPointIntentService.save(pointToPointIntent)

        then:
        pointToPointIntent.id != null
    }
}
