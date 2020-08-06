package ClientData;

public class PostData {

    private String postTitle = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
    private String postBody = "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit" +
            " molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto";

    private String editedPostTitle = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit EDITED";

    private String editedPostBody = "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit" +
            " molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto EDITED";

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public String getEditedPostTitle() {
        return editedPostTitle;
    }

    public String getEditedPostBody() {
        return editedPostBody;
    }
}
