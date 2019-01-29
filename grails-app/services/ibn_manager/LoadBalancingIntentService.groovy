package ibn_manager

import grails.gorm.services.Service

@Service(LoadBalancingIntent)
interface LoadBalancingIntentService {

    LoadBalancingIntent get(Serializable id)

    List<LoadBalancingIntent> list(Map args)

    Long count()

    void delete(Serializable id)

    LoadBalancingIntent save(LoadBalancingIntent loadBalancingIntent)

}