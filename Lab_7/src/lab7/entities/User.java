/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lab7.analytics.DataStore;

/**
 *
 * @author harshalneelkamal
 */
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private int commentNum;
    private int postNum;
    private List<Comment> comments;

    public User(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.comments = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    public int getOverallValues(){
        
        Map<Integer, User> users = DataStore.getInstance().getUsers();
        Map<Integer, Post> posts = DataStore.getInstance().getPosts();
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();
        int likes = 0;
        int commentsNum = 0;
        int postNum = 0;
            for(Comment c : this.getComments()){                              
                if (c.getUserId() == this.id){
                    commentsNum += 1;
                    likes += c.getLikes();
                }
                
            }    
            
            for(Post p : posts.values()){

                if(p.getUserId() == this.id){
                    postNum += 1;
                }
             
            }                   

        return likes+postNum+commentsNum;
    }
    

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }
    
    @Override
    public String toString() {
        return "User{" + "id = " + id + ", firstName = " + firstName + ", number of posts = " + postNum +", number of comments = "+ commentNum+ '}';
    }
    
}
