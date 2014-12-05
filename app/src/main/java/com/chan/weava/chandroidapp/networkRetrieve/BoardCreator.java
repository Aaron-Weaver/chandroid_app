package com.chan.weava.chandroidapp.networkRetrieve;

import com.chan.weava.chandroidapp.data.Board;
import com.chan.weava.chandroidapp.utils.JsonParseStrings;
import com.chan.weava.chandroidapp.utils.RequestURLStrings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * BoardCreator
 *
 * Board Creator retrieves a JSON object and parses that data to be stored as Board objects.
 *
 * This class will parse a JSON object created specifically for Board objects.
 *
 * This class implements JsonParseInterface.
 *
 * >TBA
 *
 * @author Aaron Weaver         (waaronl@okstate.edu)
 * @version ForeChanApp v0.1A
 * @since 9/16/14
 */
public class BoardCreator implements JsonParseInterface
{
    private ArrayList<Board> boards = new ArrayList<>();

    @Override
    public void parseJsonObjectToArray() throws ExecutionException, InterruptedException, JSONException
    {
        JSONArray jsonArray = null;
        JsonRequest request = new JsonRequest(RequestURLStrings.BOARDS_REQUEST_URL);
        request.execute();
        jsonArray = request.get().getJSONArray(JsonParseStrings.JSON_ARRAY_PARSE_BOARDS);

        this.parseJsonArray(jsonArray);

        for (int i = 0; i < jsonArray.length(); i++)
        {
            System.out.println("ARRAY index-" + i + " : " + jsonArray.get(i));
        }
    }

    @Override
    public void parseJsonArray(JSONArray jsonArray) throws JSONException
    {
        String linkTitle;
        String fullTitle;
        boolean isWorksafe;

        for(int i = 0; i < jsonArray.length(); i++)
        {
            Board newBoard = new Board();
            JSONObject childJsonObject = jsonArray.getJSONObject(i);
            linkTitle = childJsonObject.getString(JsonParseStrings.LINK);
            newBoard.setLinkTitle(linkTitle);
            fullTitle = childJsonObject.getString(JsonParseStrings.TITLE);
            newBoard.setFullTitle(fullTitle);
            int worksafe = childJsonObject.getInt(JsonParseStrings.WORKSAFE);
            if(worksafe == 1)
            {
                isWorksafe = true;
            }
            else
            {
                isWorksafe = false;
            }
            newBoard.setIsWorkSafe(isWorksafe);
            boards.add(newBoard);
        }
    }

    public ArrayList<Board> getBoards()
    {
        return this.boards;
    }
}
