package org.fuzzingtool.symbolic.logical;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import org.fuzzingtool.symbolic.ExpressionType;
import org.fuzzingtool.symbolic.LanguageSemantic;
import org.fuzzingtool.symbolic.SymbolicException;
import org.fuzzingtool.symbolic.SymbolicNode;
import org.graalvm.collections.Pair;

public class Not extends SymbolicNode {
	public Not(LanguageSemantic s, SymbolicNode a) throws SymbolicException.WrongParameterSize {
		this.languageSemantic = s;
		addChildren(1, a);
	}

	@Override
	public String toHRStringJS() {
		return parentheses("¬" + this.children[0].toString());
	}

	@Override
	public String toSMTExprJS() throws SymbolicException.NotImplemented {
		return parentheses("not " + this.children[0].toSMTExpr());
	}

	/**
	 * Logical 'not' operator as in
     * <a href="https://tc39.es/ecma262/2020/#sec-logical-not-operator">ECMAScript® 2020 Language Specification</a>
	 *
	 * @param ctx Z3-Context
	 * @return Result of the '!' operation
	 * @throws SymbolicException.NotImplemented
	 */
	@Override
	public Pair<Expr, ExpressionType> toZ3ExprJS(Context ctx) throws SymbolicException.NotImplemented,
			SymbolicException.UndecidableExpression {
		Pair<Expr, ExpressionType> a_boolean = toBooleanZ3JS(ctx, this.children[0].toZ3Expr(ctx));
		return Pair.create(ctx.mkNot((BoolExpr) a_boolean.getLeft()), ExpressionType.BOOLEAN);
	}
}
