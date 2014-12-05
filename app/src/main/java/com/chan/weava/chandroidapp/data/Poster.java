package com.chan.weava.chandroidapp.data;

/**
 * Poster
 *
 * This class describes the creator of a post.
 *
 * Contains information relating to the creator of any type of 4chan post.
 * Uses 4chan API.
 *
 * @author Aaron Weaver         (waaronl@okstate.edu)
 * @version ForeChanApp v0.1a
 * @since 9/10/14
 */
public abstract class Poster extends PostFile
{
    private String mPosterName;
    private String mPosterId;
    private String mPosterTripcode;

    public String getPosterName() { return this.mPosterName; }

    public void setPosterName(String posterName) { this.mPosterName = posterName; }

    public String getPosterId() { return this.mPosterId; }

    public void setPosterId(String posterId) { this.mPosterId = posterId; }

    public String getPosterTripcode() { return this.mPosterTripcode; }

    public void setPosterTripcode(String posterTripcode) { this.mPosterTripcode = posterTripcode; }
}
