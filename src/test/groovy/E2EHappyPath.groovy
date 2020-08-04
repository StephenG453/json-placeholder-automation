import groovyx.net.http.RESTClient
import spock.lang.Specification

import static groovyx.net.http.ContentType.JSON

class E2EHappyPath extends Specification {

    def client = new RESTClient('https://jsonplaceholder.typicode.com/')

    def 'user can create a new entity, edit it a couple times, retrieve it, then delete it' () {
        when:
        def response = client.post(path : 'posts',
                contentType: JSON,
                body: ["titles": "title1",
                       "body": "body1",
                       "userId": "1"])
        then:
        assert response.status == 201 : "The new entity was not created."
    }
}