package com.chan.weava.chandroidapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.chan.weava.chandroidapp.data.Board;
import com.chan.weava.chandroidapp.fragments.BoardsFragment;
import com.chan.weava.chandroidapp.fragments.ErrorFragment;
import com.chan.weava.chandroidapp.networkRetrieve.BoardCreator;
import com.chan.weava.chandroidapp.utils.BundleIdentityStrings;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Main
 *
 * This class contains the base activity for the board view. It handles
 * all data manipulation for the front end UI for the board viewer.
 *
 * Using @see BoardCreator, this class retrieves a list of all the boards
 * on 4Chan. It then sends that list to a fragment to be translated into UI elements.
 *
 * @author Aaron Weaver         (waaronl@okstate.edu)
 * @version ForeChanApp v[Insert Version Number]
 * @since 9/24/14
 */
public class Main extends ActionBarActivity implements BoardsFragment.OnDataPass
{
    private ArrayList<Board> boards;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        boards = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_toolbar);
        toolbar.showOverflowMenu();
        toolbar.setTitle("Boards");
        setSupportActionBar(toolbar);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setTintColor(getResources().getColor(R.color.action_bar_color));
        tintManager.setNavigationBarTintColor(getResources().getColor(R.color.nav_bar_color));
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);

        //new JsonRequest().execute("https://a.4cdn.org/boards.json");

        try
        {
            BoardCreator createBoards = new BoardCreator();
            createBoards.parseJsonObjectToArray();
            boards = createBoards.getBoards();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (JSONException e)
        {
            e.printStackTrace();
        } catch (NullPointerException e)
        {
            System.out.println("I couldn't find any boards for you :(");
            this.bundleWithFragment(true);
        }

        StringBuilder buildString = new StringBuilder();
        for (int i = 0; i < boards.size(); i++)
        {
            buildString.append("title" + boards.get(i).getFullTitle() + "\n");
            buildString.append("link" + boards.get(i).getLinkTitle() + "\n");
        }
        System.out.println(buildString.toString());


        this.bundleWithFragment(false);
    }

    public void bundleWithFragment(boolean isError)
    {
        if(!isError)
        {
            Parcelable[] toPass = new Parcelable[boards.size()];
            for (int i = 0; i < boards.size(); i++)
            {
                toPass[i] = boards.get(i);
            }

            Bundle bundle = new Bundle();
            bundle.putParcelableArray(BundleIdentityStrings.BOARDS_IDENTIFIER, toPass);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            BoardsFragment boardsFragment = new BoardsFragment();
            boardsFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.board_list_frag, boardsFragment);
            fragmentTransaction.commit();
        }
        else
        {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString(BundleIdentityStrings.ERROR_IDENTIFIER, this.getResources().getString(R.string.boards_error_message));
            ErrorFragment errorFragment = new ErrorFragment();
            errorFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.board_list_frag, errorFragment);
            fragmentTransaction.commit();
        }
    }

    public void goToBoard(int position)
    {
        Intent i = new Intent(this, CatalogActivity.class);
        System.out.println("MOVING TO CATALOG ACTIVITY IN BOARD: /"
                + boards.get(position).getLinkTitle() + "/");
        i.putExtra(BundleIdentityStrings.BOARD_INTENT_IDENTIFIER,
                boards.get(position).getLinkTitle());
        i.putExtra(BundleIdentityStrings.BOARD_INTENT_TITLE,
                boards.get(position).getFullTitle());
        startActivity(i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDataPass(int data)
    {
        this.goToBoard(data);
    }
}