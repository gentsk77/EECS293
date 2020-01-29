package edu.cwru.yxs626.typeinference;

/**
 * VariableType can be representation such as T in List<T> and is essential to
 * define generics.
 * 
 * @author Yue Shu
 */
public final class VariableType extends SimpleTypeEntry implements Type {

    /**
     * Determines whether the VariableType is a varialbe.
     * 
     * @return true since VariableType is a variable
     */
    @Override
    public boolean isVariable() {
        return true;
    }
}