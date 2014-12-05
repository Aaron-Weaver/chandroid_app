package com.chan.weava.chandroidapp.networkRetrieve;

import com.chan.weava.chandroidapp.data.Post;
import com.chan.weava.chandroidapp.utils.JsonParseStrings;
import com.chan.weava.chandroidapp.utils.RequestURLStrings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Replies Creator
 *
 * Replies Creator requests a JSON object with information on replies to a thread, then
 * parses it to an array of Post objects.
 *
 * Uses JsonRequest class to retrieve a list of replies to a specific thread, then parses
 * that information for each element of the JSON Array received.
 *
 * @author Aaron Weaver         (waaronl@okstate.edu)
 * @version ForeChanApp v0.1A
 * @since 11/7/14
 */
public class RepliesCreator implements JsonParseInterface
{
    private String mThreadNum;
    private String mBoardName;
    private ArrayList<Post> posts = new ArrayList<>();

    public RepliesCreator(String boardName, String threadNum)
    {
        this.mThreadNum = threadNum;
        this.mBoardName = boardName;
    }

    @Override
    public void parseJsonObjectToArray() throws JSONException, InterruptedException, ExecutionException
    {
        JSONArray repliesJsonArray = null;
        JsonRequest request =
                new JsonRequest(RequestURLStrings.POST_REQUEST_URL_PRE
                        + this.mBoardName + "/thread/" + this.mThreadNum + ".json");
        System.out.println(RequestURLStrings.POST_REQUEST_URL_PRE +
                this.mBoardName + "/thread/" + this.mThreadNum + ".json");
        request.execute();
        repliesJsonArray = request.get().getJSONArray(JsonParseStrings.JSON_ARRAY_PARSE_POSTS);
        for(int i = 0; i < repliesJsonArray.length(); i++)
        {
            System.out.println(repliesJsonArray.get(i));
        }
        this.parseJsonArray(repliesJsonArray);
    }

    @Override
    public void parseJsonArray(JSONArray jsonArray) throws JSONException
    {
        String comment;
        String name;
        String subject;
        String tripcode;
        int postNumber;
        int omittedReplies;
        int omittedImages;
        int numReplies;
        int numImages;

        int resto;

        for(int i = 0; i < jsonArray.length(); i++)
        {
            Post newPost = new Post();
            JSONObject childJsonObject = jsonArray.getJSONObject(i);
            System.out.println("TO JSON OBJECT: " + childJsonObject.toString());
            resto = childJsonObject.getInt(JsonParseStrings.RESTO);

            if(childJsonObject.has(JsonParseStrings.POST_NUMBER))
            {
                newPost.setPostNumber(childJsonObject.getInt(JsonParseStrings.POST_NUMBER));
            }
            if(childJsonObject.has(JsonParseStrings.COMMENT))
            {
                newPost.setComment(childJsonObject.getString(JsonParseStrings.COMMENT));
            }
            if(childJsonObject.has(JsonParseStrings.NAME))
            {
                newPost.setPosterName(childJsonObject.getString(JsonParseStrings.NAME));
            }
            if(childJsonObject.has(JsonParseStrings.SUBJECT))
            {
                newPost.setSubject(childJsonObject.getString(JsonParseStrings.SUBJECT));
            }
            if(childJsonObject.has(JsonParseStrings.TRIPCODE))
            {
                newPost.setPosterTripcode(childJsonObject.getString(JsonParseStrings.TRIPCODE));
            }
            if(childJsonObject.has(JsonParseStrings.RENAMED_IMAGE))
            {
                newPost.setRenamedImageFilename(childJsonObject.getString(JsonParseStrings.RENAMED_IMAGE));
            }
            if(childJsonObject.has(JsonParseStrings.IMAGE_FILE_EXTENSION))
            {
                newPost.setFileExtension(childJsonObject.getString(JsonParseStrings.IMAGE_FILE_EXTENSION));
            }
            if(childJsonObject.has(JsonParseStrings.IMAGE_NAME))
            {
                newPost.setFilename(childJsonObject.getString(JsonParseStrings.IMAGE_NAME));
            }
            if(childJsonObject.has(JsonParseStrings.UNIX_TIMESTAMP))
            {
                newPost.setUnixTimestamp(childJsonObject.getInt(JsonParseStrings.UNIX_TIMESTAMP));
            }
            if(childJsonObject.has(JsonParseStrings.THUMB_HEIGHT))
            {
                newPost.setThumbHeight(childJsonObject.getInt(JsonParseStrings.THUMB_HEIGHT));
            }
            if(childJsonObject.has(JsonParseStrings.THUMB_WIDTH))
            {
                newPost.setThumbWidth(childJsonObject.getInt(JsonParseStrings.THUMB_WIDTH));
            }

            posts.add(newPost);

        }
    }

    public ArrayList<Post> getPosts()
    {
        return this.posts;
    }
}
