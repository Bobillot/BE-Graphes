package org.insa.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class representing a path between nodes in a graph.
 * 
 * A path is represented as a list of {@link Arc} and not a list of {@link Node}
 * due to the multigraph nature of the considered graphs.
 *
 */
public class Path {

    /**
     * Create a new path that goes through the given list of nodes (in order),
     * choosing the fastest route if multiple are available.
     * 
     * @param graph Graph containing the nodes in the list.
     * @param nodes List of nodes to build the path.
     * 
     * @return A path that goes through the given list of nodes.
     * 
     * @throws IllegalArgumentException If the list of nodes is not valid, i.e. two
     *         consecutive nodes in the list are not connected in the graph.
     * 
     * 
     */
    public static Path createFastestPathFromNodes(Graph graph, List<Node> nodes)
            throws IllegalArgumentException {
        List<Arc> arcs = new ArrayList<Arc>();
        
        //Si un seul node dans la liste, appeler un constructeur particulier qui ne prend qu'un node en second param�tre.
        if(nodes.size()==1)
        {
        	return new Path(graph,nodes.get(0));
        }
        
        // Pour chaque node de la liste pass�e en param�tre (sauf le dernier, qui n'aura pas de suivant)
        for (int i=0; i<nodes.size()-1 ; i++)
        {
        	double vitessemeilleur = 1000000.0;
        	int ajoute=0;
        	//Examiner ses successeurs
        	while (nodes.get(i).iterator().hasNext())
        	{
        		Arc arcetudie = nodes.get(i).iterator().next();
        		//Si le successeur va sur le bon "node suivant"
        		if(arcetudie.getDestination()==nodes.get(i+1))
        		{
        			// On regarde s'il est plus rapide qu'un autre d�ja trouv�
        			if (arcetudie.getMinimumTravelTime()<vitessemeilleur)
        			{
        				
        				if(ajoute==1)
        				{
        					arcs.remove(i);
        					arcs.add(arcetudie);
        					vitessemeilleur=arcetudie.getMinimumTravelTime();
        				}
        				else
        				{
        					arcs.add(arcetudie);
        					ajoute=1;
        					vitessemeilleur=arcetudie.getMinimumTravelTime();
        				}
        			}
        		}
        	}
        	// Si l'arc m�moris� est encore -1, alors aucun arc ne va sur le node suivant --> exception.
        	if (ajoute==0)
        	{
        		throw new IllegalArgumentException("La liste de nodes n'est pas valide");
        	}
        }
        return new Path(graph, arcs);
    }

    /**
     * Create a new path that goes through the given list of nodes (in order),
     * choosing the shortest route if multiple are available.
     * 
     * @param graph Graph containing the nodes in the list.
     * @param nodes List of nodes to build the path.
     * 
     * @return A path that goes through the given list of nodes.
     * 
     * @throws IllegalArgumentException If the list of nodes is not valid, i.e. two
     *         consecutive nodes in the list are not connected in the graph.
     */
    public static Path createShortestPathFromNodes(Graph graph, List<Node> nodes)
            throws IllegalArgumentException {
        List<Arc> arcs = new ArrayList<Arc>();
      //Si un seul node dans la liste, appeler un constructeur particulier qui ne prend qu'un node en second param�tre.
        if(nodes.size()==1)
        {
        	return new Path(graph,nodes.get(0));
        }
        
        // Pour chaque node de la liste pass�e en param�tre (sauf le dernier, qui n'aura pas de suivant)
        for (int i=0; i<nodes.size()-1 ; i++)
        {
        	float longueurmeilleur = 1000000000;
        	int ajoute=0;			//On retient si on a deja ajout� un arc pour ce node
        	//Examiner ses successeurs
        	while (nodes.get(i).iterator().hasNext())
        	{
        		Arc arcetudie = nodes.get(i).iterator().next();
        		//Si le successeur va sur le bon "node suivant"
        		if(arcetudie.getDestination()==nodes.get(i+1))
        		{
        			// On regarde s'il est plus rapide qu'un autre d�ja trouv�
        			if (arcetudie.getLength()<longueurmeilleur)
        			{
        				
        				if(ajoute==1) //Si on a d�ja ajout� un arc mais pas le plus rapide, on le vire pour le remplacer
        				{
        					arcs.remove(i);
        					arcs.add(arcetudie);
        					longueurmeilleur=arcetudie.getLength();
        				}
        				else		//Sinon on ajoute juste celui qui r�pond � nos crit�res
        				{
        					arcs.add(arcetudie);
        					ajoute=1;
        					longueurmeilleur=arcetudie.getLength();
        				}
        			}
        		}
        	}
        	// Si on a rien ajout�, alors la liste de node en param�tre c'est de la grosse merde
        	if (ajoute==0)
        	{
        		throw new IllegalArgumentException("La liste de nodes n'est pas valide");
        	}
        }
        return new Path(graph, arcs);
    }


    /**
     * Concatenate the given paths.
     * 
     * @param paths Array of paths to concatenate.
     * 
     * @return Concatenated path.
     * 
     * @throws IllegalArgumentException if the paths cannot be concatenated (IDs of
     *         map do not match, or the end of a path is not the beginning of the
     *         next).
     
     */
    public static Path concatenate(Path... paths) throws IllegalArgumentException {
        if (paths.length == 0) {
            throw new IllegalArgumentException("Cannot concatenate an empty list of paths.");
        }
        final String mapId = paths[0].getGraph().getMapId();
        for (int i = 1; i < paths.length; ++i) {
            if (!paths[i].getGraph().getMapId().equals(mapId)) {
                throw new IllegalArgumentException(
                        "Cannot concatenate paths from different graphs.");
            }
        }
        ArrayList<Arc> arcs = new ArrayList<>();
        for (Path path: paths) {
            arcs.addAll(path.getArcs());
        }
        Path path = new Path(paths[0].getGraph(), arcs);
        if (!path.isValid()) {
            throw new IllegalArgumentException(
                    "Cannot concatenate paths that do not form a single path.");
        }
        return path;
    }

    // Graph containing this path.
    private final Graph graph;

    // Origin of the path
    private final Node origin;

    // List of arcs in this path.
    private final List<Arc> arcs;

    /**
     * Create an empty path corresponding to the given graph.
     * 
     * @param graph Graph containing the path.
     */
    public Path(Graph graph) {
        this.graph = graph;
        this.origin = null;
        this.arcs = new ArrayList<>();
    }

    /**
     * Create a new path containing a single node.
     * 
     * @param graph Graph containing the path.
     * @param node Single node of the path.
     */
    public Path(Graph graph, Node node) {
        this.graph = graph;
        this.origin = node;
        this.arcs = new ArrayList<>();
    }

    /**
     * Create a new path with the given list of arcs.
     * 
     * @param graph Graph containing the path.
     * @param arcs Arcs to construct the path.
     */
    public Path(Graph graph, List<Arc> arcs) {
        this.graph = graph;
        this.arcs = arcs;
        this.origin = arcs.size() > 0 ? arcs.get(0).getOrigin() : null;
    }

    /**
     * @return Graph containing the path.
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * @return First node of the path.
     */
    public Node getOrigin() {
        return origin;
    }

    /**
     * @return Last node of the path.
     */
    public Node getDestination() {
        return arcs.get(arcs.size() - 1).getDestination();
    }

    /**
     * @return List of arcs in the path.
     */
    public List<Arc> getArcs() {
        return Collections.unmodifiableList(arcs);
    }

    /**
     * Check if this path is empty (it does not contain any node).
     * 
     * @return true if this path is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.origin == null;
    }

    /**
     * Get the number of <b>nodes</b> in this path.
     * 
     * @return Number of nodes in this path.
     */
    public int size() {
        return isEmpty() ? 0 : 1 + this.arcs.size();
    }

    /**
     * Check if this path is valid.
     * 
     * A path is valid if any of the following is true:
     * <ul>
     * <li>it is empty;</li>
     * <li>it contains a single node (without arcs);</li>
     * <li>the first arc has for origin the origin of the path and, for two
     * consecutive arcs, the destination of the first one is the origin of the
     * second one.</li>
     * </ul>
     * 
     * @return true if the path is valid, false otherwise.
     * 
     */
    public boolean isValid() {
    	// Un chemin vide est valide
    	if(this.getArcs().size()==0)
    	{
    		return true;
    	}
    	// Un chemin constitu� d'un seul node est valide
    	else if (this.getArcs().size()==1)
    	{
    		return true;														
    	}
    	// L'origine du chemin doit �tre l'ant�c�dent du premier arc
    	if(this.getOrigin()==this.getArcs().get(0).getOrigin())
    	{
    	   	// La dest du dernier arc doit etre la dest du chemin
        	if(this.getDestination()==this.getArcs().get(this.getArcs().size()-1).getDestination())
        	{
            	// Les nodes "du milieu" doivent �tre tous li�s 
            	for (int i=0; i<this.getArcs().size()-2; i++)
            	{
            		if(this.getArcs().get(i).getDestination()!=this.getArcs().get(i+1).getOrigin())
            		{
            			return false;
            		}
            	// On v�rifie que a est �gal au nombre de liaisons test�es (auquel cas tout est bon)
            	}
            	return true ;
        	}
    	}

        return false;
    }

    /**
     * Compute the length of this path (in meters).
     * 
     * @return Total length of the path (in meters).
     * 
     */
    public float getLength() {
    	
    	// on parcourt la liste pour calculer la taille totale
    	float taille = 0 ;
    	for (int i=0; i<this.getArcs().size(); i++)
    	{
    		taille += this.getArcs().get(i).getLength() ;
    	}
    	
        return taille;
    }

    /**
     * Compute the time required to travel this path if moving at the given speed.
     * 
     * @param speed Speed to compute the travel time.
     * 
     * @return Time (in seconds) required to travel this path at the given speed (in
     *         kilometers-per-hour).
     * 
     *
     */
    public double getTravelTime(double speed) {
        
    	// on parcourt la liste d'arc pour sommer les traveltime
    	double time = 0 ;
    	for (int i=0 ; i<this.getArcs().size(); i++)
    	{
    		time += this.getArcs().get(i).getTravelTime(speed) ;
    	}
    	
    	
        return time ;
    }

    /**
     * Compute the time to travel this path if moving at the maximum allowed speed
     * on every arc.
     * 
     * @return Minimum travel time to travel this path (in seconds).
     * 
     */
    public double getMinimumTravelTime() {
    	// on parcourt la liste d'arc pour sommer les minimumtraveltime
    	double time = 0 ;
    	for (int i=0 ; i<this.getArcs().size(); i++)
    	{
    		time += this.getArcs().get(i).getMinimumTravelTime() ;
    	}
    	
    	
        return time ;
    }

}
