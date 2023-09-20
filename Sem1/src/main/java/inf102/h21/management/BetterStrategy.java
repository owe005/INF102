package inf102.h21.management;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Ole Kristian Westby - will improve this algorithm..
 */

public class BetterStrategy extends AbstractStrategy {
	@Override
	protected Job selectJob() {
		double longestTime = 0;
		Job job = backLog.peek();
		for (Job j : backLog) {
			if (j.robotsNeeded > available.size())
				continue;
			double t = j.time;
			if (t > longestTime) {
				longestTime = t;
				job = j;
			}
		}
		return job;
	}


	@Override
	protected List<Robot> selectRobots(Job job) {
		List<Robot> availableRobots = getAvailableRobots();
		List<Robot> selectRobots = new ArrayList<>();
		List<Double> distances = new ArrayList<>();
		List<Double> kNearestDistances = new ArrayList<>();

		for(int i = 0; i < availableRobots.size(); i++) {
			//get the eucledean distance for all the availableRobots from the Job
			double distance = job.location.manhattanDistance(availableRobots.get(i).getLocation());
			distances.add(distance);
		}
		Collections.sort(distances); //n log n
		int count = 0;
		for(double distance : distances){
			if(count < job.robotsNeeded)
			{
				kNearestDistances.add(distance);
				count++;
			}
		}

		int counter = 0;
		for(int i = 0; i < availableRobots.size(); i++) {
			double distance = job.location.manhattanDistance(availableRobots.get(i).getLocation());
			if(kNearestDistances.contains(distance)){
				if(counter < job.robotsNeeded && !availableRobots.isEmpty()){
					selectRobots.add(availableRobots.get(i));
					counter++;
				}
			}
		}
		return selectRobots;
	}

	@Override
	public String getName() {
		return "Better strategy";
	}
}
