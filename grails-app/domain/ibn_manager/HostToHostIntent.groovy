package ibn_manager

class HostToHostIntent {

    Long id
    String intentKey
    String macAddressSrc
    String macAddressDes
    String applicationId
    int priority

    static mapping = {
        id column: "hostToHostId", generator: "sequence"
    }

    static constraints = {
        intentKey(blank: true, nullable: true)
        macAddressSrc(blank: false, nullable: false)
        macAddressDes(blank: false, nullable: false)
        applicationId(blank: false, nullable: false)
        priority(blank: true, nullable: true)
    }
}
