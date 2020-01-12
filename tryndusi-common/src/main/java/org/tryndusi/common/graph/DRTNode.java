package org.tryndusi.common.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DRTNode<T> {

    private T data = null;
    private DRTNode<T> parent = null;
    private List<DRTNode<T>> children = null;

    public DRTNode(T data) {
        this(data, null);
    }

    public DRTNode(T data, DRTNode<T> parent) {
        this.data = data;
        this.parent = parent;
    }

    public DRTNode(T data, DRTNode<T> parent, Collection<DRTNode<T>> children) {
        this(data, parent);
        getChildren().addAll(children);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DRTNode<T> getParent() {
        return parent;
    }

    public void setParent(DRTNode<T> parent) {
        this.parent = parent;
    }

    public List<DRTNode<T>> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
        return children;
    }

    public List<T> getPathFromRoot() {
        final List<T> result = new ArrayList<>();
        result.add(data);
        DRTNode<T> ref = this;
        while (ref.parent != null) {
            result.add(ref.parent.data);
            ref = ref.getParent();
        }
        Collections.reverse(result);
        return result;
    }
}
