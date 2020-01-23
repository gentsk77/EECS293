package edu.cwru.yxs626.typeinference;

public final class VariableType extends SimpleTypeEntry implements Type {

    @Override
    public boolean isVariable() {
        return true;
    }
}