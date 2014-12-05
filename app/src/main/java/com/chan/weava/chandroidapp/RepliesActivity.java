package com.chan.weava.chandroidapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.chan.weava.chandroidapp.data.Post;
import com.chan.weava.chandroidapp.fragments.RepliesFragment;
import com.chan.weava.chandroidapp.networkRetrieve.RepliesCreator;
import com.chan.weava.chandroidapp.utils.BundleIdentityStrings;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Replies Activity
 *
 * This class contains the base activity for the thread replies view. It handles
 * all data manipulation for the front end UI for the replies viewer.
 *
 * Using @see RepliesCreator, this class retrieves a list of all the replies
 * to a specific thread. It then sends that list to a fragment to be
 * translated into UI elements.
 *
 * @author Aaron Weaver         (waaronl@okstate.edu)
 * @version ForeChanApp v0.1A
 * @since 11/7/14
 */
public class RepliesActivity extends ActionBarActivity
{
    private ArrayList<Post> posts;
    private String boardLink;
    private String boardTitle;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replies);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_toolbar);
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setTintColor(getResources().getColor(R.color.action_bar_color));
        tintManager.setNavigationBarTintColor(getResources().getColor(R.color.nav_bar_color));
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);

        boardLink = this.getIntent().getStringExtra(BundleIdentityStrings.REPLIES_INTENT_IDENTIFIER_LINK);
        boardTitle = this.getIntent().getStringExtra(BundleIdentityStrings.BOARD_INTENT_TITLE);
        String threadNum = this.getIntent().getStringExtra(BundleIdentityStrings.REPLIES_INTENT_IDENTIFIER_NUM);
        System.out.println("Thread Num: " + threadNum);
        try
        {
            RepliesCreator repliesCreator = new RepliesCreator(boardLink, threadNum);
            repliesCreator.parseJsonObjectToArray();
            this.posts = repliesCreator.getPosts();
            this.bundleWithFragment();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private void bundleWithFragment()
    {
        Parcelable[] toPass = new Parcelable[posts.size()];
        for(int i = 0; i < posts.size(); i++)
        {
            toPass[i] = posts.get(i);
        }

        Bundle bundle = new Bundle();
        bundle.putParcelableArray(BundleIdentityStrings.POSTS_IDENTIFIER, toPass);
        bundle.putString(BundleIdentityStrings.BOARD_LINK_IDENTIFIER, boardLink);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RepliesFragment repliesFragment = new RepliesFragment();
        repliesFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.replies_fragment_container, repliesFragment);
        fragmentTransaction.commit();
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
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(this, CatalogActivity.class);
        setIntent.putExtra(BundleIdentityStrings.BOARD_INTENT_IDENTIFIER, boardLink);
        setIntent.putExtra(BundleIdentityStrings.BOARD_INTENT_TITLE, boardTitle);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
}
