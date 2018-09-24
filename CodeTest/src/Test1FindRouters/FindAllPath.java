package Test1FindRouters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import Test1FindRouters.Graph.EdgeNode;

/**
 * Find all possible routes
 * @author xuzhi
 *
 */
public class FindAllPath
{

	public Map<Integer, Boolean> states = new HashMap();

	// store nodes into stack
	public Stack<Integer> stack = new Stack();

	// print the details of route
	public Set<String> printPath(Set<String> routes)
	{
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (Integer i : stack)
		{
			if (count > 8)
				break;
			sb.append(i + "->");
			count++;
		}
		sb.delete(sb.length() - 2, sb.length());
		routes.add(sb.toString());
		return routes;
	}

	// get the location of next adjacent node
	public int getNextNode(Graph graph, int x, int y)
	{
		int next_node = -1;
		EdgeNode edge = graph.vexsarray[x].firstEdge;
		if (null != edge && y == -1)
		{
			int n = edge.adjvex;
			// node is not in the stack
			if (!states.get(n))
				return n;
			return -1;
		}

		while (null != edge)
		{
			// node is not visited
			if (edge.adjvex == y)
			{
				if (null != edge.nextEdge)
				{
					next_node = edge.nextEdge.adjvex;

					if (!states.get(next_node))
						return next_node;
				}
				else
					return -1;
			}
			edge = edge.nextEdge;
		}
		return -1;
	}

	public void visit(Graph graph, int x, int y, Set<String> routes)
	{
		// initial all states of nodes in the stack
		for (int i = 0; i < graph.vexsarray.length; i++)
		{
			states.put(i, false);
		}
		
		int top_node;
		// Store the adjacent node which has been visited, if node
		// didn't exist, set value to -1
		int adjvex_node = -1;
		int next_node;
		stack.add(x);
		while (!stack.isEmpty())
		{
			top_node = stack.peek();
			// find the target node
			if (top_node == y)
			{
				// print routes
				printPath(routes);
			}
			next_node = getNextNode(graph, top_node, adjvex_node);
			if (next_node != -1)
			{
				stack.push(next_node);
				states.put(next_node, true);
				adjvex_node = -1;
			}
			else
			{
				// visited the adjvex_node number of node of top_node
				adjvex_node = stack.pop();
				// Not in the stack
				states.put(adjvex_node, false);
			}
		}
	}
}
