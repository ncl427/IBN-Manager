package ibn_manager

class PointToPointIntent {

    Long id
    String intentKey
    String macAddressSrc
    String macAddressDes
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
        macAddressSrc(blank: false, nullable: false)
        macAddressDes(blank: true, nullable: true)
        applicationId(blank: false, nullable: false)
        priority(blank: false, nullable: false)
    }
}
