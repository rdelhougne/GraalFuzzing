package org.fuzzingtool.symbolic.logical;

import org.fuzzingtool.symbolic.SymbolicException;
import org.fuzzingtool.symbolic.SymbolicNode;
import org.fuzzingtool.symbolic.Type;

public class GreaterEqual extends SymbolicNode {
    public GreaterEqual(SymbolicNode a, SymbolicNode b) throws SymbolicException.IncompatibleType, SymbolicException.WrongParameterSize {
        if (a.type == b.type && (a.type == Type.INT || a.type == Type.REAL)) {
            this.type = Type.BOOLEAN;
            addChildren(2, a, b);
        } else {
            throw new SymbolicException.IncompatibleType(a.type, "GE");
        }
    }

    @Override
    public String toString() {
        return encapsulate(this.children[0].toString() + " ≥ " + this.children[1].toString());
    }

    @Override
    public String toSMTExpr() {
        return encapsulate("GreaterEqual: NOT IMPLEMENTED");
    }

    public static GreaterEqual ge(SymbolicNode a, SymbolicNode b) throws SymbolicException.IncompatibleType, SymbolicException.WrongParameterSize {
        return new GreaterEqual(a, b);
    }
}
