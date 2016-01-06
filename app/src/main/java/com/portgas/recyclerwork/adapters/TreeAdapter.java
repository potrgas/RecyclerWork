package com.portgas.recyclerwork.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.portgas.recyclerwork.R;
import com.portgas.recyclerwork.model.Tree;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/1/5 0005.
 */
public class TreeAdapter extends RecyclerView.Adapter<TreeAdapter.TreeViewHolder> implements View.OnClickListener {

    private Context context;
    private List<Tree<?>> items;

    private RecyclerView recyclerView;

    public TreeAdapter(Context context, List<Tree<?>> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public TreeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case 0:
                //根目录
                view = LayoutInflater.from(context).inflate(R.layout.root_item, parent, false);
                break;
            case 1:
                //一级目录
                view = LayoutInflater.from(context).inflate(R.layout.first_item, parent, false);
                break;
            case 2:
                //二级目录
                view = LayoutInflater.from(context).inflate(R.layout.second_item, parent, false);
                break;
        }
        view.setOnClickListener(this);

        return new TreeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TreeViewHolder holder, int position) {
        Tree<?> tree = items.get(position);
        switch (tree.getLevel()) {
            case 0:
                //根目录
                holder.rootText.setText((String) tree.getRootData());
                holder.rootExpand.setChecked(tree.isExpand());
                break;
            case 1:
                //一级目录
                holder.firstText.setText((String) (tree.getRootData()));
                holder.firstExpand.setChecked(tree.isExpand());
                break;
            case 2:
                //二级目录
                holder.secondText.setText((String) tree.getRootData());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getLevel();
    }

    @Override
    public int getItemCount() {
        int ret = 0;

        if (items != null) {
            ret = items.size();
        }

        return ret;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    public void addAll(int position, Collection<? extends Tree<?>> collection) {
        items.addAll(position, collection);
        notifyItemRangeInserted(position, collection.size());
    }

    public void removeAll(int position, Collection<? extends Tree<?>> collection) {
        items.removeAll(collection);
        notifyItemRangeRemoved(position, collection.size());
    }

    public void remove(int position, Tree<?> tree) {
        items.remove(tree);
        notifyItemRemoved(position);
    }

    @Override
    public void onClick(View v) {
        int position = recyclerView.getChildAdapterPosition(v);
        Tree<?> tree = items.get(position);
        if (tree.isExpandable()) {
            if (tree.isExpand()) {

                List<Tree<?>> firstChildren = tree.getFirstChildren();
                if (firstChildren != null) {
                    // 判断有没有二级目录，若有优先删除
                    for (int i = 0; i < firstChildren.size(); i++) {
                        Tree<?> tree1 = firstChildren.get(i);
                        if (tree1.isExpandable()) {
                            if (tree1.isExpand()) {
                                List<Tree<?>> secondChildren = tree1.getSecondChildren();
                                removeAll(position, secondChildren);
                            }
                        }
                    }

                    removeAll(position, firstChildren);


                }
                if (tree.getSecondChildren() != null) {
                    removeAll(position, tree.getSecondChildren());
                }

            } else {
                int num = 0;
                List<Tree<?>> firstChildren = tree.getFirstChildren();
                if (firstChildren != null) {

                    addAll(position + 1, firstChildren);

                    // 判断有没有二级目录，若有则添加
                    for (int i = 0; i < firstChildren.size(); i++) {
                        Tree<?> tree1 = firstChildren.get(i);
                        if (tree1.isExpandable()) {
                            if (tree1.isExpand()) {
                                List<Tree<?>> secondChildren = tree1.getSecondChildren();
                                addAll(position + i + 2 + num, secondChildren);
                                num += secondChildren.size();
                            }
                        }
                    }
                }
                if (tree.getSecondChildren() != null) {
                    addAll(position + 1, tree.getSecondChildren());
                }

            }
            tree.setExpand(!tree.isExpand());
            notifyItemChanged(position);
        } else {
//            Toast.makeText(v.getContext(), (String) tree.getData(), Toast.LENGTH_SHORT).show();

        }
    }

    public class TreeViewHolder extends RecyclerView.ViewHolder {
        private CheckBox rootExpand;
        private TextView rootText;
        private CheckBox firstExpand;
        private TextView firstText;
        private TextView secondText;


        public TreeViewHolder(View itemView) {
            super(itemView);
            rootExpand = (CheckBox) itemView.findViewById(R.id.root_check);
            rootText = (TextView) itemView.findViewById(R.id.root_text);

            firstExpand = (CheckBox) itemView.findViewById(R.id.first_check);
            firstText = (TextView) itemView.findViewById(R.id.first_text);

            secondText = (TextView) itemView.findViewById(R.id.second_text);

        }
    }
}
