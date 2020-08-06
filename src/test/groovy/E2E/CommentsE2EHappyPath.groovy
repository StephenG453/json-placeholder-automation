package E2E

import ClientData.CommentData
import ClientData.PostData
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

import static groovyx.net.http.ContentType.JSON

class CommentsE2EHappyPath extends Specification {

    @Shared PostData postData
    @Shared CommentData data
    @Shared def client

    def setupSpec() {
        client = new RESTClient('https://jsonplaceholder.typicode.com/')
        postData = new PostData()
        data = new CommentData()
    }

    def 'user can create a new post' () {
        when:
        def response = client.post(path: 'posts',
                contentType: JSON,
                body: [userId: '1',
                       title: postData.getPostTitle(),
                       body  : postData.getPostBody()])
        then:
        assert response.status == 201: 'The new post was not created.'
    }

    def 'get post' () {
        when:
        def response = client.get(path: 'posts/1')

        then:
        assert response.status == 200 : 'Post was not retrieved.'
        //no need to check for data inside GET for a post since that is taken care of in PostsE2EHappyPath
    }

    def 'add a comment to the post' () {
        when:
        def response = client.post(path: 'posts/' + postData.getPostId() + '/comments',
                contentType: JSON,
                body: ['postId': data.getCommentPostId(),
                       'id': data.getCommentId(),
                       'name': data.getCommentName(),
                       'email': data.getCommentEmail(),
                       'postBody': data.getCommentBody()])

        then:
        assert response.status == 201 : 'Comment was not added.'
    }

    // This currently fails cause of the \n escape character problem for the "body" key inside the  /comments API
    def 'get comment' () {
        when:
        def response = client.get(path: 'posts/' + postData.getPostId() + '/comments')

        then:
        checkForCorrectDataInCommentResponse(response, 200, data.getCommentId(),
                data.getCommentName(), data.getCommentEmail(), data.getCommentBody())
    }

    def 'edit comment' () {
        when:
        def response = client.put(path: 'posts/' + postData.getPostId() + '/comments',
                contentType: JSON,
                body: ['postId': data.getCommentPostId(),
                       'id': data.getCommentId(),
                       'name': data.getEditedCommentName(),
                       'email': data.getEditedCommentEmail(),
                       'postBody': data.getEditedCommentBody()])
        then:
        assert response.status == 200 : 'The new post was not updated.'
    }

    def 'get edited comment' () {
        when:
        def response = client.get(path: 'posts/' + postData.getPostId() + '/comments')

        then:
        checkForCorrectDataInCommentResponse(response, 200, data.getCommentId(),
                data.getEditedCommentName(), data.getEditedCommentEmail(), data.getEditedCommentBody())
    }

    def 'delete comment' () {
        //apparently there is no func. to delete comments https://jsonplaceholder.typicode.com/posts/1/comments/1
        when:
        def response = client.delete(path: 'posts/' + postData.getPostId() + '/comments/' + data.getCommentId())

        then:
        assert response.status == 204 : 'Comment was not deleted.'
    }

    def 'validate comment is gone' () {
        when:
        def response = client.get(path: 'posts/' + postData.getPostId() + '/comments/' + data.getCommentId())

        then:
        assert response.status == 404 : 'Deleted comment was invalidly retrieved'
    }

    void checkForCorrectDataInCommentResponse(response, responseStatus, commentId, commentName, commentEmail,
                                              commentBody) {
        assert response.status == responseStatus : 'The expected status code is incorrect'
        assert response['data']['id'][data.getCommentId() - 1] == commentId : 'The expected commentID is incorrect'
        assert response['data']['name'][data.getCommentId() - 1] == commentName : 'The expected commentName is incorrect'
        assert response['data']['email'][data.getCommentId() - 1] == commentEmail : 'The expected commentEmail is incorrect'
        assert response['data']['body'][data.getCommentId() - 1] == commentBody : 'The expected commentBody is incorrect'
    }
}
