package com.chan.weava.chandroidapp.networkRetrieve;

import com.chan.weava.chandroidapp.data.ChanThread;
import com.chan.weava.chandroidapp.utils.JsonParseStrings;
import com.chan.weava.chandroidapp.utils.RequestURLStrings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Catalog Creator
 *
 * Catalog Creator retrieves a JSON Object and parses it to an array of ChanThread
 * objects.
 *
 * Uses the JsonRequest class to retrieve a list of threads, then parses that information
 * for each element of the JSON array received.
 *
 * @author Aaron Weaver         (waaronl@okstate.edu)
 * @version ForeChanApp v[Insert Version Number]
 * @since 9/22/14
 */
public class CatalogCreator implements JsonParseInterface
{
    private String mBoardName;
    private int mPageNumber;
    private ArrayList<ChanThread> threads = new ArrayList<>();

    public CatalogCreator(String boardName, int pageNumber)
    {
        this.mBoardName = boardName;
        this.mPageNumber = pageNumber;
    }

    @Override
    public void parseJsonObjectToArray() throws JSONException, InterruptedException, ExecutionException
    {
        JSONArray jsonArray;
        JsonRequest request =
                new JsonRequest(RequestURLStrings.THREADS_REQUEST_URL_BEGIN + mBoardName + "/" + mPageNumber + ".json");
        request.execute();
        jsonArray = request.get().getJSONArray(JsonParseStrings.JSON_ARRAY_PARSE_THREADS);
        JSONArray postArr = new JSONArray();
        ArrayList<JSONArray> postsList = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++)
        {
            postArr = jsonArray.getJSONObject(i).getJSONArray(JsonParseStrings.JSON_ARRAY_PARSE_POSTS);
            this.parseJsonArray(postArr);

            for (int j = 0; j < postArr.length(); j++)
            {
                //System.out.println("POSTS ARR: " + postArr.get(j));
            }
        }

        //this.parseJsonArray(jsonArray);

        for (int i = 0; i < jsonArray.length(); i++)
        {
           //System.out.println("ARRAY index-" + i + " : " + jsonArray.get(i));
        }
    }

    @Override
    public void parseJsonArray(JSONArray jsonArray) throws JSONException
    {
        for(int i = 0; i < jsonArray.length(); i++)
        {
            ChanThread newThread;
            JSONObject childJsonObject = jsonArray.getJSONObject(i);
            System.out.println("TO JSON OBJECT: " + childJsonObject.toString());
            int resto = childJsonObject.getInt(JsonParseStrings.RESTO);

            //Resto = 0 indicates OP
            if(resto == 0)
            {
                newThread = new ChanThread();
                if(childJsonObject.has(JsonParseStrings.POST_NUMBER))
                {
                    newThread.setPostNumber(childJsonObject.getInt(JsonParseStrings.POST_NUMBER));
                }
                if(childJsonObject.has(JsonParseStrings.OMITTED_POSTS))
                {
                    newThread.setOmittedReplies(childJsonObject.getInt(JsonParseStrings.OMITTED_POSTS));
                }
                if(childJsonObject.has(JsonParseStrings.OMITTED_IMAGES))
                {
                    newThread.setOmittedImages(childJsonObject.getInt(JsonParseStrings.OMITTED_IMAGES));
                }
                if(childJsonObject.has(JsonParseStrings.REPLIES))
                {
                    newThread.setNumReplies(childJsonObject.getInt(JsonParseStrings.REPLIES));
                }
                if(childJsonObject.has(JsonParseStrings.IMAGES))
                {
                    newThread.setNumImages(childJsonObject.getInt(JsonParseStrings.IMAGES));
                }
                if(childJsonObject.has(JsonParseStrings.COMMENT))
                {
                    newThread.setComment(childJsonObject.getString(JsonParseStrings.COMMENT));
                }
                if(childJsonObject.has(JsonParseStrings.NAME))
                {
                    newThread.setPosterName(childJsonObject.getString(JsonParseStrings.NAME));
                }
                if(childJsonObject.has(JsonParseStrings.SUBJECT))
                {
                    newThread.setSubject(childJsonObject.getString(JsonParseStrings.SUBJECT));
                }
                if(childJsonObject.has(JsonParseStrings.TRIPCODE))
                {
                    newThread.setPosterTripcode(childJsonObject.getString(JsonParseStrings.TRIPCODE));
                }
                if(childJsonObject.has(JsonParseStrings.RENAMED_IMAGE))
                {
                    newThread.setRenamedImageFilename(childJsonObject.getString(JsonParseStrings.RENAMED_IMAGE));
                }
                if(childJsonObject.has(JsonParseStrings.IMAGE_FILE_EXTENSION))
                {
                    newThread.setFileExtension(childJsonObject.getString(JsonParseStrings.IMAGE_FILE_EXTENSION));
                }
                if(childJsonObject.has(JsonParseStrings.IMAGE_NAME))
                {
                    newThread.setFilename(childJsonObject.getString(JsonParseStrings.IMAGE_NAME));
                }
                if(childJsonObject.has(JsonParseStrings.UNIX_TIMESTAMP))
                {
                    newThread.setUnixTimestamp(childJsonObject.getInt(JsonParseStrings.UNIX_TIMESTAMP));
                }

                threads.add(newThread);
            }
        }
    }

    public ArrayList<ChanThread> getThreads()
    {
        return this.threads;
    }
}