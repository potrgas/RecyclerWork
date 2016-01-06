package com.portgas.recyclerwork.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/5 0005.
 */
public class Tree<T> {
    private T rootData;
    private int level;
    private List<Tree<?>> firstChildren;
    private List<Tree<?>> secondChildren;
    private int currentPosition;

    private boolean expand;

    public Tree(T rootData) {
        this.rootData = rootData;
    }

    public T getRootData() {
        return rootData;
    }

    public void setRootData(T rootData) {
        this.rootData = rootData;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Tree<?>> getFirstChildren() {
        return firstChildren;
    }

    public void setFirstChildren(List<Tree<?>> firstChildren) {
        this.firstChildren = firstChildren;
    }

    public List<Tree<?>> getSecondChildren() {
        return secondChildren;
    }

    public void setSecondChildren(List<Tree<?>> secondChildren) {
        this.secondChildren = secondChildren;
    }

    public void addFirstChildren(Tree<?> child) {
        if (firstChildren == null) {
            firstChildren = new ArrayList<>();
        }
        child.setLevel(level + 1);
        firstChildren.add(child);

    }

    public void addSecondChildren(Tree<?> child) {
        if (secondChildren == null) {
            secondChildren = new ArrayList<>();
        }
        child.setLevel(level + 2);
        secondChildren.add(child);

    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    /**
     * 判断根节点是否可以展开
     *
     * @return
     */
    public boolean isExpandable() {
        return (firstChildren != null && !firstChildren.isEmpty()) || (secondChildren != null && !secondChildren.isEmpty());
    }

//    public class FirstTree<E> extends Tree<T> {
//        private E firstData;
//        private List<FirstTree<?>> children;
//
//        public FirstTree(E firstData) {
//            this.firstData = firstData;
//            super.setLevel(level + 1);
//        }
//
//        public E getFirstData() {
//            return firstData;
//        }
//
//        public void addChild(FirstTree<?> child) {
//            if (children == null) {
//                children = new ArrayList<>();
//            }
//
//            child.setLevel(level + 2);
//            children.add(child);
//        }
//
//        public boolean isExpandable() {
//            return children != null && !children.isEmpty();
//        }
//
//        public boolean isExpand() {
//            return expand;
//        }
//
//
//        public void setFirstData(E firstData) {
//            this.firstData = firstData;
//        }
//
//        public List<FirstTree<?>> getChildren() {
//            return children;
//        }
//
//        public void setChildren(List<FirstTree<?>> children) {
//            this.children = children;
//        }
//    }

}
