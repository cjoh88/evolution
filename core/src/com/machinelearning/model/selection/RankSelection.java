package com.machinelearning.model.selection;

import com.machinelearning.Utility;
import com.machinelearning.model.Animal;
import com.machinelearning.model.Config;

public class RankSelection implements Selection2 {

	private static final int totalRank = sum(Config.NUM_INDIVIDUALS_PREY);

	private static int sum(int num) {
		int sum = 0;
		for (int i = 1; i <= num; i++) {
			sum += i;
		}
		return sum;
	}

	@Override
	public Animal[] select(Animal[] input, int returnCount) {
		Animal[] result = new Animal[returnCount];
		for (int i = 0; i < returnCount; i++) {
			Animal s = selectOne(input);
			boolean hasBeenSelected = false;
			for (int x = 0; x < i; x++) {
				if (input[x].id() == s.id()) {
					hasBeenSelected = true;
					i--;
					break;
				}
			}
			if (!hasBeenSelected) {
				result[i] = s;
			}
		}
		return result;
	}

	public Animal selectOne(Animal[] input) {
		int x = Utility.random.nextInt(totalRank);
		int sum = 0;
		int b = 0;
		for (int a = 1; a <= input.length; a++) {
			sum += a;
			if (sum > x) {
				b = input.length - a;
				break;
			}
		}
		return input[b];
	}

}
