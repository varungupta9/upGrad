package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
//Comment Controller Class
@Controller //
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;
    //This controller method is called when the request pattern is of type '/image/{imageId}/{imageTitle}/comments' and also the incoming request is of POST type
    //set the comments from the user of the image by getting the logged in user from the Http Session
    //set the current date
    //insert the comment by calling the insertComment of commentService
    //redirects to images.html with imageid and title
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String title, @RequestParam("comment") String text, HttpSession session) {
        User user = (User) session.getAttribute("loggeduser");
        Comment comment = new Comment();
        comment.setImage(imageService.getImage(imageId));
        comment.setText(text);
        comment.setUser(user);
        comment.setCreatedDate(new Date());
        commentService.insertComment(comment);
        return "redirect:/images/" + imageId + "/" + title;

    }
}
