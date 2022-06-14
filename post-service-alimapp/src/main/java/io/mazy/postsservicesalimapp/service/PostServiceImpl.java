package io.mazy.postsservicesalimapp.service;

import io.mazy.postsservicesalimapp.model.Post;
import io.mazy.postsservicesalimapp.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final AppUserFeignService appUserFeignService;


    @Override
    public void createPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public List<Post> getAppUserPosts(Long appUserId) {
        if (!appUserFeignService.appUserExist(appUserId)) {
            throw new NoSuchElementException("User doesn't exist");
        }

        return postRepository.findByAppUserId(appUserId);
    }
}
