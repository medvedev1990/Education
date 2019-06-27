package kz.example.education.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import kz.example.education.R;
import kz.example.education.presentation.interfaces.IClickInterface;

public class TreesAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> data = new ArrayList<>();
    IClickInterface iClickedIterface;

    public TreesAdapter(Context context, ArrayList<String> data, IClickInterface iClickedIterface){
        this.context = context;
        this.iClickedIterface = iClickedIterface;
        this.data = data;
    }

    @Override
    public int getCount() {
        if(data != null) return data.size();
        else return 0;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v1 = convertView;
        TreeHolder treeHolder;
        if(v1 == null){
            v1 = LayoutInflater.from(context).inflate(R.layout.item_tree_listview_tree,parent,false);
            treeHolder = new TreeHolder();
            treeHolder.mTextViewTreeName = (TextView)v1.findViewById(R.id.textview_item_listview_tree);
            v1.setTag(treeHolder);
        }else{
            treeHolder = (TreeHolder)v1.getTag();
        }
        treeHolder.mTextViewTreeName.setText(data.get(position));
        treeHolder.mTextViewTreeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickedIterface.onViewClicked(position, v);
            }
        });
        return v1;
    }

    private class TreeHolder{
        TextView mTextViewTreeName;
    }
}
