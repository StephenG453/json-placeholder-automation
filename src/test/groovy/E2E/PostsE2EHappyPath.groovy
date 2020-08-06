package E2E

import ClientData.JavaData
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

import static groovyx.net.http.ContentType.JSON

class PostsE2EHappyPath extends Specification {

    @Shared JavaData data
    @Shared def client

    def setupSpec() {
        client = new RESTClient('https://jsonplaceholder.typicode.com/')
        data = new JavaData()
    }

    def 'user can create a new post' () {
        when:
        def response = client.post(path: 'posts',
                contentType: JSON,
                body: [userId: 1,
                       title: data.getTitle(),
                       body  : data.getBody()])
        then:
        assert response.status == 201: 'The new post was not created.'
    }

    // This currently fails cause of the \n escape character problem with the /posts API
    def 'get post' () {
        when:
        def response = client.get(path: 'posts/1')

        then:
        checkForCorrectDataInResponse(response, 200, 1, data.getTitle(), data.getBody())
    }

    def 'edit the title and body of the post'() {
        when:
        def response = client.put(path: 'posts/1',
                contentType: JSON,
                body: [userId: 1,
                       titles: data.getEditedTitle(),
                       body  : data.getEditedBody()])
        then:
        assert response.status == 200 : 'The new post was not updated.'
    }

    //this fails since the edit is actually mocked by json-placeholder
    def 'get the edited post' () {
        when:
        def response = client.get(path: 'posts/1')

        then:
        checkForCorrectDataInResponse(response, 200, 1, data.getEditedTitle(), data.getEditedBody())
    }

    //this fails since the delete is actually mocked by json-placeholder
    def 'delete the post'() {
        when:
        def response = client.delete(path: 'posts/1')

        then:
        assert response.status == 204 : 'Post was not deleted.'
    }

    //this fails since the delete is actually mocked by json-placeholder so the URL actually still exists
    def 'validate post is gone' () {
        when:
        def response = client.get(path: 'posts/1')

        then:
        assert response.status == 404 : 'Deleted post was invalidly retrieved'
    }

    void checkForCorrectDataInResponse(response, responseStatus, userID, title, body) {
        assert response.status == responseStatus : 'The expected status code is incorrect'
        assert response['data']['userId'] == userID : 'The expected userID is incorrect'
        assert response['data']['title'] == title : 'The expected title is incorrect'
        assert response['data']['body'] == body : 'The expected body is incorrect'
    }
}