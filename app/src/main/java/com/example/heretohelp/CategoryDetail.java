package com.example.heretohelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import Database.Database;
import Model.Category;
import Model.Order;

public class CategoryDetail extends AppCompatActivity {

    TextView category_name,category_description;
    ImageView menu_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnList;
//    ElegantNumberButton numberButton;

    String categoryId=" ";

    FirebaseDatabase database;
    DatabaseReference categories;

    Category currentCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        //Firebase
        database=FirebaseDatabase.getInstance();
        categories= database.getReference("Category");

        //Init View
        btnList = (FloatingActionButton)findViewById(R.id.btnList);

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Database(getBaseContext()).addToCart(new Order(
                        categoryId,
                        currentCategory.getName()
                ));
                Toast.makeText(CategoryDetail.this, "Added to To-Donate List", Toast.LENGTH_SHORT).show();
            }
        });

        category_description = (TextView)findViewById(R.id.category_description);
        category_name = (TextView)findViewById(R.id.category_name);
        menu_image = (ImageView)findViewById(R.id.img_category);

        collapsingToolbarLayout =(CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        //Get Category id from intent
        if(getIntent()!=null)
            categoryId= getIntent().getStringExtra("CategoryId");

        if(!categoryId.isEmpty()){
                getDetailCategory(categoryId);
        }

    }

    private void getDetailCategory(String categoryId) {
        categories.child(categoryId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentCategory = dataSnapshot.getValue(Category.class);
//
//
//                //Set Image
                Picasso.with(getBaseContext()).load(currentCategory.getImage())
                        .into(menu_image);
                collapsingToolbarLayout.setTitle(currentCategory.getName());
                category_name.setText(currentCategory.getName());
                category_description.setText(currentCategory.getDescription());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
