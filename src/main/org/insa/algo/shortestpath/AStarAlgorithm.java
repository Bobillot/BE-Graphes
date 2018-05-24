package org.insa.algo.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.BinaryHeap;
import org.insa.algo.utils.ElementNotFoundException;
import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.Node;
import org.insa.graph.Path;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    @Override
    protected ShortestPathSolution doRun() 
    {
    	// --------------------initialisation --------------------
    	// recup le graphe
        ShortestPathData data = getInputData();
		Graph graph = data.getGraph();		
		BinaryHeap<LabelStar> Tas = new BinaryHeap<LabelStar>() ;
		LabelStar x;
		
		// structure de la solution
        ShortestPathSolution solution = null;
        
        // variables utiles
		int destinationAtteinte=0;
		int nbNodes = graph.size() ;
        
		
        // creation du tableau de LabelStar et ajout du premier �l�ment(origine)
		LabelStar distances[]=new LabelStar[nbNodes];
		distances[data.getOrigin().getId()]=new LabelStar(data.getOrigin(),0,null,0);
		Tas.insert(distances[data.getOrigin().getId()]);
		
		
        // --------------------iterations (poly1 page 89)-----------------
		// Tant que le tas n'est pas vide, ie que tous les nodes atteignables ne sont pas marqués
		while (!Tas.isEmpty())
		{			
			// variables utiles
			x = Tas.deleteMin();
			distances[x.getNoeud().getId()].setMarquage(1);// marquage du noeud actuel
			notifyNodeMarked(x.getNoeud());
			Arc arcxy;
			Node noeudy;
			LabelStar y;
			Iterator<Arc> it = x.getNoeud().iterator();
			
			// tant qu'il existe un arc atteignable
			while (it.hasNext())
			{
				// On récupère un noeud successeur de x
				arcxy = it.next();
				noeudy = arcxy.getDestination();
				notifyNodeReached(noeudy);
				
				// si le noeud n'a jamais été initialisé dans le tab de LabelStar, on le fait
				if(distances[noeudy.getId()]==null)
				{
					distances[noeudy.getId()]=new LabelStar(noeudy,Double.POSITIVE_INFINITY,null,0);
				}
				
				// On récupère le LabelStar correspondant au noeud y
				y = distances[noeudy.getId()];
				
	
				// Si le noeud n'est pas marqué
				if (y.getMarquage()==0)
				{
					// si le coût du chemin est inferieur ET que le chemin est autorisé
					if (y.getTotalCost()>x.getTotalCost()+data.getCost(arcxy) && data.isAllowed(arcxy))
					{	
						// On actualise le coût dans le tableau de LabelStar
						distances[y.getNoeud().getId()].setCout(x.getTotalCost()+data.getCost(arcxy));
						distances[y.getNoeud().getId()].setPrecedent(x.getNoeud());
						
						// on regarde si l'element est déjà dans le tas avant de l'ajouter
						try
						{
							Tas.remove(y);					
						} catch (ElementNotFoundException e)
						{
						}
						finally
						{
							y = distances[y.getNoeud().getId()];
							Tas.insert(y);
						}
					}
				}
				// si le chemin est atteint, on arrete la boucle
				if (y.getNoeud().compareTo(data.getDestination())==0)
				{
					destinationAtteinte=1;
				}
					
			}
		}

		// on verifie si la destination a été atteinte
		if (destinationAtteinte==0)
		{
			//System.out.println("Aucun chemin n'existe entre ces points\n");
			solution = new ShortestPathSolution(data, Status.INFEASIBLE);
			return solution;
		}
		else
		{	
	        // on crée la liste de node de de la solution
			Path chemin = new Path(graph);
			ArrayList<Node> listeNode = new ArrayList<Node>();
	        
			// On remonte dans le tableau destinations à partir de la fin
			Node fin = data.getDestination();
			// tant que le precedent de fin n'est pas "origine"
			while (fin.compareTo(data.getOrigin())!=0)
			{
				listeNode.add(fin);
				fin = distances[fin.getId()].getPrecedent();
			}
			listeNode.add(fin);
			// on retourne la liste pour avoir le bon sens
			Collections.reverse(listeNode);
			
			// on crée le chemin
			chemin=Path.createShortestPathFromNodes(graph,listeNode);
			
			// on crée la solution
			solution = new ShortestPathSolution(data,Status.OPTIMAL,chemin);
	        return solution;
		}
    }

}
