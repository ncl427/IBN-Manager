package ibn.manager

import grails.gorm.services.Service

@Service(PointToPointIntent)
interface PointToPointIntentService {

    PointToPointIntent get(Serializable id)

    List<PointToPointIntent> list(Map args)

    Long count()

    void delete(Serializable id)

    PointToPointIntent save(PointToPointIntent pointToPointIntent)

}