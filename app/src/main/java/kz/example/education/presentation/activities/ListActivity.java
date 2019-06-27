package kz.example.education.presentation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import kz.example.education.R;
import kz.example.education.presentation.adapters.TreesAdapter;
import kz.example.education.presentation.contract.ListActivityContract;
import kz.example.education.presentation.interfaces.IClickInterface;

public class ListActivity extends AppCompatActivity implements ListActivityContract.View, IClickInterface{

    ListView mListViewTrees;

    ArrayList<String> mArrayListTrees = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initializeViews();
        populateList();
        initializeAdapter();
    }

    @Override
    public void initializeViews() {
        mListViewTrees = (ListView)findViewById(R.id.listview_activity_list_trees);
    }

    @Override
    public void initializeListeners() {

    }

    @Override
    public void populateList() {
        mArrayListTrees.add("Береза");
        mArrayListTrees.add("Дуб");
        mArrayListTrees.add("Сосна");
        mArrayListTrees.add("Яблоня");
        mArrayListTrees.add("Груша");
    }

    @Override
    public void initializeAdapter() {
        TreesAdapter treesAdapter = new TreesAdapter(this, mArrayListTrees, this);
        mListViewTrees.setAdapter(treesAdapter);
    }

    @Override
    public void onViewClicked(int position, View v) {
        switch (v.getId()){
            case R.id.textview_item_listview_tree:
                Toast.makeText(this, mArrayListTrees.get(position), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
