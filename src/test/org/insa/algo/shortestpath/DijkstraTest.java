package org.insa.algo.shortestpath;

import java.io.IOException;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.Node;
import org.insa.graph.RoadInformation;
import org.insa.graph.RoadInformation.RoadType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.insa.algo.shortestpath.*;

public class DijkstraTest 
{
	
    // Notre graphe de test
    private static Graph graph;

    // Liste de nodes
    private static Node[] nodes;

    // Liste d'arcs du graphe
    private static Arc x1_x2, x1_x3, x2_x4, x2_x5, x2_x6, x3_x2, x3_x6, x3_x1, x5_x4, x5_x6, x5_x3, x6_x5 ;
	
    
    // création du graphe
    @BeforeClass
    public static void initAll() throws IOException 
    {
        // necessaire à linknodes
        RoadInformation speed10 = new RoadInformation(RoadType.MOTORWAY, null, true, 36, "") ;
	
        // Création de la liste de nodes
        nodes = new Node[6];
        for (int i = 0; i < nodes.length; ++i) 
        {
            nodes[i] = new Node(i, null);
        }
        
        // Définition des arcs
        x1_x2 = Node.linkNodes(nodes[0], nodes[1], 7, speed10, null);
        x1_x3 = Node.linkNodes(nodes[0], nodes[2], 8, speed10, null);
        x2_x4 = Node.linkNodes(nodes[1], nodes[3], 4, speed10, null);
        x2_x5 = Node.linkNodes(nodes[1], nodes[4], 1, speed10, null);
        x2_x6 = Node.linkNodes(nodes[1], nodes[5], 5, speed10, null);
        x3_x2 = Node.linkNodes(nodes[2], nodes[1], 2, speed10, null);
        x3_x6 = Node.linkNodes(nodes[2], nodes[5], 2, speed10, null);
        x3_x1 = Node.linkNodes(nodes[2], nodes[0], 7, speed10, null);
        x5_x4 = Node.linkNodes(nodes[4], nodes[3], 2, speed10, null);
        x5_x6 = Node.linkNodes(nodes[4], nodes[5], 3, speed10, null);
        x5_x3 = Node.linkNodes(nodes[4], nodes[2], 2, speed10, null);
        x6_x5 = Node.linkNodes(nodes[5], nodes[4], 3, speed10, null);
        
        // Création du graphe
        graph = new Graph("ID", "", Arrays.asList(nodes), null);
    }
       
    @Test
    public void testDijkstra() 
    {

    	// tableau des solutions
    	float tab[][]=new float[6][6];
    	float tab2[][]=new float[6][6];
    	
    	// parcours du tableau
    	for(int i = 0; i<6 ; i++)
    	{
    		for (int j=0 ; j<6 ; j++)
    		{
    			if( i != j)
    			{
	    			// création du data étudié (Graph + chemin)
	    			ShortestPathData data = new ShortestPathData(graph, nodes[i], nodes[j],ArcInspectorFactory.getAllFilters().get(0));
	    			// appel de Bellman
	    			ShortestPathSolution solutionBellman = new BellmanFordAlgorithm(data).doRun() ;
	    			// appel de Dijkstra
	    			ShortestPathSolution solutionDijkstra = new DijkstraAlgorithm(data).doRun() ;
	    			
	    			// on remplit le tableau de solution de Dijkstra
	    			if (solutionDijkstra.getPath() != null )
	    			{
	    				tab[i][j] = solutionDijkstra.getPath().getLength() ;
	    				
	    			}
	    			else
	    			{
	    				tab[i][j] = -1 ;
	    			}
	    			
	    			// on remplit le tableau de solution de Bellman
	    			if (solutionBellman.getPath() != null )
	    			{
	    				tab2[i][j] = solutionBellman.getPath().getLength() ;
	    				
	    			}
	    			else
	    			{
	    				tab2[i][j] = -1 ;
	    			}		
	    			
	    			// Comparaison des deux solutions
	    	    	assertEquals(tab2[i][j],tab[i][j],0.01*tab2[i][j]);
	    			
	    			
	    	    	
	    			
    			}		
    		}
    	}
    	
    	
    	// affichage du tableau

    	System.out.println("Dijkstra");
    	System.out.println(Arrays.deepToString(tab));
    	System.out.println("Bellman");
    	System.out.println(Arrays.deepToString(tab2));
    	
    }

}
