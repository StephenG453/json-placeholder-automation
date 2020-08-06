package TestData

class CommentData {

    private final int commentId = 1
    private final int commentPostId = 1

    private String commentName = "id labore ex et quam laborum"
    private String commentEmail = "Eliseo@gardner.biz"
    private String commentBody = "laudantium enim quasi est quidem magnam voluptate ipsam eos" +
            "\\ntempora quo necessitatibus\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium"

    private String editedCommentName = "id labore ex et quam laborum EDITED"
    private String editedCommentEmail = "Eliseo@gardner.biz.EDITED"
    private String editedCommentBody = "laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus" +
            "\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium EDITED"

    int getCommentId() {
        return commentId
    }

    int getCommentPostId() {
        return commentPostId
    }

    String getCommentName() {
        return commentName
    }

    String getCommentEmail() {
        return commentEmail
    }

    String getCommentBody() {
        return commentBody
    }

    String getEditedCommentName() {
        return editedCommentName
    }

    String getEditedCommentEmail() {
        return editedCommentEmail
    }

    String getEditedCommentBody() {
        return editedCommentBody
    }
}
