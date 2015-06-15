package com.raizlabs.android.dbflow;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.raizlabs.android.dbflow.app.R;
import com.raizlabs.android.dbflow.config.BaseDatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.io.File;

public class MainFragment extends Fragment {

    private static final String TAG = MainFragment.class.getSimpleName();

    private Button mAddWidget;
    private Button mDestroyDatabase;
    private Button mBuildDatabase;

    private TextView mWidgetCount;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mAddWidget = (Button) view.findViewById(R.id.add_widget_button);
        mAddWidget.setOnClickListener(onAddClicked);

        mDestroyDatabase = (Button) view.findViewById(R.id.destroy_database_button);
        mDestroyDatabase.setOnClickListener(onDestroyClicked);

        mBuildDatabase = (Button) view.findViewById(R.id.build_database_button);
        mBuildDatabase.setOnClickListener(onBuildClicked);

        mWidgetCount = (TextView) view.findViewById(R.id.widget_count);
        long count = Widget.getCount();
        mWidgetCount.setText(String.valueOf(count));

        return view;
    }

    private View.OnClickListener onAddClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (!isAdded()) {
                return;
            }

            Log.d(TAG, "Creating new Widget");
            Widget widget = new Widget();
            widget.name = "asdf";
            widget.save();

            Log.d(TAG, "Updating Widget count");
            long count = Widget.getCount();
            mWidgetCount.setText(String.valueOf(count));
        }
    };

    private View.OnClickListener onDestroyClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (!isAdded()) {
                return;
            }

            Log.d(TAG, "FlowManager.destroy");
            FlowManager.destroy();
            Context context = getActivity().getApplicationContext();
            String databaseName = getDatabaseName();
            boolean deleted = context.deleteDatabase(databaseName);

            if (deleted) {
                Log.v(TAG, "Database deleted");
            } else {
                Log.v(TAG, "Database not deleted");
            }

        }
    };

    private View.OnClickListener onBuildClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (!isAdded()) {
                return;
            }

            Log.d(TAG, "FlowManager.init");
            Context context = getActivity().getApplicationContext();
            String databaseName = getDatabaseName();
            FlowManager.init(context);

            if (exists(context)) {
                Log.v(TAG, "Database exists");
            } else {
                Log.v(TAG, "Database does not exist");
            }

            try {
                BaseDatabaseDefinition definition = FlowManager.getDatabase(databaseName);
                Log.v(TAG, "Database does exist?");
            } catch (Exception exception) {
                Log.v(TAG, "Database does not exist");
            }

        }
    };

    private View.OnClickListener onRebootClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (!isAdded()) {
                return;
            }

            Log.d(TAG, "Exiting app");
            System.exit(0);

        }
    };

    @NonNull
    public static String getDatabaseName() {
        // TODO: write a test that ensures this resource exists.
        return String.format("%s.db", AppDatabase.NAME);
    }

    public static boolean exists(@NonNull Context context) {

        String databaseName = getDatabaseName();

        try {
            File dbFile = context.getDatabasePath(databaseName);
            return dbFile.exists();
        } catch (Exception exception) {
            Log.v(TAG, "Database doesn't exist.");
            return false;
        }
    }
}
