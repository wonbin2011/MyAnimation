package com.example.wonbin.myanimation.fragment;

import android.app.ActivityOptions;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wonbin.myanimation.activities.CircularRevealActivity;
import com.example.wonbin.myanimation.activities.ExplodeAnimationActivity;
import com.example.wonbin.myanimation.activities.FadeAnimationActivity;
import com.example.wonbin.myanimation.activities.SharedElementAnimationActivity;
import com.example.wonbin.myanimation.activities.SlideAnimationAvtivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wonbin on 2015/4/15.
 */
public class SelectionListFragment extends ListFragment{
    private final String CATEGORY_CIRCULAR_REVEAL = "Circular Reveal";
    private final String CATEGORY_ACTIVITY_TRANSITION_EXPLODE = "Activity Transition: Explode";
    private final String CATEGORY_ACTIVITY_TRANSITION_SLIDE = "Activity Transition: Slide";
    private final String CATEGORY_ACTIVITY_TRANSITION_FADE = "Activity Transition: Fade";
    private final String CATEGORY_SHARED_ELEMENT_TRANSITION_CHANGE_BOUNDS = "Shared Element Transition: Change Bounds";

    private ArrayAdapter<String> mAdapter;

    public static SelectionListFragment getInstance() {
        SelectionListFragment fragment = new SelectionListFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,getCategories());
        setListAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String text = ((TextView) v).getText().toString();
        Intent intent = null;
        if(CATEGORY_ACTIVITY_TRANSITION_EXPLODE.equalsIgnoreCase(text)) {
            getActivity().getWindow().setExitTransition(new Explode());
            intent = new Intent(getActivity(), ExplodeAnimationActivity.class);
        } else if(CATEGORY_ACTIVITY_TRANSITION_FADE.equalsIgnoreCase(text)){
            getActivity().getWindow().setExitTransition(new Fade());
            intent = new Intent(getActivity(), FadeAnimationActivity.class);
        } else if(CATEGORY_ACTIVITY_TRANSITION_SLIDE.equalsIgnoreCase(text)) {
            getActivity().getWindow().setExitTransition(new Slide());
            intent = new Intent(getActivity(),SlideAnimationAvtivity.class);
        } else if(CATEGORY_CIRCULAR_REVEAL.equalsIgnoreCase(text)) {
            intent = new Intent(getActivity(), CircularRevealActivity.class);
        } else if(CATEGORY_SHARED_ELEMENT_TRANSITION_CHANGE_BOUNDS.equalsIgnoreCase(text)) {
            intent = new Intent(getActivity(), SharedElementAnimationActivity.class);
        }

        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
    }

    private List<String> getCategories() {
        List<String> categories = new ArrayList<String>();

        categories.add(CATEGORY_ACTIVITY_TRANSITION_EXPLODE);
        categories.add(CATEGORY_ACTIVITY_TRANSITION_FADE);
        categories.add(CATEGORY_ACTIVITY_TRANSITION_SLIDE);
        categories.add(CATEGORY_CIRCULAR_REVEAL);
        categories.add(CATEGORY_SHARED_ELEMENT_TRANSITION_CHANGE_BOUNDS);
        return categories;
    }
}
