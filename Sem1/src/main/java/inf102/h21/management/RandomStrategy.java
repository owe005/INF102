package inf102.h21.management;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ole Kristian Westby
 */

public class RandomStrategy extends AbstractStrategy {

	@Override
	protected List<Robot> selectRobots(Job job) {
		List<Robot> availableRobots = getAvailableRobots();
		List<Robot> selectRobots = new ArrayList<>();

		if(availableRobots.size() >= job.robotsNeeded) //o(1)
		{
			for(int i = 0; i < job.robotsNeeded; i++) { //o(n)

				if(!availableRobots.isEmpty()) //o(1)
					selectRobots.add(availableRobots.get(i));
			}
		}
		return selectRobots;
	}

	@Override
	public String getName() {
		return "Random strategy";
	}
}
