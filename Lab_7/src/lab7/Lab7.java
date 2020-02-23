/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7;

import java.io.IOException;
import java.util.Map;
import lab7.analytics.AnalysisHelper;
import lab7.analytics.DataStore;
import lab7.entities.Comment;
import lab7.entities.Post;
import lab7.entities.User;

/**
 *
 * @author harshalneelkamal
 */
public class Lab7 {

    DataReader commentReader;
    DataReader userReader;
    AnalysisHelper helper;
    
    private final String USER_CAT_PATH = "./UserCatalogue.csv";
    private final String COMMENT_FILE_PATH = "./CommentData.csv";
    
    public Lab7() throws IOException {
        //DataGenerator generator = DataGenerator.getInstance();
        //commentReader = new DataReader(generator.getCommentFilePath());
        //userReader = new DataReader(generator.getUserCataloguePath());
        commentReader = new DataReader(COMMENT_FILE_PATH);
        userReader = new DataReader(USER_CAT_PATH);
        helper = new AnalysisHelper();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {     
        Lab7 inst = new Lab7();
        inst.readData();
    }
    
    private void readData() throws IOException{
        String[] row;
        while((row = userReader.getNextRow()) != null ){
            generateUser(row);
        }
        while((row = commentReader.getNextRow()) != null ){
            Comment comment = generateComment(row);
            generatePost(row, comment);
        }
        runAnalysis();
    }
    
    private void generateUser(String[] row){
        // TODO
        User u = new User(Integer.parseInt(row[0]), row[1], row[3]);
        DataStore.getInstance().getUsers().put(Integer.parseInt(row[0]), u);
        
    }
    
    private Comment generateComment(String[] row){
        // TODO
        int commentId = Integer.parseInt(row[0]);
        int commentingUserId = Integer.parseInt(row[4]);
        int likes = Integer.parseInt(row[3]);
        int postId = Integer.parseInt(row[1]);
        String comment = row[5];
        
        Comment c = new Comment(commentId, commentingUserId, postId, comment, likes);
        DataStore.getInstance().getComments().put(commentId, c);
        
        Map<Integer, User> users = DataStore.getInstance().getUsers();
        if(users.containsKey(commentingUserId)){
            users.get(commentingUserId).getComments().add(c);
        }
        
        return c;
    }
    
    private void generatePost(String[] row, Comment comment){
        // TODO
        int postId = Integer.parseInt(row[1]);
        int userId = Integer.parseInt(row[2]);
        //DataStore.getInstance().getPosts().put(Integer.parseInt(row[1]), p);
        Map<Integer, Post> posts = DataStore.getInstance().getPosts();
        
        if(posts.containsKey(postId)){
            posts.get(postId).getComments().add(comment);
        }
        else{
            Post post = new Post(postId, userId);
            post.getComments().add(comment);
            posts.put(postId, post);
        }
    }
    
        
    private void runAnalysis(){
        //Get result of examples from Lab
        //helper.userWithMostLikes();
        //helper.getFiveMostLikedComment();
        
        //Get result of 7 tasks of Lab7
        helper.findAverageNumOfLikesPerComment();
        helper.findPostWithMostLikedComments();
        helper.findPostWithMostComments();
        helper.topFiveInactiveUsersByTotalPostsNum();
        helper.topFiveInactiveUsersByTotalComments();
        helper.topFiveInactiveUsersByOverall();
        helper.topFiveProactiveUsersByOverall();
        
    }
}
