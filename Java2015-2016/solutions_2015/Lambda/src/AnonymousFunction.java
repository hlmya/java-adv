import java.util.function.BiFunction;

public class AnonymousFunction {
	public static void main(String[] args) {
		Operation op = new AddOp();
		Operation op2 = new MulOp();

		Operation op3;
		if ("mul".equals("add"))
			op3 = new AddOp();
		else
			op3 = new MulOp();

		// anonymous class
		BiFunction<Integer, Integer, Integer> op4 = new BiFunction<Integer, Integer, Integer>() {
			@Override
			public Integer apply(Integer t, Integer u) {
				return t + u;
			}
		};

		BiFunction<Integer, Integer, Integer> op5 = (n, m) -> n + m;

		System.out.println(op.op(3, 5));
		System.out.println(op2.op(3, 5));
		System.out.println(op3.op(3, 5));
		System.out.println(op4.apply(3, 5));
		System.out.println(op5.apply(3, 5));
	}
}

interface Operation {
	int op(int n, int m);
}

class AddOp implements Operation {
	@Override
	public int op(int n, int m) {
		return n + m;
	}
}

class MulOp implements Operation {
	@Override
	public int op(int n, int m) {
		return n * m;
	}
}
