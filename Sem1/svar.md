# Answer File - Mandatory 1
# Description of each Implementation
Briefly describe your implementation in the different strategies. What was your plan and how did you execute it? If there were any problems and/or issues failed implementations please add a description.

## Task 1 - RandomStrategy
On RandomStrategy what I did was create a list with all available robots, availableRobots. I then created an empty list with selected robots named selectRobot. Then if availableRobots had the required amount or more available robots for job.robotsNeeded, it would run it through a for loop, and add it to the list of selectRobots. If not it would just return selectRobots.

Doing the first task 1.


Analyzing assignRobots() gives us a runtime of O(n) - there are three for loops in this, thus O(3n) which is the same as O(n). Only one of these will run at the same time, and there is no for loop within a for loop.

## Task 2 - ClosestStrategy
I used a lot of code from task 1 in this strategy. Getting the available robots in a list availableRobots. Create a empty list for selectRobots. An empty list for distances and one for k nearest distances. The first for loop gets the distances for all the robots needed for the job. Then I used Collections.sort to sort the distances nearest to the job. Then just add k distances sorted to the nearest distances list and return the selected robots.

## Task 3 - BetterStrategy
My BetterStrategy is not optimal, I will try to optimize it better. But currently it passes 3/6 tests, but it is beaten by ClosestStrategy and RandomStrategy on some inputs. All jobs are executed so it should be alright?
What I do in the code is essentially already explained in the previous textbox above this one.
Only difference is that I have introduced a new method manhattanDistance which is analyzed for runtime further below in this document.
I also expanded upon SelectJob() method to try to improve the job queue. The previous method was just retrieving the first element of the backlog.


# Runtime Analysis
For each method of the different strategies give a runtime analysis in Big-O notation, and a description of why it has this runtime.

**If you have implemented new methods not listed you must add these as well.**
## Task 1 - RandomStrategy
### IStrategy
* ``registerRobots(List<Robot> robots)``: O(n)
  * This is in O(n) because it has to copy all the robots to an ArrayList.
* ``registerNewJob(Job job)``: O(mn)
  * registerNewJob is linked to doJobs() which is in O(mn), accounting for simplicity with keeping the highest order gives us a time complexity of O(n)
* ``registerJobAsFulfilled(Job job)``: O(mn)
  * It is the same with this one, it links to doJobs() which is in O(mn), accounting for simplicity with keeping the highest order gives us a time complexity of O(n)

### AbstractStrategy (if you use it)
* ``doJobs()``: O(mn)
  * In RandomStrategy, `doJobs()` is `O(mk) + O(mn)`. Since k can only be up to 10, this reduces to `O(mn)
* ``selectJob()``: O(1)
  * In RandomStrategy selectJob is not implemented and hence selectJob is constant.
* ``removeJob(Job job)``: O(1)
  * This method is in O(1), it's a constant method and also seems like it's been overcomplicated, don't think that if statement is even necessary.
* ``assignRobots(List<Robot> selected, Job job)``: O(n)
  * Analyzing assignRobots() gives us a runtime of O(n) - there are three for loops in this, thus O(3n) which is the same as O(n). Only one of these will run at the same time, and there is no for loop within a for loop.
* ``getAvailableRobots()``: O(1)
  * It's O(1), checking the implementations gives us no reason to believe it would be more than O(1). It's the same here as registerRobots() I think you could get this to go into a worse order, but for that to happen you would have to have to have ClosestStrategy check available robots in a loop. But mine saves it into a list.

### RandomStrategy
* ``selectRobots(Job job, List<Robot> available)``: O(n)
  * In my RandomStrategy I only have one for loop with O(n) and that is the only O(n) that there is, hence the method is O(n).

## Task 2 - ClosestStrategy
### IStrategy
* ``registerRobots(List<Robot> robots)``: O(n)
  * This is in O(n) because it has to copy all the robots to an ArrayList.
* ``registerNewJob(Job job)``: O(mn)
  * registerNewJob is linked to doJobs() which is in O(mn), accounting for simplicity with keeping the highest order gives us a time complexity of O(n)
* ``registerJobAsFulfilled(Job job)``: O(1)
  * The runtime is constant in this method, regardless of what input you give it.


### AbstractStrategy (if you use it)
* ``doJobs()``: O(mn)
  * This calls `selectRobots(job)`, which is `O(nk)`, inside an `O(m)` loop. Therefore, it is `O(mnk)
* ``selectJob()``: O(1)
  * In RandomStrategy selectJob is not implemented and hence selectJob is constant.
* ``removeJob(Job job)``: O(1)
  * This method is in O(1), is not implemented, and it's a constant method and also seems like it's been overcomplicated, don't think that if statement is even necessary.
* ``assignRobots(List<Robot> selected, Job job)``: O(n)
  * Analyzing assignRobots() gives us a runtime of O(n) - there are three for loops in this, thus O(3n) which is the same as O(n). Only one of these will run at the same time, and there is no for loop within a for loop.
* ``getAvailableRobots()``: O(1)
  * It's O(1), checking the implementations gives us no reason to believe it would be more than O(1). It's the same here as registerRobots() I think you could get this to go into a worse order, but for that to happen you would have to have to have ClosestStrategy check available robots in a loop. But mine saves it into a list.

### ClosestStrategy
* ``selectRobots(Job job, List<Robot> available)``: O(n)
  * My ClosestStrategy implementation is in O(n), it has three for loops, but they are not inside each other leaving O(3n) which is O(n). Since you wouldn't know which one of them is the worst one, you could write it as O(m) + O(n) + O(k) = O(mnk) but that is again simplified to O(n) for simplicity's sake.

## Task 3 - BetterStrategy
### IStrategy
* ``registerRobots(List<Robot> robots)``: O(n)
  * Same as earlier.
* ``registerNewJob(Job job)``: O(n)
  * registerNewJob is linked to doJobs() which is in O(n), accounting for simplicity with keeping the highest order gives us a time complexity of O(n)
* ``registerJobAsFulfilled(Job job)``: O(n)
  * It is the same with this one, it links to doJobs() which is in O(n), accounting for simplicity with keeping the highest order gives us a time complexity of O(n)


### AbstractStrategy (if you use it)
* ``doJobs()``: O(n)
  * doJobs() has a while loop, and backlog's implementation is a Queue which is in O(1). However, there is an implementation to assignRobots which is o(n) therefore to count for higher order. doJobs() is in time complexity O(n)
* ``selectJob()``: O(n)
  * selectJob() has been implemented in BetterStrategy and is now in O(n) due to a for loop in BetterStrategy.
* ``removeJob(Job job)``: O(1)
  * This method is in O(1), it's a constant method and also seems like it's been overcomplicated, don't think that if statement is even necessary.
* ``assignRobots(List<Robot> selected, Job job)``: O(n)
  * Analyzing assignRobots() gives us a runtime of O(n) - it has three for loops, but they are not inside each other leaving O(3n) which is O(n). Since you wouldn't know which one of them is the worst one, you could write it as O(m) + O(n) + O(k) = O(mnk) but that is again simplified to O(n) for simplicity's sake.
* ``getAvailableRobots()``: O(1)
  * It's O(1), checking the implementations gives us no reason to believe it would be more than O(1). It's the same here as registerRobots() I think you could get this to go into a worse order, but for that to happen you would have to have to have ClosestStrategy check available robots in a loop. But mine saves it into a list.


## Location
* ``manhattanDistance``: O(n)
  * For loop in BetterStrategy with manhattanDistance within.

### BetterStrategy
* ``selectRobots(Job job, List<Robot> available)``: O(n)
  * My BetterStrategy code is essentially the same as the ClosestStrategy but beats the other two strategies on 3 inputs. The runtime is the same being O(n) with no loops inside each other. Technically it is O(3n) which is the same as O(n)
