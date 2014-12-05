package com.chan.weava.chandroidapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chan.weava.chandroidapp.R;
import com.chan.weava.chandroidapp.cards.RepliesCard;
import com.chan.weava.chandroidapp.data.Post;
import com.chan.weava.chandroidapp.utils.BundleIdentityStrings;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Replies Fragment
 *
 * Replies Fragment contains all view relations for a specific thread's replies.
 *
 * retrieves a list of posts for a specific thread and displays them to a user in a
 * meaningful way.
 *
 * @see android.app.Fragment
 *
 * @author Aaron Weaver         (waaronl@okstate.edu)
 * @version ForeChanApp v0.1A
 * @since 11/10/14
 */
public class RepliesFragment extends Fragment
{
    private ArrayList<Post> mPostsList;

    private CardListView mRepliesGrid;
    private ArrayList<Card> mCardsList;
    private CardArrayAdapter mCardAdapter;
    private Parcelable[] posts;
    private String mBoardLink;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.replies_cards_list, container, false);

        posts = this.getArguments().getParcelableArray(BundleIdentityStrings.POSTS_IDENTIFIER);
        mBoardLink = this.getArguments().getString(BundleIdentityStrings.BOARD_LINK_IDENTIFIER);

        mPostsList = new ArrayList<>();
        for(int i = 0; i < posts.length; i++)
        {
            Post newPost = (Post) posts[i];
            newPost.setPosition(i);
            mPostsList.add(newPost);
        }

        mRepliesGrid = (CardListView) view.findViewById(R.id.replies_list);
        mCardsList = new ArrayList<Card>();

        for (int i = 0; i < mPostsList.size(); i++)
        {
            RepliesCard card = new RepliesCard(getActivity(), mPostsList.get(i), mBoardLink);
            mCardsList.add(card);
        }
        mCardAdapter = new CardArrayAdapter(getActivity(), mCardsList);
        mCardAdapter.setEnableUndo(true);
        mRepliesGrid.setAdapter(mCardAdapter);
        return view;
    }
}
