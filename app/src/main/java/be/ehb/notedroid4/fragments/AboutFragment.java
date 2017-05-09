package be.ehb.notedroid4.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import be.ehb.notedroid4.R;

/**
 * Created by MVH on 27-4-2017.
 */

public class AboutFragment extends Fragment {

    private TextView tvAbout;

    public AboutFragment() {
    }

    public static AboutFragment newInstance(){
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.about_fragment, container, false);

        tvAbout = (TextView) rootView.findViewById(R.id.tv_about_str);
        tvAbout.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut vitae lorem neque. Aenean ligula nisi, congue quis fringilla eu, vestibulum at ligula. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel vulputate nisi. Aliquam neque nisl, dapibus sed ullamcorper ac, sagittis ut quam. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Mauris vel mattis urna, vitae fringilla eros. Mauris sed efficitur velit. Aliquam eu cursus orci. Ut at urna in justo elementum feugiat. Maecenas aliquam mattis justo at suscipit. Curabitur neque dui, feugiat a egestas eget, dictum viverra sapien.");

        return rootView;
    }
}
