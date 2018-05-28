package org.insa.algo.shortestpath;

import static org.junit.Assert.*;

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

public class DijkstraTest3 {

	
    // -------------- CAS 1 -----------------------------------------
    // Inegalite triangulaire (AB<BC+CA), tous chemins , carte Bordeaux A=8635 B=2450 C=6546
    @Test
    public void testDijkstra1() throws Exception
    {	
    	// points
    	int A=8635,B=2450,C=6546;
        // Notre graphe de test
    	Graph graph;
        
        // Recuperation de la carte
        String mapName = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bordeaux.mapgr";
    //	String mapName = "C:/Users/Lo�ca Marotte/Documents/Cours/3A/Cours/S2/BE-Graphes/bordeaux.mapgr";
        // On met la carte dans notre variable graph
        GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
        graph=reader.read();
       
	    // création du data étudié (Graph + chemin + arcinspector) sur le segment AB
	    ShortestPathData dataAB = new ShortestPathData(graph, graph.get(A), graph.get(B) ,ArcInspectorFactory.getAllFilters().get(0));
	   
	    // appel de Dijkstra sur le segment AB
	    ShortestPathSolution solutionDijkstraAB = new DijkstraAlgorithm(dataAB).doRun() ;
	    
	    // création du data étudié (Graph + chemin + arcinspector) sur le segment BC
	    ShortestPathData dataBC = new ShortestPathData(graph, graph.get(B), graph.get(C) ,ArcInspectorFactory.getAllFilters().get(0));
	   
	    // appel de Dijkstra sur le segment BC
	    ShortestPathSolution solutionDijkstraBC = new DijkstraAlgorithm(dataBC).doRun() ;
	    
	    // création du data étudié (Graph + chemin + arcinspector) sur le segment CA
	    ShortestPathData dataCA = new ShortestPathData(graph, graph.get(C), graph.get(A) ,ArcInspectorFactory.getAllFilters().get(0));
	   
	    // appel de Dijkstra sur le segment CA
	    ShortestPathSolution solutionDijkstraCA = new DijkstraAlgorithm(dataCA).doRun() ;
	    
	    
	   
	    // Comparaison des deux solutions
	    assertTrue((solutionDijkstraAB.getPath().getLength())<(solutionDijkstraBC.getPath().getLength()+solutionDijkstraCA.getPath().getLength()));
	    
    }
	    
	    // -------------- CAS 2 -----------------------------------------
	    // Chemin impossible, tous chemins, carte Haiti entre l'Ile de La Gonâve et Port-au-Prince
	    @Test
	    public void testDijkstra2() throws Exception
	    {	
	    	int A=94801,B=54335;
	        // Notre graphe de test
	    	Graph graph;
	        
	        // Recuperation de la carte
	        String mapName = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haiti-and-domrep.mapgr";
	    //    String mapName = "C:/Users/Lo�ca Marotte/Documents/Cours/3A/Cours/S2/BE-Graphes/haiti-and-domrep.mapgr" ;
	        // On met la carte dans notre variable graph
	        GraphReader reader = new BinaryGraphReader(
	                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	        graph=reader.read();
	       
		    // création du data étudié (Graph + chemin + arcinspector) sur le segment AB
		    ShortestPathData dataAB = new ShortestPathData(graph, graph.get(A), graph.get(B) ,ArcInspectorFactory.getAllFilters().get(0));
		   
		    // appel de Dijkstra sur le segment AB
		    ShortestPathSolution solutionDijkstraAB = new DijkstraAlgorithm(dataAB).doRun() ;
		    
		    // Comparaison des deux solutions
		    assertTrue(!solutionDijkstraAB.isFeasible());
		    
    }
	    
	    
}
