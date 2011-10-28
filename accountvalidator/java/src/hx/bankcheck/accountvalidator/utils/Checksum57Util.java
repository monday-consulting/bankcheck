package hx.bankcheck.accountvalidator.utils;

import java.util.HashMap;
import java.util.Map;

public class Checksum57Util {

	private static Map<Integer, Integer> ALTERNATIVES = new HashMap<Integer, Integer>() {

		private static final long serialVersionUID = 1L;

		{
			// Alternative 1
			put(51, 0);
			put(55, 0);
			put(61, 0);
			put(64, 0);
			put(65, 0);
			put(66, 0);
			put(70, 0);
			put(73, 0);
			put(75, 0);
			put(76, 0);
			put(77, 0);
			put(78, 0);
			put(79, 0);
			put(80, 0);
			put(81, 0);
			put(82, 0);
			put(88, 0);
			put(94, 0);
			put(95, 0);

			// Alternative 2
			put(32, 1);
			put(33, 1);
			put(34, 1);
			put(35, 1);
			put(36, 1);
			put(37, 1);
			put(38, 1);
			put(39, 1);
			put(41, 1);
			put(42, 1);
			put(43, 1);
			put(44, 1);
			put(45, 1);
			put(46, 1);
			put(47, 1);
			put(48, 1);
			put(49, 1);
			put(52, 1);
			put(53, 1);
			put(54, 1);
			put(56, 1);
			put(57, 1);
			put(58, 1);
			put(59, 1);
			put(60, 1);
			put(62, 1);
			put(63, 1);
			put(67, 1);
			put(68, 1);
			put(69, 1);
			put(71, 1);
			put(72, 1);
			put(74, 1);
			put(83, 1);
			put(84, 1);
			put(85, 1);
			put(86, 1);
			put(87, 1);
			put(89, 1);
			put(90, 1);
			put(92, 1);
			put(93, 1);
			put(96, 1);
			put(97, 1);
			put(98, 1);

			// Alternative 3
			put(40, 2);
			put(50, 2);
			put(91, 2);
			put(99, 2);

			// Alternative 4
			put(1, 3);
			put(2, 3);
			put(3, 3);
			put(4, 3);
			put(5, 3);
			put(6, 3);
			put(7, 3);
			put(8, 3);
			put(9, 3);
			put(10, 3);
			put(11, 3);
			put(12, 3);
			put(13, 3);
			put(14, 3);
			put(15, 3);
			put(16, 3);
			put(17, 3);
			put(18, 3);
			put(19, 3);
			put(20, 3);
			put(21, 3);
			put(22, 3);
			put(23, 3);
			put(24, 3);
			put(25, 3);
			put(26, 3);
			put(27, 3);
			put(28, 3);
			put(29, 3);
			put(30, 3);
			put(31, 3);

		}
	};

	public static int getAlternative(int first2Digits) {
		if (ALTERNATIVES.containsKey(first2Digits)) {
			return ALTERNATIVES.get(first2Digits).intValue();
		} else {
			return -1;
		}
	}

}
