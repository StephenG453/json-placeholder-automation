package ClientData;

public class CommentData {

    private final int commentId = 1;
    private final int commentPostId = 1;

    private String commentName = "id labore ex et quam laborum";
    private String commentEmail = "Eliseo@gardner.biz";
    private String commentBody = "laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus" +
            "\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium";

    private String editedCommentName = "id labore ex et quam laborum EDITED";
    private String editedCommentEmail = "Eliseo@gardner.biz.EDITED";
    private String editedCommentBody = "laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus" +
            "\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium EDITED";

    public int getCommentId() {
        return commentId;
    }

    public int getCommentPostId() {
        return commentPostId;
    }

    public String getCommentName() {
        return commentName;
    }

    public String getCommentEmail() {
        return commentEmail;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public String getEditedCommentName() {
        return editedCommentName;
    }

    public String getEditedCommentEmail() {
        return editedCommentEmail;
    }

    public String getEditedCommentBody() {
        return editedCommentBody;
    }
}
