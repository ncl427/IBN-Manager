package ibn_manager

class LoadBalancingIntent {

    Long id

    String deviceId
    String macAddressSrc
    Integer ingressPort
    String applicationId
    int priority

    static mapping = {
        id column: "loadBalancingIntentId", generator: "sequence"
    }

    static constraints = {

        macAddressSrc(blank: false, nullable: false)
        deviceId(blank: false, nullable: false)
        ingressPort(blank: false, nullable: false)
        applicationId(blank: false, nullable: false)
        priority(blank: false, nullable: false)
    }
}
