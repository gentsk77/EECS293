package edu.cwru.yxs626.typeinference;

/**
 * 
 */
public abstract class SimpleTypeEntry extends AbstractTypeEntry implements Type {

    @Override
    public Type getType() {
        return this;
    }

}