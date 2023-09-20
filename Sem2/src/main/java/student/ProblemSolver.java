package student;

import java.util.*;
import graph.Edge;
import graph.Graph;
import graph.WeightedGraph;

public class ProblemSolver implements IProblem {

	@Override
	public <T, E extends Comparable<E>> ArrayList<Edge<T>> mst(WeightedGraph<T, E> g) { //o(n log n)

		T start = g.vertices().iterator().next();

		HashSet<T> seen = new HashSet<T>();
		PriorityQueue<Edge<T>> toSearch = new PriorityQueue<Edge<T>>(g);
		seen.add(start);
		addAll(toSearch,g.adjacentEdges(start));

		ArrayList<Edge<T>> Edges = new ArrayList<Edge<T>>();

		while(!toSearch.isEmpty() && seen.size()<g.numVertices()) {
			Edge<T> min = toSearch.poll();
			if(seen.contains(min.a) && seen.contains(min.b)) {
				continue;
			}

			T newNode;
			if(!seen.contains(min.a)) {
				newNode = min.a;
			}else {
				newNode = min.b;
			}
			seen.add(newNode);
			addAll(toSearch, g.adjacentEdges(newNode));
			Edges.add(min);
		}

		return Edges;
	}

	private <T> void addAll(Collection<T> coll, Iterable<T> elems) { //o(n)
		for(T v : elems) {
			coll.add(v);
		}
	}

	@Override
	public <T> T lca(Graph<T> g, T root, T u, T v) { //O(n)
		HashMap<T, Integer> depths = retDepths(g, root);

		while (!u.equals(v)) {

			if (depths.get(u).equals(depths.get(v))) {
				u = walkParent(g, depths, u);
				v = walkParent(g, depths, v);

			} else if (depths.get(v) > depths.get(u))
				v = walkParent(g, depths, v);

			else
				u = walkParent(g, depths, u);
		}
		return v;
	}

	private <T> HashMap<T, Integer> retDepths(Graph<T> g, T root) { //O(n)
		HashSet<T> verticesDone = new HashSet<>();
		HashMap<T, Integer> depths = new HashMap<>();
		Stack<T> stack = new Stack<>();

		depths.put(root, 0);
		stack.push(root);

		while (!stack.isEmpty()) {
			T vertex = stack.pop();
			verticesDone.add(vertex);

			for (T child : g.neighbours(vertex)) {

				if (verticesDone.contains(child))
					continue;

				depths.put(child, depths.get(vertex) + 1);
				stack.push(child);
			}
		}
		return depths;
	}

	private <T> T walkParent(Graph<T> g, HashMap<T, Integer> depths, T child) { //O(n)
		T parent = child;

		for (T adjacent : g.neighbours(child)) {

			if (depths.get(adjacent) < depths.get(child))
				parent = adjacent;
		}
		return parent;
	}

	@Override
	public <T> Edge<T> addRedundant(Graph<T> tree, T root) { //O(n*log n)
		HashSet<T> visited = new HashSet<>();
		HashMap<T, Integer> size = new HashMap<>();
		size(tree, root, visited, size);

		PriorityQueue<T> sortedRootNeighbours = getChildren(tree, root, size);
		T vertex1 = Vertex(tree, root, sortedRootNeighbours.poll(), size, 0);
		T vertex2 = Vertex(tree, root, sortedRootNeighbours.poll(), size, size.get(sortedRootNeighbours.poll()));

		return new Edge<>(vertex1, vertex2);
	}

	private <T> int size(Graph<T> tree, T node, HashSet<T> visited, HashMap<T, Integer> size) { //O(n)
		if (visited.contains(node))
			return 0;

		visited.add(node);
		int pre_Size = 1;

		for (T child : tree.neighbours(node))
			pre_Size += size(tree, child, visited, size);
		    size.put(node, pre_Size);

		return pre_Size;
	}

	private <T> PriorityQueue<T> getChildren(Graph<T> tree, T parent, HashMap<T, Integer> size) { //O(n*log n)
		PriorityQueue<T> children = new PriorityQueue<>((o1, o2) -> -Integer.compare(size.get(o1), size.get(o2)));

		for (T neighbour : tree.neighbours(parent)) {

			if (size.get(neighbour) < size.get(parent))
				children.add(neighbour);
		}
		return children;
	}

	private <T> T Vertex(Graph<T> tree, T root, T vertices, HashMap<T, Integer> size, Integer thisOutage) { //O(n*log n)
		if (vertices == null)
			return root;

		int largestOutage = (thisOutage == null ? 0 : thisOutage);
		int sizeOfPath = size.get(vertices);

		while (largestOutage <= sizeOfPath) {

			PriorityQueue<T> children = getChildren(tree, vertices, size);
			vertices = children.poll();

			if(size.get(vertices) != null)
				sizeOfPath = size.get(vertices);

				try {

					if(largestOutage < size.get(children.poll()))
						largestOutage = size.get(children.poll());

				} catch (NullPointerException ignored) {
				}
		}
		return vertices;
	}
}