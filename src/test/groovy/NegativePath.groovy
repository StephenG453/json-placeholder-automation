import TestData.CommentData
import TestData.PostData
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static groovyx.net.http.ContentType.JSON

class NegativePath extends Specification {

    @Shared PostData postsData
    @Shared CommentData commentsData
    @Shared def client

    def setupSpec() {
        client = new RESTClient('https://jsonplaceholder.typicode.com/')
        postsData = new PostData()
        commentsData = new CommentData()
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

    @Unroll
    def 'user should not be able to create an entity while using an invalid JSON request format - wrong JSON keys' (String
            requestPath, HashMap<String, Object> requestBody) {
        expect:
        assert sendRequest(requestPath, requestBody).status == 400 : 'The invalid request was invalidly successful'
        where:
        requestPath                                    | requestBody
        '/posts'                                       | [id : "bad ID",   //assuming ID can only be int
                                                          title  : postsData.getPostTitle(),
                                                          body   : postsData.getPostBody()]

        '/posts'                                       | [id : postsData.getPostId(),
                                                          title  : 123,    //assuming title can only be String
                                                          body   : postsData.getPostBody()]

        '/posts'                                       | [id : postsData.getPostId(),
                                                          title  : postsData.getPostTitle(),
                                                          body   : 130.5f] //assuming body can only be String

        '/posts' + postsData.getPostId() + '/comments' | [postId : "bad post ID", //assuming post ID can only be int
                                                          id     : commentsData.getCommentId(),
                                                          name   : commentsData.getCommentName(),
                                                          email  : commentsData.getCommentEmail(),
                                                          body   : commentsData.getCommentBody()]

        '/posts' + postsData.getPostId() + '/comments' | [postId : commentsData.getCommentPostId(),
                                                          id     : "bad ID",    //assuming ID can only be int
                                                          name   : commentsData.getCommentName(),
                                                          email  : commentsData.getCommentEmail(),
                                                          body   : commentsData.getCommentBody()]

        '/posts' + postsData.getPostId() + '/comments' | [postId : commentsData.getCommentPostId(),
                                                          id     : commentsData.getCommentId(),
                                                          name   : 123,   //assuming name can only be String
                                                          email  : commentsData.getCommentEmail(),
                                                          body   : commentsData.getCommentBody()]

        '/posts' + postsData.getPostId() + '/comments' | [postId : commentsData.getCommentPostId(),
                                                          id     : commentsData.getCommentId(),
                                                          name   : commentsData.getCommentName(),
                                                          email  : 123.5f,  //assuming email can only be String
                                                          body   : commentsData.getCommentBody()]

        '/posts' + postsData.getPostId() + '/comments' | [postId : commentsData.getCommentPostId(),
                                                          id     : commentsData.getCommentId(),
                                                          name   : commentsData.getCommentName(),
                                                          email  : commentsData.getCommentEmail(),
                                                          body   : false]   //assuming body can only be String
    }

    @Unroll
    def 'user should not be able to create an entity while using an invalid JSON request format - missing JSON keys'(
            String requestPath, HashMap<String, Object> requestBody) {
        expect:
        assert sendRequest(requestPath, requestBody).status == 400 : 'The invalid request was invalidly successful'

        where:
        requestPath                                    | requestBody
        '/posts'                                       | [title  : postsData.getPostTitle(),
                                                          body   : postsData.getPostBody()]    //no userID - apparently the API automatically creates an ID if none if found

        '/posts'                                       | [userId : postsData.getPostId(),
                                                          body   : postsData.getPostBody()]     //no title

        '/posts'                                       | [userId : postsData.getPostId(),
                                                          title  : postsData.getPostTitle()]    //no body

        '/posts' + postsData.getPostId() + '/comments' | [postId : commentsData.getCommentPostId(),
                                                          id     : commentsData.getCommentId(),
                                                          name   : commentsData.getCommentName(),
                                                          email  : commentsData.getCommentEmail()] //no body

        '/posts' + postsData.getPostId() + '/comments' | [postId : commentsData.getCommentPostId(),
                                                          id     : commentsData.getCommentId(),
                                                          name   : commentsData.getCommentName(),
                                                          body   : commentsData.getCommentBody()]  //no email

        '/posts' + postsData.getPostId() + '/comments' | [postId : commentsData.getCommentPostId(),
                                                          id     : commentsData.getCommentId(),
                                                          email  : commentsData.getCommentEmail(),
                                                          body   : commentsData.getCommentBody()]   //no name

        '/posts' + postsData.getPostId() + '/comments' | [postId : commentsData.getCommentPostId(),
                                                          name   : commentsData.getCommentName(),
                                                          email  : commentsData.getCommentEmail(),
                                                          body   : commentsData.getCommentBody()]   //no ID

        '/posts' + postsData.getPostId() + '/comments' | [id     : commentsData.getCommentId(),
                                                          name   : commentsData.getCommentName(),
                                                          email  : commentsData.getCommentEmail(),
                                                          body   : commentsData.getCommentBody()]   //no postID
    }

    def sendRequest(String path, HashMap<String, Object> json) {
        def response = client.post(path: path,
                contentType: JSON,
                body: json)
        return response
    }

    //TODO: any authorization??

}