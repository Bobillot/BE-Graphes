package org.insa.algo.shortestpath;

import java.util.Arrays;

import org.insa.graph.Graph;
import org.insa.graph.Node;
import org.insa.algo.utils.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() 
    {
    	// --------------------initialisation --------------------
    	// recup le graphe
        ShortestPathData data = getInputData();
		Graph graph = data.getGraph();
		
		// structure de la solution
        ShortestPathSolution solution = null;
        
        // variables utiles
		final int nbNodes = graph.size();
        
        
        // init du tableau de label(premiere ligne avec des infini et un zero dans le noeud de depart)
		Label[] distances = new Label[nbNodes];
		int i = 0 ;
		for (Node node : graph)
		{
			if (node.equals(data.getOrigin()))
			{
				distances[i] = new Label(node,0,null,0) ;
			}
			else
			{
				distances[i]= new Label(node,Double.POSITIVE_INFINITY,null,0) ;
			}
			
			i++ ;		
		}
		
        // --------------------iterations --------------------
		BinaryHeap<Label> Tas = new BinaryHeap() ;
		
		
		
        
        

        return solution;
    }

}
