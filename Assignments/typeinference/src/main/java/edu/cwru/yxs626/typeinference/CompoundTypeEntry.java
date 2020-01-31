package edu.cwru.yxs626.typeinference;

import java.util.List;
import java.util.stream.Collectors;

public class CompoundTypeEntry extends AbstractTypeEntry {

    private final CompoundType type;

    private final List<TypeEntry> subTypes;

    private CompoundTypeEntry(CompoundType type, List<TypeEntry> subTypes) {
        this.type = type;
        this.subTypes = subTypes;
    }

    public CompoundType getType() {
        return this.type;
    }

    public List<TypeEntry> getSubTypes() {
        return cloneSubTypes(this.subTypes);
    }

    public static final CompoundTypeEntry of(CompoundType type, List<TypeEntry> subTypes) throws ArityException {
        checkSubTypes(type, subTypes);

        return new CompoundTypeEntry(type, cloneSubTypes(subTypes));
    }

    private static void checkSubTypes(CompoundType type, List<TypeEntry> subTypes) throws ArityException {
        if (type == null || subTypes == null) {
            throw new NullPointerException("The input should not contain any null element");
        } else if (subTypes.size() != type.getArity()) {
            throw new ArityException(type, subTypes);
        }
        // else pass the subType and arity test
    }

    private static List<TypeEntry> cloneSubTypes(List<TypeEntry> subTypes) {
        return subTypes.stream().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    }


} 