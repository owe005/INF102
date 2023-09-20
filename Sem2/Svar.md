# Answer File - Semester 2
# Description of each Implementation
Briefly describe your implementation of the different methods. What was your idea and how did you execute it? If there were any problems and/or failed implementations please add a description.

## Task 1 - mst
First get a vertex, then keep all the vertices already connected, then keep all edges that could be the smallest.
Then while loop when toSearch is not empty, and seen.size is less than the number of vertices. Check if seen.contains min a and b.
Lastly, if seen does not contain min.a define newNode = min.a, else min.b. Add the newNode to seen, return Edges.

## Task 2 - lca
For task 2 I implemented two extra helper methods. As long as u is not equal to v then check if the HashMap depths for u equals v. 
Then define u and v. By comparing adjacent neighbours, we return the parent of the vertex. If a exists, visit b. Then return v.

For retDepths, I created a new HashSet for vertices that are already checked, a new HashMap and a new Stack.
Then while the stack isn't empty add vertices to verticesDone. T vertex = stack.pop gives you first element of list and removes it.
Then loop through neighbours for "child" until it is empty.
Then return depths.

For visitParent, You set T parent equals child, loop through adjacent neighbours for adjacent children and if the depths for adjacent
is less than it is for child. Set parent equals adjacent. Return parent.

## Task 3 - addRedundant
In addRedundant I am using methods from my other three helper methods in order to solve the task.
I don't think there's much use in explaining addRedundant since most of the code exists in other methods.

Firstly in the integer size method we check if visited already contains a node, in which we return 0. 
Afterwards we add a node to the visited list, assuming there wasn't one already. We loop through all 
neighbouring nodes in a tree. Then we add the data to pre_Size and return it. 

Secondly, in the PriorityQueue helper method we first create a new PriorityQueue children, then 
we loop through the neighboring parents in a tree. Then if the size of the neighbour is less than 
the parent; add it children, and return children. 

Lastly, for the Vertex helper method:
First check if vertices == 0, in which return the root.
Then we define two integers, largestOutage and sizeOfPath.
While largestOutage is less than or equal to sizeOfPath, we will define the priorityQueue
children. Then define vertices to be the element at the front of the container and that element will 
also be removed from the container. 
If the size of vertices is not null, then add that size to sizeOfPath. 

I added a try, catch exception. If largestOutage < size of first element in children, then define largestOutage to
be equal to said element. Then return vertices. 



# Runtime Analysis
For each method of the different strategies give a runtime analysis in Big-O notation, and a description of why it has this runtime.

**If you have implemented any helper methods you must add these as well.**

* ``mst(WeightedGraph<T, E> g)``: O(n log n)
    This method is in O(n log n) because of the PriorityQueue.


* ``addAll(Collection<T> coll, Iterable<T> elems)``: O(n)
  This method is in O(n) because of a for loop.


* ``lca(Graph<T> g, T root, T u, T v)``: O(n)
    My lca method is in O(n) simply because there is a while loop.
  
  
* ``addRedundant(Graph<T> g, T root)``: O(n log n)
    This method is in O(n log n) because of the PriorityQueue.
  
  
* ``retDepths(Graph<T> g, T root)``: O(n)
    This method is in O(n) because there is a for loop. Although the for loop is within 
    a while loop, that doesn't change the time complexity because it's not nested.
  
  
* ``visitParent(Graph<T> g, HashMap<T, Integer> depths, T child)``: O(n)
    This method is also in O(n) because of a for loop. 
  

* ``int size(Graph<T> tree, T node, HashSet<T> visited, HashMap<T, Integer> size)``: O(n)
    Again, O(n) because of a for loop.
  

* ``getChildren(Graph<T> tree, T parent, HashMap<T, Integer> size)``: O(n log n)
    This method is in O(n log n) because of the PriorityQueue.
  

* ``Vertex(Graph<T> tree, T root, T vertices, HashMap<T, Integer> size, Integer thisOutage)``: O(n log n)
    This method is in O(n log n) because of the PriorityQueue.
