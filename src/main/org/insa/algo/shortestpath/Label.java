package org.insa.algo.shortestpath;
import org.insa.graph.*;

public class Label implements Comparable<Label>
{
	private Node noeud ;
	private double cout ;
	private Node precedent ;
	private int marquage ;
	
	// constructeur
	public Label (Node noeud, double cout, Node precedent, int marquage)
	{
		this.noeud = noeud ;
		this.cout = cout ;
		this.precedent = precedent ;
		this.marquage = marquage ;
	}
	
	// getter
	public Node getNoeud()
	{
		return this.noeud ;
	}
	public double getCout()
	{
		return this.cout ;
	}
	public Node getPrecedent()
	{
		return this.precedent ;
	}
	public int getMarquage()
	{
		return this.marquage ;
	}
	
	// setter
	public void setCout(double cout)
	{
		this.cout = cout ;
	}
	public void setPrecedent(Node noeud)
	{
		this.precedent = noeud ;
	}
	public void setMarquage(int marquage)
	{
		this.marquage = marquage;
	}
	
	// fonction de comparaison retourne (this-autre)
	public int compareTo(Label autre)
	{
		if(this.getCout()-autre.getCout()<0) 
		{
			return -1 ;
		}
		else if(this.getCout()-autre.getCout()>0)
		{
			return 1 ;
		}
		else
		{
			return 0 ;
		}
	}
	
	
}
