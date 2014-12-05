package com.chan.weava.chandroidapp.networkRetrieve;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.ExecutionException;

/**
 * JSON Parse Interface
 *
 * This class will enforce other classes to implement JSON parsing methods.
 *
 * This interface will be used in conjunction with builder classes.
 *
 * @author Aaron Weaver         (waaronl@okstate.edu)
 * @version ForeChanApp v0.1A
 * @since 9/16/14
 */
public interface JsonParseInterface
{
    public void parseJsonObjectToArray() throws JSONException, InterruptedException, ExecutionException;

    public void parseJsonArray(JSONArray jsonArray) throws JSONException;
}
