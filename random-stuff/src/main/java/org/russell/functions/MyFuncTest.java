package org.russell.functions;

import java.util.stream.Stream;


public class MyFuncTest {

	public static void main(String[] args) throws Exception {
		final MyFunc times2 = x -> {
			System.out.println("times2");
			return x * 2;
		};
		final MyFunc square = x -> {
			System.out.println("square");
			return x * x;
		};

		MyFunc reduce = Stream.of(times2, square).reduce(MyFunc.identity(), MyFunc::andThen);
		int apply = reduce.apply(4);
		System.out.println(apply);
	}
}

@FunctionalInterface
interface MyFunc {
	Always always = new Always();

	static MyFunc identity() {
		return x -> {
			System.out.println("identity.apply()");
			return x;
		};
	}

	int apply(int x);

	default MyFunc andThen(MyFunc after) {
		//			System.out.println("called andThen");
		return x -> {
			//				int r2 = apply(x);
			//				int rn = after.apply(r2);
			//				System.out.println("x: " + x);
			//				System.out.println("r2: " + r2);
			//				System.out.println("rn: " + rn);
			//				return always.apply(rn);
			return always.apply(after.apply(apply(x)));
		};
	}
}

class Always implements MyFunc {
	@Override
	public int apply(final int x) {
		System.out.println("running always with " + x);
		return x;
	}
}
