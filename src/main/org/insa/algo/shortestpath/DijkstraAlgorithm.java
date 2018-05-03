package org.insa.algo.shortestpath;

import java.util.Arrays;
import java.util.Collections;

import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.Node;
import org.insa.graph.Path;
import org.insa.algo.AbstractSolution.Status;
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
		BinaryHeap<Label> Tas = new BinaryHeap<Label>() ;
		Label x;
		
		// structure de la solution
        ShortestPathSolution solution = null;
        
        // variables utiles
		final int nbNodes = graph.size();
		int nbSommetsMarques = 0;
        
        
        // init du tableau de label(premiere ligne avec des infini et un zero dans le noeud de depart)
		ArrayList<Label> distances = new ArrayList<Label>();
		ArrayList<Node> tabNode = new ArrayList<Node>();
		for (Node node : graph)
		{
			if (node.equals(data.getOrigin()))
			{
				distances.add(new Label(node,0,null,0)) ;
				Tas.insert(distances.get(0)); //Ajout dans le tas
				tabNode.add(node);
			}
			else
			{
				distances.add(new Label(node,Double.POSITIVE_INFINITY,null,0)) ;
				tabNode.add(node);
			}		
		}
		
        // --------------------iterations (poly1 page 89)-----------------
		while (nbSommetsMarques!=nbNodes) 
		{
			x = Tas.deleteMin();
			distances.get(distances.indexOf(x)).setMarquage(1); 
			nbSommetsMarques++;
			while (x.getNoeud().iterator().hasNext() )
			{
				// On récupère un noeud successeur de x
				Arc arcxy = x.getNoeud().iterator().next();
				Node noeudy = arcxy.getDestination();
				notifyNodeReached(noeudy);
				// On récupère le label correspondant au noeud y
				Label y = distances.get(tabNode.indexOf(noeudy));
				
	
				// Si le noeud n'est pas marqué
				if (y.getMarquage()==0)
				{
					if (y.getCout()>x.getCout()+arcxy.getLength())
					{							
						distances.get(tabNode.indexOf(noeudy)).setCout(x.getCout()+arcxy.getLength());
						try
						{
							Tas.remove(y);					
						} catch (ElementNotFoundException e)
						{
							System.out.println("Il n'était pas dans le tas, on l'ajoute");
						}
						finally
						{
							y = distances.get(tabNode.indexOf(noeudy));
							Tas.insert(y);
						}
					}
				}
					
			}
		}

			
        // on crée la liste de node de de la solution
		Path chemin = new Path(graph);
		ArrayList<Node> listeNode = new ArrayList<Node>();
        
		// On remonte dans le tableau destinations à partir de la fin
		Node fin = data.getDestination();
		// tant que le precedent de fin n'est pas "origine"
		while (!fin.equals(data.getOrigin()))
		{
			listeNode.add(fin);
			fin = distances.get(tabNode.indexOf(fin)).getPrecedent();
		}
		// on retourne la liste pour avoir le bon sens
		Collections.reverse(listeNode);
		
		// on crée le chemin
		chemin=Path.createShortestPathFromNodes(graph,listeNode);
		
		// on crée la solution
		solution = new ShortestPathSolution(data,Status.OPTIMAL,chemin);
        return solution;
    }

}
