package com.chan.weava.chandroidapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Post
 *
 * Abstract class to apply to multiple different post data classes.
 *
 * This class will apply common functionality for multiple different posts
 * provided by the 4chan API.
 *
 * Derivatives: Thread, Reply
 *
 * @author Aaron Weaver         (waaronl@okstate.edu)
 * @version ForeChanApp v0.1a
 * @since 9/10/14
 */
public class Post extends Poster implements Parcelable
{
    private String mComment;
    private String mSubject;
    private int mPosition;
    private int mPostNumber;
    private Date mDatePosted;
    private int mUnixTimestamp;

    // Replies to the post
    private ArrayList<Integer> mReplies;

    public Post() {}

    public String getComment() { return this.mComment; }

    public void setComment(String comment) { this.mComment = comment; }

    public String getSubject() { return this.mSubject; }

    public void setSubject(String subject) { this.mSubject = subject; }

    public int getPostNumber() { return this.mPostNumber; }

    public void setPostNumber(int postNumber) { this.mPostNumber = postNumber; }

    public Date getDatePosted() { return this.mDatePosted; }

    public void setDatePosted(Date datePosted) { this.mDatePosted = datePosted; }

    public ArrayList<Integer> getReplies() { return this.mReplies; }

    public void setReplies(ArrayList<Integer> replies) { this.mReplies = replies; }

    public int getUnixTimestamp() { return this.mUnixTimestamp; }

    public void setUnixTimestamp(int unixTimestamp) { this.mUnixTimestamp = unixTimestamp; }

    public int getPosition() { return this.mPosition; }

    public void setPosition(int position) { this.mPosition = position; }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {

    }
}
