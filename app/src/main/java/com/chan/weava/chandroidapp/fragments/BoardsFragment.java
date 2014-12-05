package com.chan.weava.chandroidapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chan.weava.chandroidapp.R;
import com.chan.weava.chandroidapp.cards.BoardCard;
import com.chan.weava.chandroidapp.data.Board;
import com.chan.weava.chandroidapp.utils.BundleIdentityStrings;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardGridView;

/**
 * Boards Fragment
 *
 * Boards fragment contains all view properties relating to boards
 *
 * retrieves a list of boards from the server and applies them, in a meaningful way,
 * to a list view contained in the fragment's view.
 *
 * @see android.app.Fragment
 *
 * @author Aaron Weaver         (waaronl@okstate.edu)
 * @version ForeChanApp v[Insert Version Number]
 * @since 9/19/14
 */
public class BoardsFragment extends Fragment
{
    public interface OnDataPass
    {
        public void onDataPass(int data);
    }

    public OnDataPass dataPasser;

    private CardGridView mBoardGrid;
    private ArrayList<Card> mCardsList;
    private CardArrayAdapter mCardAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //View view = inflater.inflate(R.layout.boards_fragment, container, false);
        View view = inflater.inflate(R.layout.boards_cards_grid, container, false);
        Parcelable[] boards = this.getArguments().getParcelableArray(BundleIdentityStrings.BOARDS_IDENTIFIER);
        final ArrayList<Board> boardList = new ArrayList<>();
        for(int i = 0; i < boards.length; i++)
        {
            boardList.add((Board) boards[i]);
        }

        mBoardGrid = (CardGridView) view.findViewById(R.id.boards_grid);
        mCardsList = new ArrayList<Card>();
        String boardLink = "";
        String boardTitle = "";
        boolean isWorksafe = true;
        for (int i = 0; i < boardList.size(); i++)
        {
            isWorksafe = boardList.get(i).getIsWorkSafe();
            if(isWorksafe)
            {
                BoardCard card = new BoardCard(getActivity(), boardList.get(i), i);
                card.setOnClickListener(new Card.OnCardClickListener()
                {
                    @Override
                    public void onClick(Card card, View view)
                    {
                        BoardCard boardCard = (BoardCard) card;
                        passData(boardCard.getPosition());
                    }
                });
                mCardsList.add(card);
            }
        }
        mCardAdapter = new CardArrayAdapter(getActivity(), mCardsList);
        mCardAdapter.setEnableUndo(true);
        mBoardGrid.setAdapter(mCardAdapter);

        return view;
    }

    public void passData(int data)
    {
        dataPasser.onDataPass(data);
    }

    @Override
    public void onAttach(Activity a)
    {
        super.onAttach(a);
        dataPasser = (OnDataPass) a;
    }
}
