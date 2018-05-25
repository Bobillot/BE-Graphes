package org.insa.algo.shortestpath;
import org.insa.graph.*;

public class Label implements Comparable<Label>
{
	private Node noeud ;
	private float cout ;
	private Node precedent ;
	private int marquage ;
	
	// constructeur
	public Label (Node noeud, float cout, Node precedent, int marquage)
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
	public float getCout()
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
	
	public float getTotalCost()
	{
		return this.cout;
	}
	// setter
	public void setCout(float cout)
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
	
	// fonction inutile ici, utile dans labelstar
	public float getCoutEstime()
	{
		return 0 ;
	}
	
	
	// fonction de comparaison retourne (this-autre)
		public int compareTo(Label autre)
		{
			// this < autre
			if(this.getTotalCost()-autre.getTotalCost()<0) 
			{
				return -1 ;
			}
			// this > autre
			else if(this.getTotalCost()-autre.getTotalCost()>0)
			{
				return 1;
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
