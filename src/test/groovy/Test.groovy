import spock.lang.Specification

class Test extends Specification {

    void "first test"() {
        expect:
        true != false
    }

    void "datatable test"() {
        expect:
        Math.min(a, b) == result
        where:
        a         | b || result
        0         | 0 || 0
        100000000 | 1 || 1
    }

}
