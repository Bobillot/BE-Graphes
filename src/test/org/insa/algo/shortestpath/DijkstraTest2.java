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
    // carte haute garonne, Tous chemins, en distance depuis le capitole de toulouse jusqu'a Ramonville
    @Test
    public void testDijkstra1() throws Exception
    {	
        // Notre graphe de test
    	Graph graph;
        
        // Recuperation de la carte
        String mapName = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haute-garonne.mapgr";
    	//String mapName = "C:/Users/Lo�ca Marotte/Documents/Cours/3A/Cours/S2/BE-Graphes/haute-garonne.mapgr";
   	
        // On met la carte dans notre variable graph
        GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
        graph=reader.read();
       
	    // création du data étudié (Graph + chemin + arcinspector)
	    ShortestPathData data = new ShortestPathData(graph, graph.get(8094), graph.get(105571) ,ArcInspectorFactory.getAllFilters().get(0));
	   
	    // appel de Bellman
	    ShortestPathSolution solutionBellman = new BellmanFordAlgorithm(data).doRun() ;
	    // appel de Dijkstra
	    ShortestPathSolution solutionDijkstra = new DijkstraAlgorithm(data).doRun() ;
	   
	    // Comparaison des deux solutions
	    assertEquals(solutionBellman.getPath().getLength(),solutionDijkstra.getPath().getLength(),0.01*solutionBellman.getPath().getLength());

	    // affichage des résultats
	    System.out.println("CAS 1")  ;
	    System.out.println("Bellman-Ford");
	    System.out.println(solutionBellman.getPath().getLength());
	    System.out.println("Dijkstra");
	    System.out.println(solutionDijkstra.getPath().getLength());	    
    
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
     //	String mapName = "C:/Users/Lo�ca Marotte/Documents/Cours/3A/Cours/S2/BE-Graphes/toulouse.mapgr" ;

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
	    assertEquals(solutionBellman.isFeasible(),solutionDijkstra.isFeasible());
	    
	     
    }

    // -------------- CAS 3 -----------------------------------------
    // carte Guadeloupe, en voiture, en distance de Le Moule à Saint Claude
    @Test
    public void testDijkstra3() throws Exception
    {
        // Notre graphe de test
    	Graph graph;
        
        // Recuperation de la carte
         String mapName = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/guadeloupe.mapgr";
    //	String mapName = "C:/Users/Lo�ca Marotte/Documents/Cours/3A/Cours/S2/BE-Graphes/guadeloupe.mapgr";

        // On met la carte dans notre variable graph
         GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
        graph=reader.read();
       
	    // création du data étudié (Graph + chemin + arcinspector)
        ShortestPathData data = new ShortestPathData(graph, graph.get(111), graph.get(10486) ,ArcInspectorFactory.getAllFilters().get(1));
	   
	    // appel de Bellman
        ShortestPathSolution solutionBellman = new BellmanFordAlgorithm(data).doRun() ;
	    // appel de Dijkstra
        ShortestPathSolution solutionDijkstra = new DijkstraAlgorithm(data).doRun() ;
	   
	    // Comparaison des deux solutions
	    assertEquals(solutionBellman.getPath().getLength(),solutionDijkstra.getPath().getLength(),0.01*solutionBellman.getPath().getLength());
   
	    // affichage des résultats
	    System.out.println("CAS 3")  ;
	    System.out.println("Bellman-Ford");
	    System.out.println(solutionBellman.getPath().getLength());
	    System.out.println("Dijkstra");
	    System.out.println(solutionDijkstra.getPath().getLength());	    
   
    }
    
    // -------------- CAS 4-----------------------------------------
    // carte Paris, chemin le plus rapide à pied, de la Tour Eiffel à Notre Dame
    @Test
    public void testDijkstra4() throws Exception
    {
        // Notre graphe de test
    	Graph graph;
        
        // Recuperation de la carte
         String mapName = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/paris.mapgr";
    //	String mapName ="C:/Users/Lo�ca Marotte/Documents/Cours/3A/Cours/S2/BE-Graphes/paris.mapgr";

        // On met la carte dans notre variable graph
         GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
        graph=reader.read();
       
	    // création du data étudié (Graph + chemin + arcinspector)
        ShortestPathData data = new ShortestPathData(graph, graph.get(1053), graph.get(7301) ,ArcInspectorFactory.getAllFilters().get(4));
	   
	    // appel de Bellman
        ShortestPathSolution solutionBellman = new BellmanFordAlgorithm(data).doRun() ;
	    // appel de Dijkstra
        ShortestPathSolution solutionDijkstra = new DijkstraAlgorithm(data).doRun() ;
	   
	    // Comparaison des deux solutions
	    assertEquals(solutionBellman.getPath().getTravelTime(5),solutionDijkstra.getPath().getTravelTime(5),0.01*solutionBellman.getPath().getTravelTime(5));
	     
	    // affichage des résultats
	    System.out.println("CAS 4")  ;
	    System.out.println("Bellman-Ford");
	    System.out.println(solutionBellman.getPath().getTravelTime(5));
	    System.out.println("Dijkstra");
	    System.out.println(solutionDijkstra.getPath().getTravelTime(5));	    
   
    }
	
	
	
}
