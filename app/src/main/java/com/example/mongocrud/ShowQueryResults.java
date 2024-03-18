package com.example.mongocrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.bson.Document;

import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.MongoCursor;

public class ShowQueryResults extends AppCompatActivity {
    String Appid = "mongocrud-dlnrf"; // Your App ID
    private App app;
    MongoDatabase mongoDatabase;
    MongoClient mongoClient;
    MongoCollection<Document> mongoCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_query_results);

        app = new App(new AppConfiguration.Builder(Appid).build());

        // Log in asynchronously using anonymous credentials
        app.loginAsync(Credentials.anonymous(), result -> {
            if (result.isSuccess()) {

                mongoClient = app.currentUser().getMongoClient("mongodb-atlas");
                mongoDatabase = mongoClient.getDatabase("SUKHWINDERSINGH");
                mongoCollection = mongoDatabase.getCollection("products");

                Button bt1 = findViewById(R.id.bt1);
                bt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Document queryFilter = new Document("name", "Watch pro");
                        mongoCollection.findOne(queryFilter).getAsync(task -> {
                            if (task.isSuccess()) {
                                Document doc = task.get();

                                runOnUiThread(() -> {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ShowQueryResults.this);
                                    builder.setTitle("Document Found")
                                            .setMessage(doc.toString())
                                            .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                                            .show();
                                });
                            } else {
                                runOnUiThread(() -> {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ShowQueryResults.this);
                                    builder.setTitle("Error")
                                            .setMessage("Failed to find document")
                                            .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                                            .show();
                                });
                            }
                        });
                    }
                });



/*
                Document updateFilter = new Document("category", "electronics");
                mongoCollection.find(updateFilter).iterator().getAsync(task -> {
                    if (task.isSuccess()) {
                        MongoCursor<Document> cursor = task.get();
                        while (cursor.hasNext()) {
                            Document document = cursor.next();
                            double newPrice = document.getDouble("price") * 1.10; // Increase price by 10%
                            Document updateDocument = new Document("$set", new Document("price", newPrice));
                            mongoCollection.updateOne(document, updateDocument);
                        }
                    } else {
                        // Handle error
                    }
                });


 */

            } else {
                Log.v("User", "Failed to Login");
            }
        });



        findViewById(R.id.bthome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowQueryResults.this, MainActivity.class);
                startActivity(intent);
            }
        });
/*
        findViewById(R.id.bthome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = LayoutInflater.from(ShowQueryResults.this);
                View dialogView = inflater.inflate(R.layout.docpopup, null);

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ShowQueryResults.this);
                dialogBuilder.setView(dialogView);


                EditText editTextProductName = dialogView.findViewById(R.id.editTextProductName);
                EditText editTextPrice = dialogView.findViewById(R.id.editTextPrice);
                EditText editTextCategory = dialogView.findViewById(R.id.editTextCategory);
                EditText editTextdate = dialogView.findViewById(R.id.editTextdate);
                EditText editTextManufacturer = dialogView.findViewById(R.id.editTextManufacturer);


                dialogBuilder.setPositiveButton("Insert", (dialog, which) -> {

                    String productName = editTextProductName.getText().toString();
                    String price = editTextPrice.getText().toString();
                    String category = editTextCategory.getText().toString();
                    String rdate = editTextdate.getText().toString();
                    String manufacturer = editTextManufacturer.getText().toString();



                    Document newDocument = new Document()
                            .append("name", productName)
                            .append("price", price)
                            .append("category", category)
                            .append("stock", rdate)
                            .append("manufacturer", manufacturer);


                });

                dialogBuilder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());


                AlertDialog dialog = dialogBuilder.create();
                dialog.show();

            }
        });


 */
    }
}
