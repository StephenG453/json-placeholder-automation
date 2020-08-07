import TestData.PostData
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static groovyx.net.http.ContentType.JSON

class NegativePath extends Specification {

    @Shared PostData data
    @Shared def client

    def setupSpec() {
        client = new RESTClient('https://jsonplaceholder.typicode.com/')
        data = new PostData()
    }

    def 'user should not be able to create a post with the same ID as a prior post'() {}

    def 'user should not be able to create a comment with the same ID as a prior comment'() {}

    //----

    def 'user should not be able to create a comment with a negative ID'() {}

    def 'user should not be able to create a post with a negative ID'() {}

    def 'user should not be able to create any entity with a negative ID'() {}

    //----

    def 'user should not be able to get data from an invalid URL'() {}

    def 'user should not be able to write data to an invalid URL'() {}

    def 'user should not be able to create a post while using an invalid JSON request format - wrong JSON keys'(String postTitle,
    String postBody) {
        expect:
        assert sendRequestWithInvalidJSONToPostAPI(postTitle, postBody) == 201 : 'The new post was invalidly created with ' +
        'a wrong format'

        where:
        postTitle           | postBody
        123                 | data.getPostBody()
        data.getPostTitle() | 123
        123                 | 123
    }

    @Unroll
    def 'user should not be able to create a post while using an invalid JSON request format - missing JSON keys'(
            HashMap<String, Object> json) {
        expect:
        assert sendRequestWithInvalidJSONToCommentsAPI(json).status == 400 : 'The invalid request was invalidly successful'

        where:
        json                          | _
        [title  : data.getPostTitle(),
         body   : data.getPostBody()] | _   //no userID - apparently the API automatically creates an ID if none if found
        [userId : data.getPostId(),
         body   : data.getPostBody()] | _    //no title
        [userId : data.getPostId(),
         title  : data.getPostTitle()]| _    //no body
    }

    def 'user should not be able to create a comment while using an invalid JSON request format - wrong JSON keys'() {}

    def 'user should not be able to create a comment while using an invalid JSON request format - missing JSON keys'() {}

    def 'user should only be able to create a post with title as a string'() {}

    def 'user should only be able to create a post with ID as an int'() {}

    def 'user should only be able to create a post with ID as an'() {}

    def sendRequestWithInvalidJSONToPostAPI(String postTitle, String postBody) {
        def response = client.post(path: 'posts',
                contentType: JSON,
                body: [userId: 1,
                       title: postTitle,
                       body  : postBody])
//        assert response.status == 201: 'The new post was not created.'
        return response
    }

    def sendRequestWithInvalidJSONToCommentsAPI(HashMap<String, Object> json) {
        def response = client.post(path: 'posts',
                contentType: JSON,
                body: json)
//                body: [userId: 1,
//                       title: postTitle,
//                       body  : postBody])
//                    )
        return response
    }

    //TODO: any authorization??

}