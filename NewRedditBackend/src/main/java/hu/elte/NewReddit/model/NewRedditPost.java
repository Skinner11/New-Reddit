/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.NewReddit.model;

/**
 *
 * @author Csalad
 */
public class NewRedditPost {

    private final String user;
    private final String postContent;
    private final long postId;

    public NewRedditPost(long postId, String user, String postContent) {
        this.user = user;
        this.postContent = postContent;
        this.postId = postId;
    }

    public String getPostContent() {
        return postContent;
    }

    public String getUser() {
        return user;
    }

    public long getPostId() {
        return postId;
    }

}
