package com.pdfpreviewapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SecondActivity extends AppCompatActivity {
    PDFView pdfView;
    ProgressBar progressCircular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        pdfView = findViewById(R.id.pdfView);
        progressCircular = findViewById(R.id.progress_circular);
        progressCircular.setIndeterminate(true);
        new RetrivePDFStream().execute();
    }

    class RetrivePDFStream extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... voids) {
            try {
                InputStream input = new URL("http://www.africau.edu/images/default/sample.pdf").openStream();
                pdfView.fromStream(input).load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressCircular.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            progressCircular.setVisibility(View.GONE);
        }
    }
}