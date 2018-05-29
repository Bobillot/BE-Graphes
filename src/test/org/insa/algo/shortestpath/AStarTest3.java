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

public class AStarTest3 {

	
    // -------------- CAS 1 -----------------------------------------
    // Inegalite triangulaire (AB<BC+CA), tous chemins , carte Bordeaux A=8635 B=2450 C=6546
    @Test
    public void testAStar1() throws Exception
    {	
    	// points
    	int A=8635,B=2450,C=6546;
        // Notre graphe de test
    	Graph graph;
        
        // Recuperation de la carte
        //String mapName = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bordeaux.mapgr";
    	String mapName = "C:/Users/Lo�ca Marotte/Documents/Cours/3A/Cours/S2/BE-Graphes/bordeaux.mapgr";

        // On met la carte dans notre variable graph
        GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
        graph=reader.read();
       
	    // création du data étudié (Graph + chemin + arcinspector) sur le segment AB
	    ShortestPathData dataAB = new ShortestPathData(graph, graph.get(A), graph.get(B) ,ArcInspectorFactory.getAllFilters().get(0));
	   
	    // appel de AStar sur le segment AB
	    ShortestPathSolution solutionAStarAB = new AStarAlgorithm(dataAB).doRun() ;
	    
	    // création du data étudié (Graph + chemin + arcinspector) sur le segment BC
	    ShortestPathData dataBC = new ShortestPathData(graph, graph.get(B), graph.get(C) ,ArcInspectorFactory.getAllFilters().get(0));
	   
	    // appel de AStar sur le segment BC
	    ShortestPathSolution solutionAStarBC = new AStarAlgorithm(dataBC).doRun() ;
	    
	    // création du data étudié (Graph + chemin + arcinspector) sur le segment CA
	    ShortestPathData dataCA = new ShortestPathData(graph, graph.get(C), graph.get(A) ,ArcInspectorFactory.getAllFilters().get(0));
	   
	    // appel de AStar sur le segment CA
	    ShortestPathSolution solutionAStarCA = new AStarAlgorithm(dataCA).doRun() ; 
	   
	    // Comparaison des deux solutions
	    assertTrue((solutionAStarAB.getPath().getLength())<(solutionAStarBC.getPath().getLength()+solutionAStarCA.getPath().getLength()));
	    
	    // affichage des résultats
	    System.out.println("CAS 1")  ;
	    System.out.println("de A à B");
	    System.out.println(solutionAStarAB.getPath().getLength());
	    System.out.println("De B à C");
	    System.out.println(solutionAStarBC.getPath().getLength());
	    System.out.println("De C à A");
	    System.out.println(solutionAStarCA.getPath().getLength());
    }
	    
	    // -------------- CAS 2 -----------------------------------------
	    // Chemin impossible, tous chemins, carte Haiti entre l'Ile de La Gonâve et Port-au-Prince
	    @Test
	    public void testAStar2() throws Exception
	    {	
	    	int A=94801,B=54335;
	        // Notre graphe de test
	    	Graph graph;
	        
	        // Recuperation de la carte
	        //String mapName = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haiti-and-domrep.mapgr";
	    	String mapName = "C:/Users/Lo�ca Marotte/Documents/Cours/3A/Cours/S2/BE-Graphes/haiti-and-domrep.mapgr" ;

	        // On met la carte dans notre variable graph
	        GraphReader reader = new BinaryGraphReader(
	                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	        graph=reader.read();
		   
	        // Chemin Aller
	        
		    // création du data étudié (Graph + chemin + arcinspector) sur le segment AB
		    ShortestPathData dataAB = new ShortestPathData(graph, graph.get(A), graph.get(B) ,ArcInspectorFactory.getAllFilters().get(0));
		   
		    // appel de AStar sur le segment AB
		    ShortestPathSolution solutionAStarAB = new AStarAlgorithm(dataAB).doRun() ;
		    
		    // Comparaison des deux solutions
		    assertTrue(!solutionAStarAB.isFeasible());
		    
		    // chemin Retour
		    
		    // création du data étudié (Graph + chemin + arcinspector) sur le segment BA
		    ShortestPathData dataBA = new ShortestPathData(graph, graph.get(B), graph.get(A) ,ArcInspectorFactory.getAllFilters().get(0));
		   
		    // appel de AStar sur le segment BA
		    ShortestPathSolution solutionAStarBA = new AStarAlgorithm(dataBA).doRun() ;
		    
		    // Comparaison des deux solutions
		    assertTrue(!solutionAStarBA.isFeasible()) ;
	    }
	    
	    @Test
	    public void testAStar3() throws Exception
	    {
	    
	    // -------------- CAS 3 ----------------------------
	    // Trajet aller et retour, en voiture, carte Bretagne, entre Saint Malo et Plougastel Daoulas
	    
	    int A=18100,B=162869;
        // Notre graphe de test
    	Graph graph;
        
        // Recuperation de la carte
        //String mapName = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bretagne.mapgr";
        String mapName = "C:/Users/Lo�ca Marotte/Documents/Cours/3A/Cours/S2/BE-Graphes/bretagne.mapgr" ;
        
     // On met la carte dans notre variable graph
        GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
        graph=reader.read();
   
	    // TEST EN DISTANCE
        
	    // création du data étudié (Graph + chemin + arcinspector) sur le segment AB
	    ShortestPathData dataAB = new ShortestPathData(graph, graph.get(A), graph.get(B) ,ArcInspectorFactory.getAllFilters().get(1));
	    
	    // appel de AStar sur le segment AB
	    ShortestPathSolution solutionAStarAB = new AStarAlgorithm(dataAB).doRun() ;
	    
	    // création du data étudié (Graph + chemin + arcinspector) sur le segment BA
	    ShortestPathData dataBA = new ShortestPathData(graph, graph.get(B), graph.get(A) ,ArcInspectorFactory.getAllFilters().get(1));
	    
	    // appel de AStar sur le segment BA
	    ShortestPathSolution solutionAStarBA = new AStarAlgorithm(dataBA).doRun() ;
	    
	    // Comparaison des deux solutions
	    assertEquals(solutionAStarAB.getPath().getLength(),solutionAStarBA.getPath().getLength(),0.01*solutionAStarAB.getPath().getLength());
	    
	    // affichage des résultats
	    System.out.println("CAS 3")  ;
	    System.out.println("En distance")  ;
	    System.out.println("de A à B");
	    System.out.println(solutionAStarAB.getPath().getLength());
	    System.out.println("De B à A");
	    System.out.println(solutionAStarBA.getPath().getLength());	 
	    

	    // TEST EN TEMPS
	    
	    // création du data étudié (Graph + chemin + arcinspector) sur le segment AB
	    ShortestPathData dataAB2 = new ShortestPathData(graph, graph.get(A), graph.get(B) ,ArcInspectorFactory.getAllFilters().get(2));
	    
	    // appel de AStar sur le segment AB
	    ShortestPathSolution solutionAStarAB2 = new AStarAlgorithm(dataAB2).doRun() ;
	    
	    // création du data étudié (Graph + chemin + arcinspector) sur le segment BA
	    ShortestPathData dataBA2 = new ShortestPathData(graph, graph.get(B), graph.get(A) ,ArcInspectorFactory.getAllFilters().get(2));
	    
	    // appel de AStar sur le segment BA
	    ShortestPathSolution solutionAStarBA2 = new AStarAlgorithm(dataBA2).doRun() ;
	    
	    // Comparaison des deux solutions
	    assertEquals(solutionAStarAB2.getPath().getMinimumTravelTime(),solutionAStarBA2.getPath().getMinimumTravelTime(),0.01*solutionAStarAB2.getPath().getMinimumTravelTime());

	    // affichage des résultats
	    System.out.println("En temps")  ;
	    System.out.println("de A à B");
	    System.out.println(solutionAStarAB2.getPath().getMinimumTravelTime());
	    System.out.println("De B à A");
	    System.out.println(solutionAStarBA2.getPath().getMinimumTravelTime());
	    
	    }
	    
	   
		    
		    
    }
	    
	    

