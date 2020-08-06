

import ClientData.PostData
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

import static groovyx.net.http.ContentType.JSON

class NegativePath extends Specification {

//    @Shared PostData data
//    @Shared def client
//
//    def setupSpec() {
//        client = new RESTClient('https://jsonplaceholder.typicode.com/')
//        data = new PostData()
//    }
//
//    def 'user can create a new post' () {
//        when:
//        def response = client.post(path: 'posts',
//                contentType: JSON,
//                body: [userId: 1,
//                       title: data.getPostTitle(),
//                       body  : data.getPostBody()])
//        then:
//        assert response.status == 201: 'The new post was not created.'
//    }
//
//    // This currently fails cause of the \n escape character problem with the /posts API
//    def 'get post' () {
//        when:
//        def response = client.get(path: 'posts/1')
//
//        then:
//        checkForCorrectDataInResponse(response, 200, 1, data.getPostTitle(), data.getPostBody())
//    }
//
//    def 'edit the title and body of the post'() {
//        when:
//        def response = client.put(path: 'posts/1',
//                contentType: JSON,
//                body: [userId: 1,
//                       titles: data.getEditedPostTitle(),
//                       body  : data.getEditedPostBody()])
//        then:
//        assert response.status == 200 : 'The new post was not updated.'
//    }
//
//    //this fails since the edit is actually mocked by json-placeholder
//    def 'get the edited post' () {
//        when:
//        def response = client.get(path: 'posts/1')
//
//        then:
//        checkForCorrectDataInResponse(response, 200, 1, data.getEditedPostTitle(), data.getEditedPostBody())
//    }
//
//    //this fails since the delete is actually mocked by json-placeholder
//    def 'delete the post'() {
//        when:
//        def response = client.delete(path: 'posts/1')
//
//        then:
//        assert response.status == 204 : 'Post was not deleted.'
//    }
//
//    //this fails since the delete is actually mocked by json-placeholder so the URL actually still exists
//    def 'validate post is gone' () {
//        when:
//        def response = client.get(path: 'posts/1')
//
//        then:
//        assert response.status == 404 : 'Deleted post was invalidly retrieved'
//    }
//
//    void checkForCorrectDataInResponse(response, responseStatus, userID, title, body) {
//        assert response.status == responseStatus : 'The expected status code is incorrect'
//        assert response['data']['userId'] == userID : 'The expected userID is incorrect'
//        assert response['data']['postTitle'] == title : 'The expected postTitle is incorrect'
//        assert response['data']['postBody'] == body : 'The expected postBody is incorrect'
//    }
}