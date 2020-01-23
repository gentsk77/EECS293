package edu.cwru.yxs626.typeinference;

/**
 * TypeEntry abstracts the concept of a type within an expression.
 * 
 * A difference between a Type and TypeEntry is for example that List is a Type
 * but List<T> is a TypeEntry.
 * 
 * @author Yue Shu
 */
public interface TypeEntry {

    public Type getType();

    public boolean isVariable();
}