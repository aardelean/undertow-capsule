package home.undertow.capsule.services;

import home.undertow.capsule.blocking.CommentDao;
import home.undertow.capsule.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by alex on 9/26/2015.
 */
@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    public Iterable<Comment> getAllComments() {
        return commentDao.findAll();
    }
}
