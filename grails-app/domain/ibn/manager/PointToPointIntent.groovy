package ibn.manager

class PointToPointIntent {

    Long id
    String intentKey
    String macAddress
    String deviceId
    Integer sliceId
    Integer ingressPort
    Integer egressPort
    String applicationId
    int priority

    static mapping = {
        id column: "pointToPointId", generator: "sequence"
    }

    static constraints = {
        sliceId(blank: true, nullable: true)
        intentKey(blank: true, nullable: true)
        deviceId(blank: false, nullable: false)
        egressPort(blank: false, nullable: false)
        ingressPort(blank: false, nullable: false)
        macAddress(blank: false, nullable: false)
        applicationId(blank: false, nullable: false)
        priority(blank: false, nullable: false)
    }
}
