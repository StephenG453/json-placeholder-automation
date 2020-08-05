import groovyx.net.http.RESTClient
import spock.lang.Specification

import static groovyx.net.http.ContentType.JSON

class CommentsE2EHappyPath extends Specification {

    def client = new RESTClient('https://jsonplaceholder.typicode.com/')

    def 'user can create a new post' () {
        when:
        def response = client.post(path: 'posts',
                contentType: JSON,
                body: [userId: '1',
                       titles: 'title1',
                       body  : 'body1'])
        then:
        assert response.status == 201: 'The new post was not created.'
    }

    def 'get post' () {
        when:
        def response = client.get(path: 'posts/1')
//        def response = getData()

        then:
        assert response.status == 200 : 'Post was not retrieved.'
    }

    def 'add a comment to the post' () {
        when:
        def response = client.post(path: 'posts/1/comments',
                contentType: JSON,
                bidy: ['postId': 1,
                       'id': 1,
                       'name': "meaningful name",
                       'email': "Eliseo@gardner.biz",
                       'body': "meaningful body"])

        then:
        assert response.status == 201 : 'Comment was not added.'
    }

    def 'get comment' () {
        when:
        def response = client.get(path: 'posts/1/comments')

        then:
        assert response.status == 200 : 'Comment was not retrieved.'
    }

    def 'edit comment' () {
        when:
        def response = client.put(path: 'posts/1/comments',
                contentType: JSON,
                bidy: ['postId': 1,
                       'id': 1,
                       'name': "another meaningful name",
                       'email': "Eliseo@gardner.biz",
                       'body': "another meaningful body"])
        then:
        assert response.status == 200 : 'The new post was not updated.'
    }

    def 'get edited comment' () {
        when:
        def response = client.get(path: 'posts/1/comments')

        then:
        assert response.status == 200 : 'Comment was not retrieved.'
    }

    def 'delete comment' () {
        //apparently there is no func. to delete comments https://jsonplaceholder.typicode.com/posts/1/comments/1
        when:
        def response = client.delete(path: 'posts/1/comments/1')

        then:
        assert response.status == 204 : 'Comment was not deleted.'
    }

    def 'validate comment is gone' () {
        when:
//        def response = getData()
        def response = client.get(path: 'posts/1/comments/1')

        then:
        assert response.status == 404 : 'Deleted post was invalidly retrieved'
    }

//    def getData() {
//        def response = client.get(path: 'posts/1')
//        return response
//    }

}
