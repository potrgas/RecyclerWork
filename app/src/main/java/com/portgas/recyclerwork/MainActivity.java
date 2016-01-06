package com.portgas.recyclerwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.portgas.recyclerwork.adapters.TreeAdapter;
import com.portgas.recyclerwork.model.Tree;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TreeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Tree<?>> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Tree<String> tree = new Tree<>(String.format("第%02d组", i));
            for (int j = 0; j < 5; j++) {
                Tree<String> child = new Tree<>(String.format("第%02d组 第%02d条", i, j));
                for (int k = 0; k < 10; k++) {
                    child.addSecondChildren(new Tree<>(String.format("第%02d组 第%02d条 第%02d个", i, j, k)));
                }
                tree.addFirstChildren(child);
            }
            items.add(tree);
        }

        recyclerView = (RecyclerView) findViewById(R.id.main_recycler);

        adapter = new TreeAdapter(this, items);
        recyclerView.setAdapter(adapter);


    }
}
