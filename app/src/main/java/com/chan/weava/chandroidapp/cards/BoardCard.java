package com.chan.weava.chandroidapp.cards;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chan.weava.chandroidapp.LetterTile;
import com.chan.weava.chandroidapp.R;
import com.chan.weava.chandroidapp.data.Board;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * [Insert Class Name]
 * <p/>
 * [Insert Class Description]
 * <p/>
 * [Insert Class Details]
 * <p/>
 * <Insert Class Derivatives/Dependencies>
 * <p/>
 * <Insert Class's Associated Design Patterns>
 *
 * @author Aaron Weaver         (waaronl@okstate.edu)
 * @version ForeChanApp v0.1A
 * @since 11/17/14
 */
public class BoardCard extends Card
{
    private Board mBoard;
    private int mPosition;
    private String mBoardLink;
    private String mDisplayBoardLink;
    private Bitmap letterTile;
    private LetterTile tile;
    private int tileSize;

    public BoardCard(Context context, Board board, int position)
    {
        super(context, R.layout.board_card_layout);
        this.mBoard = board;
        this.mPosition = position;
        this.mBoardLink = board.getLinkTitle();
        this.mDisplayBoardLink = "/" + this.mBoardLink + "/";
        final Resources res = getContext().getResources();
        tileSize = res.getDimensionPixelSize(R.dimen.letter_tile_size);
        tile = new LetterTile(getContext());
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view)
    {
        super.setupInnerViewElements(parent, view);

        TextView boardLinkView = (TextView) view.findViewById(R.id.card_board_link_tile);
        TextView boardTitleView = (TextView) view.findViewById(R.id.card_board_title);
        System.out.println("BOARD CARD: " + tile.pickColor(this.mDisplayBoardLink));
        boardLinkView.setBackgroundColor(tile.pickColor(this.mBoardLink));
        boardLinkView.setText(this.mDisplayBoardLink);
        boardTitleView.setText(this.mBoard.getFullTitle());
    }

    public int getPosition()
    {
        return mPosition;
    }
}
