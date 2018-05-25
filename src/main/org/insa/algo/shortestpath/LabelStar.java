package org.insa.algo.shortestpath;

import org.insa.graph.Node;

public class LabelStar extends Label{
	
	private float coutEstime;

	// constructeur
	public LabelStar (Node noeud, float cout, Node precedent, int marquage,float coutEstime)
	{
		super(noeud,cout,precedent,marquage);
		this.coutEstime=coutEstime;
	}
	//getters
	public float getTotalCost()
	{
		return this.getCout()+this.coutEstime;
	}
	public float getCoutEstime()
	{
		return this.coutEstime;
	}
	
	//setters
	public void setCoutEstime(float x)
	{
		this.coutEstime=x;
	}
	
	
	

}
