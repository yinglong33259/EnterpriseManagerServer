package com.com.study.bst;

public class BST<Key extends Comparable<Key>,Value> {
    private class Node{
        private Key key;
        private Value value;
        private Node left,right;
        private int N;
        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }

    private Node root;

    public int size(){
        return size(root);
    }

    private int size(Node x){
        if(x==null){
            return 0;
        }else{
            return x.N;
        }
    }

    public Value get(Key key){
        return get(root,key);
    }

    public Value get(Node x, Key key){
        if(x == null ){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if(cmp<0) return get(x.left,key);
        if(cmp>0) return get(x.right,key);
        else return x.value;
    }

    public void put(Key key, Value value){
        root = put(root,key,value);
    }

    private Node put(Node x, Key key, Value val){
        if(x==null) return new Node(key,val,1);

        int cmp = key.compareTo(x.key);
        if(cmp<0) x.left = put(x.left,key,val);
        if(cmp>0) x.right = put(x.right,key,val);
        else x.value = val;

        x.N = size(x.left)+ size(x.right) + 1;
        return x;
    }

}
