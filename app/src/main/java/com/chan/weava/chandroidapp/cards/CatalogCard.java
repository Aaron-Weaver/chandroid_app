package com.chan.weava.chandroidapp.cards;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chan.weava.chandroidapp.CatalogActivity;
import com.chan.weava.chandroidapp.R;
import com.chan.weava.chandroidapp.data.ChanThread;
import com.chan.weava.chandroidapp.utils.HtmlParser;
import com.chan.weava.chandroidapp.utils.RequestURLStrings;
import com.squareup.picasso.Picasso;

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
 * @since 11/19/14
 */
public class CatalogCard extends Card implements Card.OnCardClickListener
{
    private ChanThread mThread;
    private String mBoardLink;

    public CatalogCard(Context context, ChanThread thread, String boardLink)
    {
        super(context, R.layout.catalog_card_layout);
        this.mThread = thread;
        this.mBoardLink = boardLink;
        this.setOnClickListener(this);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view)
    {
        super.setupInnerViewElements(parent, view);
        System.out.println("CARD AT: " + mThread.getPosition() + " HAS BEEN CREATED");
        ImageView threadImage = (ImageView) view.findViewById(R.id.thread_image);
        TextView subjectText = (TextView) view.findViewById(R.id.subject_text);
        TextView commentText = (TextView) view.findViewById(R.id.comment_text);
        //View omitRepliesView = view.findViewById(R.id.omit_replies_view);

        String imageUrl = RequestURLStrings.RETRIEVE_THUMBNAIL_REQUEST + mBoardLink + "/" +
                mThread.getRenamedImageFilename() + "s.jpg";

        //threadImage.setImageResource(R.drawable.ic_launcher);
        Log.d("UNIX TIMESTAMP VS TIM", mThread.getUnixTimestamp() + "\n" + mThread.getRenamedImageFilename());

        Picasso.with(getContext())
                .load(imageUrl)
                .fit()
                .into(threadImage);

        subjectText.setText(HtmlParser.parseHtml(mThread.getSubject()));
        commentText.setText(HtmlParser.parseHtml(mThread.getComment()));

    }

    public int getPosition()
    {
        return this.mThread.getPosition();
    }

    @Override
    public void onClick(Card card, View view)
    {
        CatalogActivity catalogActivity = (CatalogActivity) this.getContext();
        catalogActivity.goToThread(mThread.getPosition());
    }
}
