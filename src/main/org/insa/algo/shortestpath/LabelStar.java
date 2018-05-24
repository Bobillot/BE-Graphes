package org.insa.algo.shortestpath;

import org.insa.graph.Node;

public class LabelStar extends Label {
	
	private double coutEstime;

	// constructeur
	public LabelStar (Node noeud, double cout, Node precedent, int marquage,int coutEstime)
	{
		super(noeud,cout,precedent,marquage);
		this.coutEstime=coutEstime;
	}
	//getters
	public double getTotalCost()
	{
		return this.getCout()+this.coutEstime;
	}
	public double getCoutEstime()
	{
		return this.coutEstime;
	}
	
	//setters
	public void setCoutEstime(double x)
	{
		this.coutEstime=x;
	}
	
	// fonction de comparaison retourne (this-autre)
	public int compareTo(LabelStar autre)
	{
		// this < autre
		if(this.getTotalCost()-autre.getTotalCost()<0) 
		{
			return -1 ;
		}
		// this > autre
		else if(this.getTotalCost()-autre.getTotalCost()>0)
		{
			return 1 ;
		}
		// si ils sont égaux, on compare les couts estimés
		else
		{
			// this < autre
			if (this.getCoutEstime() - autre.getCoutEstime() < 0)
			{
				return -1 ;
			}
			// this > autre
			if (this.getCoutEstime() - autre.getCoutEstime() > 0)
			{
				return 1 ;
			}
			// This = autre
			else
			{
				return 0 ;
			}
		}
	}
	

}
