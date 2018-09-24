package Test1FindRouters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

public class TestCase
{
	public static void main(String[] args) throws IOException
	{
		int[] vexs = {0,1,2,3,4};
		int[][] edges = {
				{0,1},
				{1,2},
				{2,3},
				{3,2},
				{3,4},
				{0,3},
				{2,4},
				{4,1},
				{0,4}
		};
		Graph graph = new Graph();
		graph.buildGraph(vexs, edges);
    String line = "";
		System.out.println("Please type the start and end points");
		Set<String> routes = new HashSet<>();
		try (Reader in = new InputStreamReader(System.in);
		    BufferedReader reader = new BufferedReader(in))
		{
			System.out.print("> ");
			line = reader.readLine();
			line = line.replaceAll(" ", "");
			
			FindAllPath findAllPath = new FindAllPath();
			findAllPath.visit(graph, line.charAt(0)-'A', line.charAt(1)-'A', routes);
			for(String route : routes)
			{
				System.out.println(route);
			}
		}
		
		
	}
}
