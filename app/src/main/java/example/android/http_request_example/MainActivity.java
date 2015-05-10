package example.android.http_request_example;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    String URL = "http://www.localhost/serverGreetings.php";
    TextView nameInput;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = (TextView) findViewById(R.id.requesterName);
        submit = (Button) findViewById(R.id.submitButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = (nameInput.getText()).toString();
                new asyncCall().execute(name);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class asyncCall extends AsyncTask<String, Void, String> {

        /* This method will be called before the second thread is created */
        @Override
        protected void onPreExecute() {
            /* Pre-execution message */
            Toast.makeText(getApplicationContext(), "Preparing to perform the HTTP Request.",
                    Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }

        /* This method will be called in the background,
            here is where we make the request
        */
        @Override
        protected String doInBackground(String... param) {
            httpRequestHandler httpPostObj = new httpRequestHandler(URL, param[0]);
            return httpPostObj.postData();
        }

        /* This method will be called after getting the request response */
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null && !result.isEmpty()) {
                Toast.makeText(getApplicationContext(), result,
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
