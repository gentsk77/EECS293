package edu.cwru.yxs626.typeinference;

/**
 * Type abstracts the concept of a type such as Integer, List, or T.
 * 
 * @author Yue Shu
 */
public interface Type {

    /**
     * Determines whether the type is a variable, such as T.
     * 
     * @return true if the Type is a varialbe, false elsewise
     */
    public boolean isVariable();
}