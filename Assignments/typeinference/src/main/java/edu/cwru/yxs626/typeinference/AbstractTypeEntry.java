package edu.cwru.yxs626.typeinference;

public abstract class AbstractTypeEntry implements TypeEntry{

    @Override
    public boolean isVariable() {
        return false;
    }
}