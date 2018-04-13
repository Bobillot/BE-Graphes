package org.insa.algo.shortestpath;

import java.util.Arrays;

import org.insa.graph.Graph;
import org.insa.graph.Node;
import org.insa.algo.utils.*;

import java.util.ArrayList;
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
		BinaryHeap<Label> Tas = new BinaryHeap() ;
		Label temp;
		
		// structure de la solution
        ShortestPathSolution solution = null;
        
        // variables utiles
		final int nbNodes = graph.size();
		int nbSommetsMarques = 0;
        
        
        // init du tableau de label(premiere ligne avec des infini et un zero dans le noeud de depart)
		ArrayList<Label> distances = new ArrayList<Label>();
		for (Node node : graph)
		{
			if (node.equals(data.getOrigin()))
			{
				distances.add(new Label(node,0,null,0)) ;
				Tas.insert(distances.get(distances.size())); //Ajout dans le tas
				
			}
			else
			{
				distances.add(new Label(node,Double.POSITIVE_INFINITY,null,0)) ;
			}		
		}
		
        // --------------------iterations (poly1 page 89)-----------------
		while (nbSommetsMarques!=nbNodes) 
		{
			temp = Tas.findMin();
			distances.get(distances.indexOf(temp)).setMarquage(1); 
		}
		
		
        
        

        return solution;
    }

}
