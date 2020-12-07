package outils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.Game;
import model.PacmanGame;

public class StrategyAetoile implements Strategy{
	private ArrayList<Noeud> chemin = new ArrayList<StrategyAetoile.Noeud>();
	private ArrayList<Noeud> nourrriture = new ArrayList<StrategyAetoile.Noeud>();
	
	private static class Noeud {
		double x;
		double y;
		double cout;
		double heuristique;
		int direction;
		Noeud parent;
		
		Noeud(double _x, double _y, double _cout, double _heuristique, Noeud _parent, int _direction){
			x = _x;
			y = _y;
			cout = _cout;
			heuristique = _heuristique;
			parent = _parent;
			direction = _direction;
		}
		
		public String afficher() {
			return "("+x+","+y+")";
		}
		
	}
	
	private static Comparator<Noeud> NoeudComparator = new  Comparator<Noeud>() {

		@Override
		public int compare(Noeud arg0, Noeud arg1) {
			if(arg0.heuristique < arg1.heuristique) {
				return -1;
			}else if(arg0.heuristique == arg1.heuristique) {
				return 0;
			}else {
				return 1;
			}
		}
		
	};
	
	
	@Override
	public AgentAction getAction(Agent agt, Game game) {
		Noeud agent = new Noeud((double)agt.getPosition().getX(), (double)agt.getPosition().getY(), 0, 0, null, AgentAction.STOP);
		
		parcoursLargeur(game, agent);
		
		System.out.println("Taille "+nourrriture.size());
		Noeud destination = nourrriture.get(0);
		double dist = nourrriture.get(0).cout;
		for(int i = 1; i < nourrriture.size(); i++) {
			if(nourrriture.get(i).cout < dist) {
				destination = nourrriture.get(i);
				dist = nourrriture.get(i).cout;
			}
		}
		System.out.println("Chemin size "+chemin.size());
		if(chemin.size() == 0) {
			aEtoile(game, destination, agent);
			
		}
		System.out.println("Chemin :"+chemin.size());
		for(Noeud e : chemin) {
			System.out.print(e.afficher()+" ");
		}
		System.out.println("");
		AgentAction action = new AgentAction(chemin.get(chemin.size()-1).direction);
		chemin.remove(chemin.get(chemin.size()-1));
		return action;
	}
	
	private void reconstituerChemin(Noeud fin) {
		Noeud courant = fin;
		while(courant.parent != null) {
			chemin.add(courant);
			courant = courant.parent;
		}
	}
	
	private Noeud rechercheNoeud(ArrayList<Noeud> list , Noeud n) {
		if(list.size() == 0) return null;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).x == n.x && list.get(i).y == n.y) {
				return list.get(i);
			}
		}
		return null;
	}
	
	private void changeNoeud(ArrayList<Noeud> list , Noeud n) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).x == n.x && list.get(i).y == n.y) {
				list.set(i, n);
			}
		}
	}
	
	private double distance(Noeud n1, Noeud n2) {
		return Math.sqrt( Math.pow((n1.x - n2.x),2) +  Math.pow((n1.y - n2.y),2));
	}
	
	private boolean aEtoile(Game game, Noeud objectif, Noeud depart) {
		System.out.println("Objectif : "+objectif.afficher());
		System.out.println("Depart : "+depart.afficher());
		PacmanGame pgame = (PacmanGame) game;
		ArrayList<Noeud> closedList = new ArrayList<StrategyAetoile.Noeud>();
		ArrayList<Noeud> openList = new ArrayList<StrategyAetoile.Noeud>();
		openList.add(depart);
		while(openList.size() > 0) {
			Collections.sort(openList, NoeudComparator);
			for(Noeud e : openList) {
				System.out.print(e.afficher()+" "+e.heuristique);
			}
			System.out.println("");
			Noeud courant = openList.remove(0);
			System.out.println("Noeud courant "+courant.afficher()+" Obj "+objectif.afficher());
			if(courant.x == objectif.x && courant.y == objectif.y) {
				//System.out.println("Fin de recherche");
				reconstituerChemin(courant);
				return true;
			}else {
				//System.out.println("Pas fin");
				ArrayList<Noeud> voisins = new ArrayList<StrategyAetoile.Noeud>();
				if(!pgame.getMaze().isWall((int)courant.x - 1, (int)courant.y)) voisins.add(new Noeud(courant.x - 1, courant.y, 0, 0, courant, AgentAction.WEST));// Case gauche
				if(!pgame.getMaze().isWall((int)courant.x + 1, (int)courant.y)) voisins.add(new Noeud(courant.x + 1, courant.y, 0, 0, courant, AgentAction.EAST));// Case droite
				if(!pgame.getMaze().isWall((int)courant.x, (int)courant.y - 1)) voisins.add(new Noeud(courant.x, courant.y - 1, 0, 0, courant, AgentAction.NORTH));// Case haut
				if(!pgame.getMaze().isWall((int)courant.x, (int)courant.y + 1)) voisins.add(new Noeud(courant.x, courant.y + 1, 0, 0, courant, AgentAction.SOUTH));// Case bas
				System.out.println("Voisins size : "+voisins.size());
				for(Noeud voisin : voisins) {
					//System.out.println(closedList.toString());
					//System.out.println("Recherche "+(rechercheNoeud(closedList, voisin) == null));
					if(rechercheNoeud(closedList, voisin) == null) {
						//System.out.println("Test");
						voisin.cout = courant.cout + 1;
						voisin.heuristique = voisin.cout + distance(voisin, objectif);
						
						Noeud dejaPesent = rechercheNoeud(openList, voisin);
						//System.out.println("Deja present "+dejaPesent.afficher());
						if(dejaPesent != null ) {
							System.out.println("Ajout noeud: "+voisin.afficher());
							if(dejaPesent.heuristique > voisin.heuristique) {
								changeNoeud(openList, voisin);
							}
						}else {
							System.out.println("Ajout noeud: "+voisin.afficher());
							openList.add(voisin);
						}
					}
				}
				closedList.add(courant);
			}
		}
		System.out.println("Terminé");
		return false;
	}
	
	private void parcoursLargeur(Game game, Noeud depart) {
		nourrriture.clear();
		PacmanGame pgame = (PacmanGame) game;
		ArrayList<Noeud> markedList = new ArrayList<StrategyAetoile.Noeud>();
		ArrayList<Noeud> openList = new ArrayList<StrategyAetoile.Noeud>();
		openList.add(depart);
		markedList.add(depart);
		while(openList.size() > 0) {
			Noeud courant = openList.remove(0);
			ArrayList<Noeud> voisins = new ArrayList<StrategyAetoile.Noeud>();
			if(!pgame.getMaze().isWall((int)courant.x - 1, (int)courant.y)) voisins.add(new Noeud(courant.x - 1, courant.y, 0, 0, courant, AgentAction.WEST));// Case gauche
			if(!pgame.getMaze().isWall((int)courant.x + 1, (int)courant.y)) voisins.add(new Noeud(courant.x + 1, courant.y, 0, 0, courant, AgentAction.EAST));// Case droite
			if(!pgame.getMaze().isWall((int)courant.x, (int)courant.y - 1)) voisins.add(new Noeud(courant.x, courant.y - 1, 0, 0, courant, AgentAction.NORTH));// Case haut
			if(!pgame.getMaze().isWall((int)courant.x, (int)courant.y + 1)) voisins.add(new Noeud(courant.x, courant.y + 1, 0, 0, courant, AgentAction.SOUTH));// Case bas
			for(Noeud voisin : voisins) {;
				if(rechercheNoeud(markedList, voisin) == null) {
					voisin.cout = courant.cout + 1;
					voisin.heuristique = voisin.cout;
					openList.add(voisin);
					markedList.add(voisin);
					if(pgame.getMaze().isFood((int)voisin.x, (int)voisin.y) || pgame.getMaze().isCapsule((int)voisin.x, (int)voisin.y) ) {
						nourrriture.add(voisin);
					}
				
				}
			}
			
		}
	}

	@Override
	public void afficheStrategy() {
		System.out.println("-- Strategy A * --");
		
	}

}
