package cs371m.utrivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by julioMendez on 8/9/16.
 */
public class HowTo extends BaseMenuActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.how_to_layout);
    }

    public void toHome(View view) {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}
