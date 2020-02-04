package edu.cwru.yxs626.typeinference;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A CompoundTypeEntry is an entry corresponding to a CompoundType.
 * 
 * @author Yue Shu
 */
public class CompoundTypeEntry extends AbstractTypeEntry {

    /** The CompoundType corresponding to the CompoundTypeEntry. */
    private final CompoundType type;

    /** The list of sub types corresponding to the CompoundTypeEntry. */
    private final List<TypeEntry> subTypes;

    /**
     * Creates a new CompoundTypeEntry.
     * 
     * @param type     the CompoundType used to define the CompoundTypeEntry
     * @param subTypes the list of sub types used to define the CompoundTypeEntry
     */
    private CompoundTypeEntry(CompoundType type, List<TypeEntry> subTypes) {
        this.type = type;
        this.subTypes = subTypes;
    }

    /**
     * Returns the CompoundType corresponding to the CompoundTypeEntry.
     * 
     * @return the CompoundType corresponding to the CompoundTypeEntry
     */
    public CompoundType getType() {
        return this.type;
    }

    /**
     * Returns the list of sub types corresponding to the CompoundTypeEntry.
     * 
     * @return the list of sub types corresponding to the CompoundTypeEntry
     */
    public List<TypeEntry> getSubTypes() {
        return cloneSubTypes(this.subTypes);
    }

    /**
     * Returns a new CompoundTypeEntry corresponding to the given CompoundType and
     * list of sub types.
     * 
     * @param the      CompoundType used to define the CompoundTypeEntry
     * @param subTypes the list of sub types used to define the CompoundTypeEntry
     * @return a new CompoundTypeEntry
     * @throws ArityException if the number of sub types does not match the arity
     */
    public static final CompoundTypeEntry of(CompoundType type, List<TypeEntry> subTypes) throws ArityException {
        checkSubTypes(type, subTypes);

        return new CompoundTypeEntry(type, cloneSubTypes(subTypes));
    }

    /**
     * Check whether the size of the list of sub types match the gtiven arity of the
     * CompoundType.
     * 
     * @param type     the CompoundType which arity will be checked
     * @param subTypes the list of sub types to be checked
     * @throws ArityException if the number of sub types does not match the arity of
     *                        the CompoundType
     */
    private static void checkSubTypes(CompoundType type, List<TypeEntry> subTypes) throws ArityException {
        Objects.requireNonNull(type, "Input type should not be null");
        Objects.requireNonNull(subTypes, "Input sub type list should not be null.");

        if (subTypes.size() != type.getArity()) {
            throw new ArityException(type, subTypes);
        }
        // else pass the subType and arity test
    }

    /**
     * Returns a clone of the given list of sub types.
     * 
     * @param subTypes the list of sub types to be cloned
     * @return a clone of the given list of sub types
     */
    private static List<TypeEntry> cloneSubTypes(List<TypeEntry> subTypes) {
        return subTypes.stream().collect(Collectors.toList());
    }

    /**
     * Returns a string representation of the CompoundTypeEntry.
     * 
     * @return a string representation of the CompoundTypeEntry
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getType().toString() + "<");

        Iterator<TypeEntry> subTypeIterator = subTypes.iterator();

        APPEND_SUBTYPES: 
        while (subTypeIterator.hasNext()) {
            sb.append(subTypeIterator.next().toString());

            if (!subTypeIterator.hasNext()) {
                break APPEND_SUBTYPES;
            }
            // else there are still at least one element left

            sb.append(", ");
        }

        sb.append(">");
        return sb.toString();
    }

}