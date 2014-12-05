package com.chan.weava.chandroidapp.cards;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chan.weava.chandroidapp.R;
import com.chan.weava.chandroidapp.data.Post;
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
 * @since 11/20/14
 */
public class RepliesCard extends Card
{
    private Post mPost;
    private String mBoardLink;

    public RepliesCard(Context context, Post post, String boardLink)
    {
        super(context, R.layout.replies_card_layout);
        this.mPost = post;
        this.mBoardLink = boardLink;
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view)
    {
        LinearLayout imageInfoRow = (LinearLayout) view.findViewById(R.id.image_info_row);
        ImageView replyImage = (ImageView) view.findViewById(R.id.reply_image);
        TextView subjectText = (TextView) view.findViewById(R.id.reply_subject);
        TextView commentText = (TextView) view.findViewById(R.id.reply_comment);

        String imageUrl = RequestURLStrings.RETRIEVE_THUMBNAIL_REQUEST + mBoardLink + "/" +
                mPost.getRenamedImageFilename() + "s.jpg";

        Log.d("IMAGE URL", imageUrl);

        Picasso.with(getContext())
                .load(imageUrl)
                .resize(200, 200)
                .centerCrop()
                .into(replyImage);

        subjectText.setText(HtmlParser.parseHtml(mPost.getSubject()));
        commentText.setText(HtmlParser.parseHtml(mPost.getComment()));
    }
}
