package com.example.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.finalproject.R;

public class CategoryActivity extends AppCompatActivity {
    //GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // gridView = (GridView) findViewById(R.id.gridView);

       /* ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, numbers);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

*/
        // gridView = (GridView) findViewById(R.id.gridView);

        //  gridView.setAdapter(new ImageAdapter(this));
        //gridView.setAdapter(new ImageAda);
       /* gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                // Send intent to SingleViewActivity
                Intent i = new Intent(getApplicationContext(), CategoryActivity.class);

                // Pass image index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
    }
    }*/
    }
}


