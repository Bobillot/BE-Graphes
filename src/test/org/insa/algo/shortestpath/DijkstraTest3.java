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
	    
	    // affichage des résultats
	    System.out.println("CAS 1")  ;
	    System.out.println("de A à B");
	    System.out.println(solutionDijkstraAB.getPath().getLength());
	    System.out.println("De B à C");
	    System.out.println(solutionDijkstraBC.getPath().getLength());
	    System.out.println("De C à A");
	    System.out.println(solutionDijkstraCA.getPath().getLength());
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
	       
	        // Chemin Aller
	        
		    // création du data étudié (Graph + chemin + arcinspector) sur le segment AB
		    ShortestPathData dataAB = new ShortestPathData(graph, graph.get(A), graph.get(B) ,ArcInspectorFactory.getAllFilters().get(0));
		   
		    // appel de Dijkstra sur le segment AB
		    ShortestPathSolution solutionDijkstraAB = new DijkstraAlgorithm(dataAB).doRun() ;
		    
		    // Comparaison des deux solutions
		    assertTrue(!solutionDijkstraAB.isFeasible());
		    
		    // chemin Retour
		    
		    // création du data étudié (Graph + chemin + arcinspector) sur le segment BA
		    ShortestPathData dataBA = new ShortestPathData(graph, graph.get(B), graph.get(A) ,ArcInspectorFactory.getAllFilters().get(0));
		   
		    // appel de Dijkstra sur le segment BA
		    ShortestPathSolution solutionDijkstraBA = new DijkstraAlgorithm(dataBA).doRun() ;
		    
		    // Comparaison des deux solutions
		    assertTrue(!solutionDijkstraBA.isFeasible()) ;
		    
		    
		    
    }
	  
	    @Test
	    public void testDijkstra3() throws Exception
	    {
	    
	    // -------------- CAS 3 ----------------------------
	    // Trajet aller et retour, en voiture, carte Bretagne, entre Saint Malo et Plougastel Daoulas
	    
	    int A=18100,B=162869;
        // Notre graphe de test
    	Graph graph;
        
        // Recuperation de la carte
        String mapName = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bretagne.mapgr";
    //    String mapName = "C:/Users/Lo�ca Marotte/Documents/Cours/3A/Cours/S2/BE-Graphes/bretagne.mapgr" ;
        
     // On met la carte dans notre variable graph
        GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
        graph=reader.read();
   
	    // TEST EN DISTANCE
        
	    // création du data étudié (Graph + chemin + arcinspector) sur le segment AB
	    ShortestPathData dataAB = new ShortestPathData(graph, graph.get(A), graph.get(B) ,ArcInspectorFactory.getAllFilters().get(1));
	    
	    // appel de Dijkstra sur le segment AB
	    ShortestPathSolution solutionDijkstraAB = new DijkstraAlgorithm(dataAB).doRun() ;
	    
	    // création du data étudié (Graph + chemin + arcinspector) sur le segment BA
	    ShortestPathData dataBA = new ShortestPathData(graph, graph.get(B), graph.get(A) ,ArcInspectorFactory.getAllFilters().get(1));
	    
	    // appel de Dijkstra sur le segment BA
	    ShortestPathSolution solutionDijkstraBA = new DijkstraAlgorithm(dataBA).doRun() ;
	    
	    // Comparaison des deux solutions
	    assertEquals(solutionDijkstraAB.getPath().getLength(),solutionDijkstraBA.getPath().getLength(),0.01*solutionDijkstraAB.getPath().getLength());
	    
	    // affichage des résultats
	    System.out.println("CAS 3")  ;
	    System.out.println("En distance")  ;
	    System.out.println("de A à B");
	    System.out.println(solutionDijkstraAB.getPath().getLength());
	    System.out.println("De B à A");
	    System.out.println(solutionDijkstraBA.getPath().getLength());	 
	    

	    // TEST EN TEMPS
	    
	    // création du data étudié (Graph + chemin + arcinspector) sur le segment AB
	    ShortestPathData dataAB2 = new ShortestPathData(graph, graph.get(A), graph.get(B) ,ArcInspectorFactory.getAllFilters().get(2));
	    
	    // appel de Dijkstra sur le segment AB
	    ShortestPathSolution solutionDijkstraAB2 = new DijkstraAlgorithm(dataAB2).doRun() ;
	    
	    // création du data étudié (Graph + chemin + arcinspector) sur le segment BA
	    ShortestPathData dataBA2 = new ShortestPathData(graph, graph.get(B), graph.get(A) ,ArcInspectorFactory.getAllFilters().get(2));
	    
	    // appel de Dijkstra sur le segment BA
	    ShortestPathSolution solutionDijkstraBA2 = new DijkstraAlgorithm(dataBA2).doRun() ;
	    
	    // Comparaison des deux solutions
	    assertEquals(solutionDijkstraAB2.getPath().getMinimumTravelTime(),solutionDijkstraBA2.getPath().getMinimumTravelTime(),0.01*solutionDijkstraAB2.getPath().getMinimumTravelTime());

	    // affichage des résultats
	    System.out.println("En temps")  ;
	    System.out.println("de A à B");
	    System.out.println(solutionDijkstraAB2.getPath().getMinimumTravelTime());
	    System.out.println("De B à A");
	    System.out.println(solutionDijkstraBA2.getPath().getMinimumTravelTime());
	    
	    }
	    
	   
}
