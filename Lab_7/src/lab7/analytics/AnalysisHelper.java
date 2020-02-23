/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7.analytics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lab7.entities.Comment;
import lab7.entities.Post;
import lab7.entities.User;

/**
 *
 * @author harshalneelkamal
 */
public class AnalysisHelper {
    
    // find user with Most Likes
    public void userWithMostLikes() {
       Map<Integer, Integer> userLikesCount = new HashMap<>();
       Map<Integer, User> users = DataStore.getInstance().getUsers();
       
       for(User user : users.values()){
           for(Comment c : user.getComments()){
               int likes = 0;
               if(userLikesCount.containsKey(user.getId())){
                   likes = userLikesCount.get(user.getId());
               }
               likes += c.getLikes();
               userLikesCount.put(user.getId(), likes);
               
           }
       }
       int max = 0;
       int maxId = 0;
       for(int id : userLikesCount.keySet()){
           if(userLikesCount.get(id) > max){
               max = userLikesCount.get(id);
               maxId = id;
           }
       }
       
       System.out.println("User with most likes: " + max + "\n"
            + users.get(maxId));
               
    }

    
    // find 5 comments which have the most likes
    public void getFiveMostLikedComment() {
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();
        List<Comment> commentList = new ArrayList<>(comments.values());
        
        Collections.sort(commentList, new Comparator<Comment>(){
            @Override
            public int compare(Comment o1, Comment o2){
                return o2.getLikes() - o1.getLikes();
            }
        });
        
        System.out.println();
        for(int i = 0;i < commentList.size() && i < 5;i++){
            System.out.println(commentList.get(i));
        }
        
    }
    
    //Task1
    //Find Average number of likes per comment
    public void findAverageNumOfLikesPerComment(){
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();
        List<Comment> commentList = new ArrayList<>(comments.values());
        
        //Count sum of likes and number of comment
        int likes = 0;
        int count = 0;
        for(int i = 0;i < commentList.size();i++){
            likes += commentList.get(i).getLikes();
            count++;
        }
        
        //Print out the result
        System.out.println();
        System.out.println("1. Average likes for comments is: " + (float)likes / count);
        
    }
    
    //Task2
    //Find the post with most liked comments.
    public void findPostWithMostLikedComments(){
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();
        List<Comment> commentList = new ArrayList<>(comments.values());
        
        //Sort the comment list based on number of likes, rewrite comparator.
        Collections.sort(commentList, new Comparator<Comment>(){
            @Override
            public int compare(Comment o1, Comment o2){
                return o2.getLikes() - o1.getLikes();
            }
        });
        
        //Print out the result
        System.out.println();
        System.out.println("2. Find the post with most liked comments: ");
        System.out.println("Post Id: " + commentList.get(0).getPostId() + " With comment: ");
        System.out.println(commentList.get(0));
    }
       
    //Task3
    //Find the post with most comments
    public void findPostWithMostComments(){
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();
        List<Comment> commentList = new ArrayList<>(comments.values());
        
        Map<Integer, Post> posts = DataStore.getInstance().getPosts();
        List<Post> postList = new ArrayList<>(posts.values());
        
        //Make a map of post ID and number of comments from all comments
        Map<Integer, Integer> postIdAndCommentNum = new HashMap<>();
        for(Post post : postList){
            int commentNum = 0;
            for(Comment comment : commentList){
                if(comment.getPostId() == post.getPostId())
                    commentNum++;
            }
            postIdAndCommentNum.put(post.getPostId(), commentNum);
        }
        
        //Compare and get the post Id of most comments
        int maxCommentNum = 0;
        int maxPostId = 0;
        for(int postId : postIdAndCommentNum.keySet()){
            if(postIdAndCommentNum.get(postId) > maxCommentNum){
                maxCommentNum = postIdAndCommentNum.get(postId);
                maxPostId = postId;
            }
        }
        
        //Print out the result
        System.out.println();
        System.out.println("3.Find the post with most comments:");
        System.out.println("Post Id is: " + maxPostId + " Number of comments is: " + maxCommentNum);
        
    }
    
    //Task4
    //Top 5 inactive users based on total posts number
    public void topFiveInactiveUsersByTotalPostsNum(){
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();
        List<Comment> commentList = new ArrayList<>(comments.values());
        Map<Integer, User> users = DataStore.getInstance().getUsers();
        List<User> userList = new ArrayList<>(users.values());
        Map<Integer, Post> posts = DataStore.getInstance().getPosts();
        List<Post> postList = new ArrayList<>(posts.values());
       // System.out.print(postList.size());
      
        getPostNum(postList, userList);
        getCommentNum(commentList, userList);
        Collections.sort(userList, new Comparator<User>(){
            @Override
            public int compare(User u1, User u2){
                int n = u2.getPostNum() - u1.getPostNum();
                return n*-1;
           }
        });
        
        System.out.println();
        System.out.println("4. Top 5 inactive users based on total posts number:");
        
        for(int i = 0;i < userList.size() && i < 5;i++){
           System.out.println(userList.get(i));
        }
    }
    // count post number for each user id
    public void getPostNum(List<Post> postList, List<User> userList){
        int id=0, num=0;
        for(User u : userList){
            
            id = u.getId();
            num=0;
            for(Post p : postList){
                if(id==p.getUserId()){
                    num++;
                    //Post p = new Post(c.getPostId(), c.getUserId());
                    
                }
            }
            u.setPostNum(num);
        }
        
    }

    
    //Task5
    //Top 5 inactive users based on total comments they created
    public void topFiveInactiveUsersByTotalComments(){
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();
        List<Comment> commentList = new ArrayList<>(comments.values());
        Map<Integer, User> users = DataStore.getInstance().getUsers();
        List<User> userList = new ArrayList<>(users.values());
      //  System.out.print(comments.size());
      
        Collections.sort(userList, new Comparator<User>(){
            @Override
            public int compare(User u1, User u2){
                int n = u2.getCommentNum() - u1.getCommentNum();
                return n*-1;
           }
        });
        
        System.out.println();
        System.out.println("5. Top 5 inactive users based on total comments they created:");
        for(int i = 0;i < userList.size() && i < 5;i++){
           System.out.println(userList.get(i));
        }
    }
    //count comment number for each user
    public void getCommentNum(List<Comment> commentList, List<User> userList){
        int id=0, num=0;
        for(User u : userList){
            
            id = u.getId();
            num=0;
            for(Comment c : commentList){
                if(id==c.getUserId()){
                    num++;
                    
                }
            }
            u.setCommentNum(num);
        }
        
    }
    
    public static void getPostNum(List<Comment> comPerusers) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(Comment c : comPerusers) {
            map.put(c.getUserId(), map.get(c) == null ? 1 : map.get(c) + 1);
        }
        for(int key : map.keySet()) {
            System.out.println(key + ":" + map.get(key));
        }

    }
    
    //Task6
    //Top 5 inactive users overall (sum of comments, posts and likes)
    public void topFiveInactiveUsersByOverall(){
    Map<Integer, User> user = DataStore.getInstance().getUsers();
    List<User> userList = new ArrayList<>(user.values());
        
        Collections.sort(userList, new Comparator<User>(){
            @Override
            public int compare(User o1, User o2){
                return  o1.getOverallValues()- o2.getOverallValues();
            }
        });
        
        System.out.println();
        System.out.println("6. Top 5 inactive users overall (sum of comments, posts and likes):");
        for(int i = 0;i < userList.size() && i < 5;i++){
            System.out.println(userList.get(i));
        }
        
    }
    
    //Task7
    //Top 5 proactive users overall (sum of comments, posts and likes)
    public void topFiveProactiveUsersByOverall(){
    Map<Integer, User> user = DataStore.getInstance().getUsers();
    List<User> userList = new ArrayList<>(user.values());
        
        Collections.sort(userList, new Comparator<User>(){
            @Override
            public int compare(User o1, User o2){
                return  o2.getOverallValues()- o1.getOverallValues() ;
            }
        });
        
        System.out.println();
        System.out.println("7. Top 5 proactive users overall (sum of comments, posts and likes):");
        for(int i = 0;i < userList.size() && i < 5;i++){
            System.out.println(userList.get(i));
           
        }
         
            
    }
}
