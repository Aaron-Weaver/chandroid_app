package com.chan.weava.chandroidapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chan.weava.chandroidapp.R;
import com.chan.weava.chandroidapp.cards.CatalogCard;
import com.chan.weava.chandroidapp.data.ChanThread;
import com.chan.weava.chandroidapp.utils.BundleIdentityStrings;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardGridArrayAdapter;
import it.gmariotti.cardslib.library.view.CardGridView;

/**
 * Catalog Fragment
 *
 * Catalog Fragment contains all view relations for 4Chan's catalog
 *
 * takes a list of all threads available within a board and displays them in a
 * meaningful way for the user
 *
 * @see android.app.Fragment
 *
 * @author Aaron Weaver         (waaronl@okstate.edu)
 * @version ForeChanApp v[Insert Version Number]
 * @since 9/24/14
 */
public class CatalogFragment extends Fragment
{
    public interface OnDataPass
    {
        public void onDataPass(int position);
    }

    private ArrayList<ChanThread> threadList;
    private String boardLink;

    //private CardListView mCatalogGrid;
    //private CardRecyclerView mCatalogList;
    private ArrayList<Card> mCardsList;
    //private CardArrayAdapter mCardAdapter;
    //private CardArrayRecyclerViewAdapter mCardAdapter;

    OnDataPass dataPasser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.catalog_cards_grid, container, false);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        Parcelable[] threads = this.getArguments().getParcelableArray(BundleIdentityStrings.THREAD_IDENTIFIER);
        boardLink = this.getArguments().getString(BundleIdentityStrings.BOARD_LINK_IDENTIFIER);

        System.out.println("THREADS LENGTH: " + threads.length);
        threadList = new ArrayList<>();
        for(int i = 0; i < threads.length; i++)
        {
            threadList.add((ChanThread) threads[i]);
            threadList.get(i).setPosition(i);
            System.out.println("THREAD: " + threadList.get(i).getComment());
        }

        mCardsList = new ArrayList<Card>();

        for(ChanThread c : threadList) {
            Log.i("CARD POSITION: ", "" + c.getPosition());
            mCardsList.add(new CatalogCard(getActivity(), c, boardLink));
        }

        CardGridView mCatalogList = (CardGridView) view.findViewById(R.id.catalog_grid_view);
        CardGridArrayAdapter mCardAdapter = new CardGridArrayAdapter(getActivity(), mCardsList);
        mCardAdapter.setCardGridView(mCatalogList);
        mCardAdapter.setInnerViewTypeCount(mCardsList.size());
        //mCatalogList.setHasFixedSize(false);
        //mCardAdapter.setHasStableIds(false);
        //mCatalogList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCatalogList.setAdapter(mCardAdapter);
        //mCardAdapter.getCardRecyclerView().buildDrawingCache();
    }

    public void passData(int position)
    {
        dataPasser.onDataPass(position);
    }

    @Override
    public void onAttach(Activity a)
    {
        super.onAttach(a);
        dataPasser = (OnDataPass) a;
    }
}
