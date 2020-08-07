import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

import static groovyx.net.http.ContentType.JSON

class NegativePath extends Specification {

    def 'user should not be able to create a post with the same ID as a prior post'() {}

    def 'user should not be able to create a comment with the same ID as a prior comment'() {}

    def 'user should not be able to create a comment with a negative ID'() {}

    def 'user should not be able to create a post with a negative ID'() {}

    def 'user should not be able to get data from an invalid URL'() {}

    def 'user should not be able to write data to an invalid URL'() {}

    def 'user should not be able to create a post while using an invalid JSON request format - wrong JSON keys'() {}

    def 'user should not be able to create a comment while using an invalid JSON request format - wrong JSON keys'() {}

    def 'user should not be able to create a post while using an invalid JSON request format - missing JSON keys'() {}

    def 'user should not be able to create a comment while using an invalid JSON request format - missing JSON keys'() {}

    def 'user should only be able to create a post with title as a string'() {}

    def 'user should only be able to create a post with ID as an int'() {}

    def 'user should only be able to create a post with ID as an'() {}

    //TODO: any authorization??

}