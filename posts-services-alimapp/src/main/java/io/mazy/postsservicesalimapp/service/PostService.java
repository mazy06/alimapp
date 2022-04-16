package io.mazy.postsservicesalimapp.service;


import io.mazy.postsservicesalimapp.model.Post;

import java.util.List;

public interface PostService {
    /**
     * Create new post
     * @param post
     */
    void createPost(Post post);

    /**
     * List of all post's user
     * @param appUserId
     * @return
     */
    List<Post> getAppUserPosts(Long appUserId);
}
