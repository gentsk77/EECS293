package edu.cwru.yxs626.typeinference;

import java.util.ArrayList;
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
        return type;
    }

    /**
     * Returns the list of sub types corresponding to the CompoundTypeEntry.
     * 
     * @return the list of sub types corresponding to the CompoundTypeEntry
     */
    public List<TypeEntry> getSubTypes() {
        return cloneSubTypes(subTypes);
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
        Objects.requireNonNull(type, "Input type should not be null");
        Objects.requireNonNull(subTypes, "Input sub type list should not be null.");

        if (subTypes.size() != type.getArity()) {
            throw new ArityException(type, subTypes);
        }
        // else pass the subType and arity test

        return new CompoundTypeEntry(type, cloneSubTypes(subTypes));
    }

    /**
     * Returns a clone of the given list of sub types.
     * 
     * @param subTypes the list of sub types to be cloned
     * @return a clone of the given list of sub types
     */
    private static final List<TypeEntry> cloneSubTypes(List<TypeEntry> subTypes) {
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
        sb.append(getType().toString() + "<");

        Iterator<TypeEntry> subTypeIterator = getSubTypes().iterator();

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

    /**
     * Determines if this TypeEntry has the equal underlying type as the given
     * TypeEntry.
     * 
     * @return true if the CompoundTypes of the two CompoundTypeEntries are the same
     */
    @Override
    public boolean hasEqualUnderlyingType(TypeEntry other) {
        Objects.requireNonNull(other, "Input TypeEntry should not be null");

        return this.getType().equals(other.getType());
    }

    /**
     * Return a string representation of the representative of its corresponding
     * TypeGroup in the given TypeSystem.
     * 
     * @param typeSystem the TypeSystem containing this TypeEntry
     * @return a string representation of the representative of this TypeEntry
     */
    @Override
    protected String basicRepresentativeString(TypeSystem typeSystem) {
        Objects.requireNonNull(typeSystem, "Input TypeSystem should not be null");

        List<TypeEntry> subTypeRepresentatives = new ArrayList<>();

        for (TypeEntry subType : getSubTypes()) {
            subTypeRepresentatives.add(typeSystem.representative(subType));
        }

        // init to be this to avoid initialization exceptions
        TypeEntry compoundTypeEntryRepresentative = this;

        try {
            compoundTypeEntryRepresentative = CompoundTypeEntry.of(getType(), subTypeRepresentatives);
        } catch (ArityException e) {
            // indicating this is not supposed to happen
            assert false;
            // the error really shouldn't happen here
            // since the arity should be exactly the same
        }

        return compoundTypeEntryRepresentative.toString();
    }
}