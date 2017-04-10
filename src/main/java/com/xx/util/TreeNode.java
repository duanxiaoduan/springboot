package com.xx.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jishu04 on 2016/5/19.
 */
public abstract class TreeNode<ID, T extends TreeNode<ID, T>> {

    abstract public ID getId();

    abstract public ID getParentId();

    abstract public List<T> getChildren();

    abstract public void setChildren(List<T> children);

    /** 是否为父节点 */
    public boolean isMyParent(T p) {
        if (p == null) {
            return false;
        }
        return p.getId() == getParentId() || (p.getId() != null && p.getId().equals(getParentId()));
    }

    /** 添加子节点 */
    public void addChild(T child) {
        if (getChildren() == null) {
            setChildren(new ArrayList<T>());
        }
        getChildren().add(child);
    }

    static public <ID, T extends TreeNode<ID, T>> Map<ID ,List<T>> groupByParentId(List<T> list) {
        Map<ID, List<T>> group = new HashMap<ID, List<T>>();
        for (T t : list) {
            List<T> childList = group.get(t.getParentId());
            if (childList == null) {
                childList = new ArrayList<T>();
                group.put(t.getParentId(), childList);
            }
            childList.add(t);
        }
        return group;
    }

    public void addChildrenFromGroup(Map<ID, List<T>> group) {
        List<T> childList = group.get(getId());
        if (childList != null) {
            setChildren(childList);
            for (T child : childList) {
                child.addChildrenFromGroup(group);
            }
        }
    }

    public void addChildrenFromList(List<T> list) {
        if (list == null) {
            return;
        }
        Map<ID, List<T>> group = groupByParentId(list);
        addChildrenFromGroup(group);
    }

}
