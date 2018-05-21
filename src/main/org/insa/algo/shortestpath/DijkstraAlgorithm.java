package org.insa.algo.shortestpath;

import java.util.Arrays;
import java.util.Collections;
import java.util.*;

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
		int destinationAtteinte=0;
		int nbNodes = graph.size() ;
        
        // -------------------------A FAIRE : ---------------------------------------------------------------
		
		// Changer l'arraylist distances par un tableau simple tab [] de la taille du NbNodes
		// Supprimer tabNode
		// Associer l'index à l'id du Node (tab[Label.getPrecedent().getID()]= Label)
		// bonus : Ne pas initialiser tout le tab mais au fur et à mesure
		
		// --------------------------------------------------------------------------------------------------
		
        // creation du tableau de Label et ajout du premier �l�ment(origine)
		Label distances[]=new Label[nbNodes];
		distances[data.getOrigin().getId()]=new Label(data.getOrigin(),0,null,0);
		Tas.insert(distances[data.getOrigin().getId()]);
        // --------------------iterations (poly1 page 89)-----------------
		while (destinationAtteinte==0)
		{
			// on verifie si le tas n'est pas vide càd si la destination est atteignable
			if (Tas.isEmpty())
			{
				System.out.println("Aucun chemin n'existe entre ces points\n");
				solution = new ShortestPathSolution(data, Status.INFEASIBLE);
				return solution;
			}
			
			// variables utiles
			x = Tas.deleteMin();
			distances[x.getNoeud().getId()].setMarquage(1);// marquage du noeud actuel
			notifyNodeMarked(x.getNoeud());
			Arc arcxy;
			Node noeudy;
			Label y;
			Iterator<Arc> it = x.getNoeud().iterator();
			
			// tant qu'il exite un arc atteignable ou que le chemin n'est pas trouvé
			while (it.hasNext() && destinationAtteinte==0)
			{
				// On récupère un noeud successeur de x
				arcxy = it.next();
				noeudy = arcxy.getDestination();
				notifyNodeReached(noeudy);
				
				if(distances[noeudy.getId()]==null)
				{
					distances[noeudy.getId()]=new Label(noeudy,Double.POSITIVE_INFINITY,null,0);
				}
				
				// On récupère le label correspondant au noeud y
				y = distances[noeudy.getId()];
				
	
				// Si le noeud n'est pas marqué
				if (y.getMarquage()==0)
				{
					// si le coût du chemin est inferieur ET que le chemin est autorisé
					if (y.getCout()>x.getCout()+data.getCost(arcxy) && data.isAllowed(arcxy))
					{	
						// On actualise le coût dans le tableau de label
						distances[y.getNoeud().getId()].setCout(x.getCout()+data.getCost(arcxy));
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
