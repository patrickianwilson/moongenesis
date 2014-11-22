package com.patrickwilson.moongenesis.core.cli

import spock.lang.Specification

/**
 * unit tests for the main CLI class.
 */
class MainLoaderSpec extends Specification {

    def "will run properly"() {

        expect:
        name.size() == length

        where:
        name     | length
        "Spock"  | 5
        "Kirk"   | 4
        "Scotty" | 6
    }

}
