package ibn.manager

class PointToPointIntent {

    Long id
    String intentKey
    String macAddress
    String deviceId
    int ingressPort
    int egressPort
    String applicationId
    int priority

    static mapping = {
        id column: "pointToPointId", generator: "sequence"
    }

    static constraints = {
        intentKey(blank: true, nullable: true)
        deviceId(blank: false, nullable: false)
        egressPort(blank: false, nullable: false)
        ingressPort(blank: false, nullable: false)
        macAddress(blank: false, nullable: false)
        applicationId(blank: false, nullable: false)
        priority(blank: false, nullable: false)
    }
}
