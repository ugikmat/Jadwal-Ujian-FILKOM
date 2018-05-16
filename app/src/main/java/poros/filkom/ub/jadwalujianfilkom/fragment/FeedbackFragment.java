package poros.filkom.ub.jadwalujianfilkom.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import poros.filkom.ub.jadwalujianfilkom.R;
import poros.filkom.ub.jadwalujianfilkom.model.firebase.Feedback;


/**
 * A simple {@link Fragment} subclass.
 */

public class FeedbackFragment extends Fragment {

    public static String TAG= FeedbackFragment.class.getSimpleName();

    private EditText etName, etDescription;
    private Button btnSubmit;
    private ProgressBar pbFeedback;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("feedback");

    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }

    public static FeedbackFragment newInstance() {
        FeedbackFragment fragment = new FeedbackFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        View v = getView();

        etDescription = (EditText) v.findViewById(R.id.et_description);
        etName = (EditText) v.findViewById(R.id.et_name);
        btnSubmit = (Button) v.findViewById(R.id.btn_submit);
        pbFeedback = (ProgressBar) v.findViewById(R.id.pb_feedback);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFeedback submitFeedback = new submitFeedback();
                submitFeedback.execute();
            }
        });
    }

    private void enableProgressBar() {
        btnSubmit.setEnabled(false);
        pbFeedback.setVisibility(View.VISIBLE);
    }

    private void disableProgressBar() {
        btnSubmit.setEnabled(true);
        pbFeedback.setVisibility(View.GONE);
    }

    private class submitFeedback extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                    Feedback feedback = new Feedback(etName.getText().toString(), etDescription.getText().toString());
                    ref.child(ref.push().getKey()).setValue(feedback);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getActivity(), "Terimakasih sudah memberikan feedback", Toast.LENGTH_SHORT).show();
            disableProgressBar();
        }

        @Override
        protected void onPreExecute() {
            enableProgressBar();
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
