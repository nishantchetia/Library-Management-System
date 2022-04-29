import java.util.*;
class GCP
{
	static Scanner sc = new Scanner(System.in);
	static int min_color;
	static int cnt = 0;
	public static void main(String args[])
	{
		System.out.print("Enter No. of Vertices = ");
		int n_vert = sc.nextInt();
		System.out.print("Enter No. of Edges = ");
		int n_edges = sc.nextInt();
		int graph[][] = new int[n_vert][n_vert];
		int ver_color[] = new int[n_vert];

		Arrays.fill(ver_color, -1);
		for(int i=0;i<graph.length;i++)
			Arrays.fill(graph[i], -1);
		System.out.println("Enter Paths = ");
		for(int i=0;i<n_edges;i++)
		{
			int src = sc.nextInt();
			int dest = sc.nextInt();
			graph[src][dest] = 1;
			graph[dest][src] = 1;
		}

		min_color = graph_colour_min(graph,ver_color, 0);
		System.out.println("\nChromatic Number for Input Graph = "+ min_color);
		// disp_vert_color(ver_color);
		System.out.println("\nPossible Solutions = ");
		graph_colour(graph,ver_color, 0);
		System.out.println("\nTotal Possible Solutions = "+ cnt);
	}
	static boolean is_safe(int graph[][], int ver_color[], int vert, int clr)
	{
		for(int i=0;i<graph.length;i++)
		{
			if(graph[vert][i] == 1 && ver_color[i] == clr)
				return false;
		}
		return true;
	}

	static void graph_colour(int graph[][], int ver_color[], int vert)
	{
		if (vert == graph.length)
		{
			disp_vert_color(ver_color);
			return;
		}

		for(int i=1;i<=min_color;i++)
		{
			for(int j=vert+1; j<ver_color.length;j++)
				ver_color[j] = -1;
			if(is_safe(graph, ver_color, vert, i))
			{
				ver_color[vert] = i;
				graph_colour(graph, ver_color, vert+1);
			}
		}
	}
	static int graph_colour_min(int graph[][], int ver_color[], int vert)
	{
		while(vert<graph.length)
		{
			for(int i=1;i<=graph.length;i++)
			{
				if(is_safe(graph, ver_color, vert, i))
				{
					ver_color[vert] = i;
					vert +=1;
					break;
				}
			}
		}
		return Arrays.stream(ver_color).max().getAsInt();
	}

	static void disp_vert_color(int clr_arr[])
	{
		for(int i=0;i<clr_arr.length;i++)
			System.out.print(String.format("%d ", clr_arr[i]));
		System.out.println();
		cnt += 1;
	}
}