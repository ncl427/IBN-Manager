package ibn.manager

import grails.gorm.services.Service

@Service(HostToHostIntent)
interface HostToHostIntentService {

    HostToHostIntent get(Serializable id)

    List<HostToHostIntent> list(Map args)

    Long count()

    void delete(Serializable id)

    HostToHostIntent save(HostToHostIntent hostToHostIntent)

}