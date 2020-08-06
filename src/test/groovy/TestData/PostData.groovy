package TestData

class PostData {

    private final int id = 1
    private String postTitle = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"
    private String postBody = "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit" +
            " molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"

    private String editedPostTitle = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit EDITED"

    private String editedPostBody = "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit" +
            " molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto EDITED"

    int getPostId() {
        return id
    }

    String getPostTitle() {
        return postTitle
    }

    String getPostBody() {
        return postBody
    }

    String getEditedPostTitle() {
        return editedPostTitle
    }

    String getEditedPostBody() {
        return editedPostBody
    }
}
