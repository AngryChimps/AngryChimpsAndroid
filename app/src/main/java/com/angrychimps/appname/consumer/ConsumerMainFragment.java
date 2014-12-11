package com.angrychimps.appname.consumer;

import android.app.ListFragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.angrychimps.appname.AdFlowArrayAdapter;
import com.angrychimps.appname.AdFlowCompanyListing;
import com.angrychimps.appname.QuickReturnListView;
import com.angrychimps.appname.R;

import java.util.ArrayList;

public class ConsumerMainFragment extends ListFragment {

    private QuickReturnListView listView;
    private View header;
    private LinearLayout quickReturnView;
    private int cachedVerticalScrollRange, quickReturnHeight, scrollY, minRawY = 0, rawY;
    private static final int STATE_ONSCREEN = 0, STATE_OFFSCREEN = 1, STATE_RETURNING = 2, STATE_EXPANDED = 3;
    private int state = STATE_ONSCREEN;
    private boolean noAnimation = false;
    private TranslateAnimation anim;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        header = inflater.inflate(R.layout.quick_return_header, null);
        quickReturnView = (LinearLayout) view.findViewById(R.id.sticky);
        TextView tvSearchDetails = (TextView) view.findViewById(R.id.tvSearchDetails);
        tvSearchDetails.setText(Html.fromHtml("Showing <b>all</b> deals in <b>all</b> categories near your <b>current location</b>"));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView = (QuickReturnListView) getListView();
        listView.addHeaderView(header);

        ArrayAdapter<AdFlowCompanyListing> adapter = new AdFlowArrayAdapter(getActivity(), getCompanies());
        setListAdapter(adapter);

        //Set up the animations for the quick_return_header when the user swipes up
        listView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        quickReturnHeight = quickReturnView.getHeight();
                        listView.computeScrollY();
                        cachedVerticalScrollRange = listView.getListHeight();
                    }
                });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                scrollY = 0;
                int translationY = 0;

                if (listView.scrollYIsComputed()) {
                    scrollY = listView.getComputedScrollY();
                }

                rawY = header.getTop()
                        - Math.min(
                        cachedVerticalScrollRange
                                - listView.getHeight(), scrollY);

                switch (state) {
                    case STATE_OFFSCREEN:
                        if (rawY <= minRawY) {
                            minRawY = rawY;
                        } else {
                            state = STATE_RETURNING;
                        }
                        translationY = rawY;
                        break;

                    case STATE_ONSCREEN:
                        if (rawY < -quickReturnHeight) {
                            System.out.println("test3");
                            state = STATE_OFFSCREEN;
                            minRawY = rawY;
                        }
                        translationY = rawY;
                        break;

                    case STATE_RETURNING:

                        if (translationY > 0) {
                            translationY = 0;
                            minRawY = rawY - quickReturnHeight;
                        } else if (rawY > 0) {
                            state = STATE_ONSCREEN;
                            translationY = rawY;
                        } else if (translationY < -quickReturnHeight) {
                            state = STATE_OFFSCREEN;
                            minRawY = rawY;

                        } else if (quickReturnView.getTranslationY() != 0
                                && !noAnimation) {
                            noAnimation = true;
                            anim = new TranslateAnimation(0, 0,
                                    -quickReturnHeight, 0);
                            anim.setFillAfter(true);
                            anim.setDuration(250);
                            quickReturnView.startAnimation(anim);
                            anim.setAnimationListener(new Animation.AnimationListener() {

                                @Override
                                public void onAnimationStart(Animation animation) {
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {
                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    noAnimation = false;
                                    minRawY = rawY;
                                    state = STATE_EXPANDED;
                                }
                            });
                        }
                        break;

                    case STATE_EXPANDED:
                        if (rawY < minRawY - 2 && !noAnimation) {
                            noAnimation = true;
                            anim = new TranslateAnimation(0, 0, 0,-quickReturnHeight);
                            anim.setFillAfter(true);
                            anim.setDuration(250);
                            anim.setAnimationListener(new Animation.AnimationListener() {

                                @Override
                                public void onAnimationStart(Animation animation) {
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    noAnimation = false;
                                    state = STATE_OFFSCREEN;
                                }
                            });
                            quickReturnView.startAnimation(anim);
                        } else if (translationY > 0) {
                            translationY = 0;
                            minRawY = rawY - quickReturnHeight;
                        } else if (rawY > 0) {
                            state = STATE_ONSCREEN;
                            translationY = rawY;
                        } else if (translationY < -quickReturnHeight) {
                            state = STATE_OFFSCREEN;
                            minRawY = rawY;
                        } else {
                            minRawY = rawY;
                        }
                }
                quickReturnView.setTranslationY(translationY);
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
        });
    }


        // Called every time the screen orientation changes or Android kills an Activity to conserve resources
        // We save the last item selected in the list here and attach it to the key curChoice
        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);

        }

        // TODO add onClick functionality
        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
        }

    private ArrayList<AdFlowCompanyListing> getCompanies() {
        ArrayList<AdFlowCompanyListing> list = new ArrayList<AdFlowCompanyListing>();
        list.add(new AdFlowCompanyListing(null, "We cut your hair", "Hair Company \n" +
                "123 Main St \nSan Francisco, CA 94110", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "We're the best!", "Haircut Express \n" +
                "123 Harrison St \nSan Francisco, CA 94112", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "Best deals in town", "Cheap Hairdos \n" +
                "123 Folsom St \nSan Francisco, CA 94114", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "Half off hair!", "Barber Man \n" +
                "123 12th St \nSan Francisco, CA 94112", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "We cut your hair", "Hair Company \n" +
                "123 Main St \nSan Francisco, CA 94110", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "We're the best!", "Haircut Express \n" +
                "123 Harrison St \nSan Francisco, CA 94112", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "Best deals in town", "Cheap Hairdos \n" +
                "123 Folsom St \nSan Francisco, CA 94114", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "Half off hair!", "Barber Man \n" +
                "123 12th St \nSan Francisco, CA 94112", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "We cut your hair", "Hair Company \n" +
                "123 Main St \nSan Francisco, CA 94110", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "We're the best!", "Haircut Express \n" +
                "123 Harrison St \nSan Francisco, CA 94112", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "Best deals in town", "Cheap Hairdos \n" +
                "123 Folsom St \nSan Francisco, CA 94114", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "Half off hair!", "Barber Man \n" +
                "123 12th St \nSan Francisco, CA 94112", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "We cut your hair", "Hair Company \n" +
                "123 Main St \nSan Francisco, CA 94110", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "We're the best!", "Haircut Express \n" +
                "123 Harrison St \nSan Francisco, CA 94112", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "Best deals in town", "Cheap Hairdos \n" +
                "123 Folsom St \nSan Francisco, CA 94114", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "Half off hair!", "Barber Man \n" +
                "123 12th St \nSan Francisco, CA 94112", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "We cut your hair", "Hair Company \n" +
                "123 Main St \nSan Francisco, CA 94110", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "We're the best!", "Haircut Express \n" +
                "123 Harrison St \nSan Francisco, CA 94112", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "Best deals in town", "Cheap Hairdos \n" +
                "123 Folsom St \nSan Francisco, CA 94114", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "Half off hair!", "Barber Man \n" +
                "123 12th St \nSan Francisco, CA 94112", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "We cut your hair", "Hair Company \n" +
                "123 Main St \nSan Francisco, CA 94110", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "We're the best!", "Haircut Express \n" +
                "123 Harrison St \nSan Francisco, CA 94112", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "Best deals in town", "Cheap Hairdos \n" +
                "123 Folsom St \nSan Francisco, CA 94114", "Tomorrow 9:30-12:00pm"));
        list.add(new AdFlowCompanyListing(null, "Half off hair!", "Barber Man \n" +
                "123 12th St \nSan Francisco, CA 94112", "Tomorrow 9:30-12:00pm"));
        return list;
    }
}
