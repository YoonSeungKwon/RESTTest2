package yoon.test.rest.restapi2.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yoon.test.rest.restapi2.domain.Post;
import yoon.test.rest.restapi2.repository.BoardRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    @Transactional
    public List<Post> read(){
        List<Post> list = new ArrayList<>();
        list = boardRepository.findAll();
        if(list == null)
            return null;
        return list;
    }
    @Transactional
    public Post read(String id){
        Long uid = Long.parseLong(id);
        Post post = boardRepository.findPostById(uid);
        System.out.println(post);
        return post;
    }
    @Transactional
    public void join(Post post){
        boardRepository.save(post);
    }
    @Transactional
    public String put(String id, Post post){
        Long uid = Long.parseLong(id);
        Post tempPost = boardRepository.findPostById(uid);

        if(tempPost == null || post == null)
            return "E";
        tempPost.setTitle(post.getTitle());
        tempPost.setContent(post.getContent());
        tempPost.setWriter(post.getWriter());

        return "O";
    }

    @Transactional
    public String unlink(String id){
        Long uid = Long.parseLong(id);
        Post post = boardRepository.findPostById(uid);
        String title = post.getTitle();
        if(post == null)
            return "";
        boardRepository.delete(post);
        return title;
    }
}
