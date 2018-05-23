package org.insa.algo.shortestpath;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.Arrays;

import org.insa.algo.ArcInspectorFactory;
import org.insa.graph.Graph;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;
import org.insa.graphics.drawing.Drawing;
import org.junit.Test;

public class DijkstraTest2 
{
	
    
    // -------------- CAS 1 -----------------------------------------
    // carte haute garonne, Tous chemins, en distance depuis le capitole de toulouse jusqu'a Saint Gaudens
    @Test
    public void testDijkstra1() throws Exception
    {	
        // Notre graphe de test
    	Graph graph;
        
        // Recuperation de la carte
        String mapName = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haute-garonne.mapgr";

        // On met la carte dans notre variable graph
        GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
        graph=reader.read();
       
	    // création du data étudié (Graph + chemin + arcinspector)
	    ShortestPathData data = new ShortestPathData(graph, graph.get(2613), graph.get(47851) ,ArcInspectorFactory.getAllFilters().get(0));
	   
	    // appel de Bellman
	    ShortestPathSolution solutionBellman = new BellmanFordAlgorithm(data).doRun() ;
	    // appel de Dijkstra
	    ShortestPathSolution solutionDijkstra = new DijkstraAlgorithm(data).doRun() ;
	   
	    // Comparaison des deux solutions
	    assertEquals(solutionDijkstra.getPath().getLength(),solutionBellman.getPath().getLength(),1e-6);
    }
    
    // -------------- CAS 2 -----------------------------------------
    // carte toulouse, Voiture, en distance depuis le bikini jusqu'a insa (gei) === Chemin impossible
    @Test
    public void testDijkstra2() throws Exception
    {
        // Notre graphe de test
    	Graph graph;
        
        // Recuperation de la carte
         String mapName = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/toulouse.mapgr";

        // On met la carte dans notre variable graph
         GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
        graph=reader.read();
       
	    // création du data étudié (Graph + chemin + arcinspector)
        ShortestPathData data = new ShortestPathData(graph, graph.get(37833), graph.get(1066) ,ArcInspectorFactory.getAllFilters().get(1));
	   
	    // appel de Bellman
        ShortestPathSolution solutionBellman = new BellmanFordAlgorithm(data).doRun() ;
	    // appel de Dijkstra
        ShortestPathSolution solutionDijkstra = new DijkstraAlgorithm(data).doRun() ;
	   
	    // Comparaison des deux solutions
	    assertEquals(solutionDijkstra.isFeasible(),solutionBellman.isFeasible());
   
	     
    }

    // -------------- CAS 3 -----------------------------------------
    // carte Bretagne, Voiture, en temps depuis Plougastel Daoulas jusqu'a Saint Malo
    @Test
    public void testDijkstra3() throws Exception
    {
        // Notre graphe de test
    	Graph graph;
        
        // Recuperation de la carte
         String mapName = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bretagne.mapgr";

        // On met la carte dans notre variable graph
         GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
        graph=reader.read();
       
	    // création du data étudié (Graph + chemin + arcinspector)
        ShortestPathData data = new ShortestPathData(graph, graph.get(18159), graph.get(163184) ,ArcInspectorFactory.getAllFilters().get(2));
	   
	    // appel de Bellman
        ShortestPathSolution solutionBellman = new BellmanFordAlgorithm(data).doRun() ;
	    // appel de Dijkstra
        ShortestPathSolution solutionDijkstra = new DijkstraAlgorithm(data).doRun() ;
	   
	    // Comparaison des deux solutions
	    assertEquals(solutionDijkstra.getPath().getLength(),solutionBellman.getPath().getLength(),1e-6);
   
	     
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
