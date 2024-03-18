package com.example.mongocrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.MongoCursor;

public class MainActivity extends AppCompatActivity {
    String Appid = "mongocrud-dlnrf";
    private App app;
    MongoDatabase mongoDatabase;
    MongoClient mongoClient;
    public MongoCollection<Document> mongoCollection;
    private RecyclerView productsRecyclerView;
    private ProductAdapter productsAdapter;
    private List<Product> productList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Realm.init(this);

        app = new App(new AppConfiguration.Builder(Appid).build());

        productsRecyclerView = findViewById(R.id.productsRecyclerView);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productsAdapter = new ProductAdapter(productList);
        productsRecyclerView.setAdapter(productsAdapter);

        app.loginAsync(Credentials.anonymous(), result -> {
            if (result.isSuccess()) {
                Log.v("User", "Logged In Successfully");


                mongoClient = app.currentUser().getMongoClient("mongodb-atlas");
                mongoDatabase = mongoClient.getDatabase("SUKHWINDERSINGH");
                mongoCollection = mongoDatabase.getCollection("products");


                mongoCollection.find().iterator().getAsync(task -> {
                    if (task.isSuccess()) {
                        MongoCursor<Document> results = task.get();
                        // Log.v("Collections", "Pulled Data");
                        while (results.hasNext()) {
                            Document document = results.next();

                            String name = document.getString("name");
                            String category = document.getString("category");
                            Integer price = document.getInteger("price");
                            String manufacturer = document.getString("manufacturer");
                            String releaseDate = document.getString("releaseDate");

                            Product product = new Product(name, category, price, manufacturer, releaseDate);
                            productList.add(product);

                        }

                        runOnUiThread(() -> productsAdapter.notifyDataSetChanged());

                    } else {
                        Log.e("Collection Data", "Failed to Fetch Data", task.getError());
                    }
                });
            } else {
                Log.v("User", "Failed to Login");
            }
        });



        findViewById(R.id.qbt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowQueryResults.class);
                startActivity(intent);
            }
        });

        }
}