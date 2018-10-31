package training.uva.utils;

import java.awt.Adjustable;
//A Java program to print topological sorting of a graph 
//using indegrees 
import java.util.*;

//Class to represent a graph 
public class Graph {
	int V;// No. of vertices

	// An Array of List which contains
	// references to the Adjacency List of
	// each vertex
	List<Integer> adj[];
	List<Integer> distances[];

	public Graph(int V)// Constructor
	{
		this.V = V;
		adj = new ArrayList[V];
		distances = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<Integer>();
			distances[i] = new ArrayList<Integer>();
		}
	}

	// function to add an edge to graph
	public void addEdge(int u, int v) {
		adj[u].add(v);
	}

	// prints a Topological Sort of the complete graph
	public Stack<Integer> topologicalSort() {
		// Create a array to store indegrees of all
		// vertices. Initialize all indegrees as 0.
		int indegree[] = new int[V];

		// Traverse adjacency lists to fill indegrees of
		// vertices. This step takes O(V+E) time
		for (int i = 0; i < V; i++) {
			ArrayList<Integer> temp = (ArrayList<Integer>) adj[i];
			for (int node : temp) {
				indegree[node]++;
			}
		}

		// Create a queue and enqueue all vertices with
		// indegree 0
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < V; i++) {
			if (indegree[i] == 0)
				q.add(i);
		}

		// Initialize count of visited vertices
		int cnt = 0;

		// Create a vector to store result (A topological
		// ordering of the vertices)
		Stack<Integer> topOrder = new Stack<Integer>();
		while (!q.isEmpty()) {
			// Extract front of queue (or perform dequeue)
			// and add it to topological order
			int u = q.poll();
			topOrder.add(u);

			// Iterate through all its neighbouring nodes
			// of dequeued node u and decrease their in-degree
			// by 1
			for (int node : adj[u]) {
				// If in-degree becomes zero, add it to queue
				if (--indegree[node] == 0)
					q.add(node);
			}
			cnt++;
		}

		// Check if there was a cycle
		if (cnt != V) {
			System.out.println("There exists a cycle in the graph");
		}

		// Print topological order
		for (int i : topOrder) {
			System.out.print((i+1) + " ");
		}
		System.out.println("----------");
		return topOrder;
	}
	
	public void longestPathFromVertex(int source) {
		int[] tempDistances = new int[V];
		// Initialize array of distances
		for(int i = 0; i<V ; i++) {
			tempDistances[i] = Integer.MIN_VALUE;
		}
		// Distance to itself = 0
		tempDistances[source] = 0;
		
		// Get topologically ordered graph
		Stack<Integer> orderedGraph = topologicalSort();
		
		//int a = longPath(orderedGraph,0);
		
		// Process vertices in topological order
		while(!orderedGraph.isEmpty()) {
			// Get next in topological order
			int next = orderedGraph.pop();
			
			// Update distances on all adjacent vertices
			if(tempDistances[next] != Integer.MIN_VALUE) {
				for(int i = 0; i<adj[next].size(); i++){
					
					int cmp = adj[next].get(i);
					
					if(tempDistances[cmp] < tempDistances[next] + 1) {
						tempDistances[cmp] = tempDistances[next] +1;
					}
				}
			}
		}
		
		if(distances[source].isEmpty()) {
			for(int i : tempDistances) {
				distances[source].add(i);
			}
		}
		
		
		
		
		
		
	}
	
	public int longPath(Stack<Integer> graph, int totalDistance) {
		List<Integer> copy = new Stack<Integer>();
		
		graph.forEach(x -> copy.add(x));
		
		int maxCost = 0;
		int nextCost;
		
		
		
		for(int i = 0; i<V; i++) {

			int nextNode = copy.get(i);
			List<Integer> adja = adj[nextNode];
				
			
			for(int j = 0; i<adja.size(); i++) {
				int prox = copy.get(j);
				if(i!=j) {
//					int proxC = findCost(prox,copy,adj[prox]);
//					if(proxC > maxCost)
//						maxCost += proxC;
				
				}
				
			}
//			nextCost  =  findCost(nextNode,copy, adja);
//			if(nextCost > maxCost) 
//				maxCost = nextCost;
		}
		
		// Obter max path dos adjacentes
		
		
		
		return -1;
	}

	
//	public int findCostAux(int i) {
//		if(adj[i].isEmpty()) return 0;
//		else {
//			
//		}
//	}
//	
//	
//	private int findCost(int nextNode, List<Integer> copy, List<Integer> adja) {
//		
//		if(adja.isEmpty()) return 0;
//		else {
//			
//			for(int i = 0; i<adja.size(); i++) {
//				int auxC = findCostAux(adja.get(i));
//			}
//		}
//		
//	}
	
	public int maxIndexOfArray(int[] maxDist) {
		int max = 0;
		int maxI = 0;
		
		for(int i = 0; i<V; i++) {
			if(maxDist[i] > max) {
				max = maxDist[i];
				maxI = i;
			}
		}
		return maxI;
	}
	
	public int maxOfArray(int[] maxDist) {
		int max = 0;
		
		for(int i = 0; i<V; i++) {
			if(maxDist[i] > max) {
				max = maxDist[i];
			}
		}
		return max;
	}

	public void longerPath() {
		
		
		for(int i=0; i<V ;i++) {
			longestPathFromVertex(i);
		}
		
		
		List<Integer> orderedGraph = topologicalSort();
		
		
		int maa = 0;
		int maxmax[] = new int[V];
		
		for(int vertex : orderedGraph) {
			int maxDist[] = new int[V];
			
			int l = costestPath(vertex,maxDist);
			maxmax[vertex] = maxOfArray(maxDist);
			if(l>maa)maa=l;
			System.out.println(l);
		}
		
		ArrayList<Integer> seq = new ArrayList<Integer>(V);
		
		int maxIndex = maxIndexOfArray(maxmax);
		List<Integer> afterInTopo = afterInTopologicalOrder(maxIndex);
		
		
		Map<Integer,Integer> toEnd = new HashMap<Integer,Integer>();
		distanceToEnd(toEnd,afterInTopo);
		
		getLongestSequence(seq,maxIndexOfArray(maxmax));
		
		System.out.println("l");
	}
	
	
	
	
	private void distanceToEnd(Map<Integer, Integer> toEnd, List<Integer> afterInTopo) {
		for(int i = afterInTopo.size()-1; i>=0 ;i--) {
			int v = afterInTopo.get(i);
			if(adj[v].isEmpty()) toEnd.put(v, 0);
			else {
				if(toEnd.get(v) != null) {
					toEnd.replace(v, toEnd.get(v) + 1);
				}
				else {
					toEnd.put(v, 1);
				}
			
				for(int j : adj[v]) {
					if(toEnd.get(j) != null) {
						toEnd.replace(j, toEnd.get(j) + 1);
					}
						else {
							toEnd.put(j, 1);
					
					}
				}
			}
		}
		
	}

	public List<Integer> afterInTopologicalOrder(int vertex) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		
		
		List<Integer> a = topologicalSort();
		
		for(int i = a.indexOf(vertex); i<V; i++) {
			res.add(a.get(i));
		}
		
		
		return res;
		
	}
	
	
	private void getLongestSequence(List<Integer> sequence, int maxOfArray) {
		
		// Verificar se está para tras na topological order (ignorar neste caso)
		List<Integer> afterInTopo = afterInTopologicalOrder(maxOfArray);
		
		// Adjacentes do max
		List<Integer> adjMax = adj[maxOfArray];
		
		List<Integer> toCalculate = new ArrayList<Integer>();
		
		for(int a : adjMax) {
			if(!afterInTopo.contains(a)) continue;
			else {
				toCalculate.add(a);
			}
		}
		
		int[] dists = new int[V];
		for(int i = 0; i<V; i++) {
			dists[i] = -1;
		}

		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		int l = 0;
		for(Integer c : toCalculate) {
			l = costly(map,c, dists);
			System.out.println(l);
		}
		
		
		for(int i = 0; i<V; i++) {
			if(toCalculate.contains(i)) {
				map.put(i, dists[i]);
			}
		}
		
		
		
		System.out.println(sequence);
	}
	
	
	private int costly(Map<Integer,Integer> several, int vertex, int[] maxDist) {
		List<Integer> costList = adj[vertex];
		
		if(costList.isEmpty()) {
			System.out.println("Ending Point");
			return 0;
		}
		
		for(int adjTest : costList) {
			int[] maxx = new int[V];
			for(int i=0; i<V; i++) maxx[i] = -1;
			int err = costlyAux(maxx, adjTest);
			
			int n = maxOfArray(maxx);
			int nI = maxIndexOfArray(maxx);
			if(n>0) several.put(nI, n);
		}
		
		return 0;
}
	
	
	private int costlyAux(int[] maxx, int vertex) {
		List<Integer> costList = adj[vertex];
		
		if(costList.isEmpty()) {
			System.out.println("Ending Point");
			return 0;
		}
		
		for(int adjTest : costList) {
			maxx[vertex] += 1 + costlyAux(maxx, adjTest);
		}
		
		return 0;
	}

	private int costInSequence (List<Integer> list, int vertex, int[] maxDist) {
		List<Integer> costList = adj[vertex];
		
		if(costList.isEmpty()) {
			System.out.println("Ending Point");
			return 0;
		}
		
		for(int adjTest : costList) {
			if(list.contains(adjTest))
				maxDist[vertex] += 1 + costInSequence(list,adjTest, maxDist);
		}
		
		return 0;
	}

	private int costestPath(int vertex, int[] maxDist) {
		List<Integer> costList = adj[vertex];
		
		if(costList.isEmpty()) {
			System.out.println("Ending Point");
			return 0;
		}
		
		for(int adjTest : costList) {
			maxDist[vertex] += 1 + costestPath(adjTest, maxDist);
		}
		
		return 0;
}

	public void longestPath() {
		
		
		for(int i=0; i<V ;i++) {
			longestPathFromVertex(i);
		}
		
		int[] dis = new int[V];
		int[] novo = new int[V];
		int maxC = 0;
		for(int i = 0; i<V; i++) {
			novo[i] = 0;
			dis[i] = distances[0].get(i);
		}
		
		
		
		
		List<Integer> endings = new ArrayList<Integer>();
		for(int i = 0; i<V; i++) {
			List<Integer> ds = distances[i];
			boolean nFails = false;
			for(int j = 0; j<V && !nFails; j++) {
				if(ds.get(j) > 0) {
					nFails = true;
				}
			}

			if(!nFails) {
				endings.add(i);
			}
		}
		
		
		// Pegar nos endings
		
		// 
		
		
		
		for(int i = 1; i<V ; i++) {
			List<Integer> prev = distances[i-1];
			List<Integer> act = distances[i];
			
			
			
			int prevDist = prev.get(i);
			
			for(int j = 0; j<V ; j++) {
				if(prev.get(j) != 0 && prev.get(j) != Integer.MIN_VALUE  
						&& act.get(j) != 0 && act.get(j) != Integer.MIN_VALUE ) {

					act.set(j,prevDist + act.get(j));
				}
			}
			
			distances[i] = act;
		}
		

		System.out.println("finito1");
	}
}