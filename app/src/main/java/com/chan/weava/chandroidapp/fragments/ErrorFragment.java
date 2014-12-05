package com.chan.weava.chandroidapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chan.weava.chandroidapp.R;
import com.chan.weava.chandroidapp.utils.BundleIdentityStrings;

/**
 * Error Fragment
 *
 * Error fragment displays the UI for when an error occurs.
 *
 * @author Aaron Weaver         (waaronl@okstate.edu)
 * @version ForeChanApp v0.1A
 * @since 10/9/14
 */
public class ErrorFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.error_fragment, container, false);
        TextView errorText = (TextView) view.findViewById(R.id.error_text);
        String errorMessage = this.getArguments().getString(BundleIdentityStrings.ERROR_IDENTIFIER);
        errorText.setText(errorMessage);
        return view;
    }
}
