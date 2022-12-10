import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
///TElikooooo
public class UCS {
	
	static int expansionsNumber = 0;
	static ArrayList<Integer> finalState = new ArrayList<Integer>();
	static int flag=0;
	static int break_flag;

	static ArrayList<Node> searchFrontier = new ArrayList<Node>();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Give the number of the elements in the list: ");
		int listsLength = scanner.nextInt();
		ArrayList<Node> currentNodeChilds = new ArrayList<Node>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		Node rootNode = new Node(0);
		System.out.print("Give the elements: ");
		for (int i = 0; i < listsLength; i++) {
			
			rootNode.insert(scanner.nextInt());
		}
		///
		System.out.print("Press 1 for UCS algorithm or 0 for A*: ");
		int algorithm = scanner.nextInt();
		///
		
		ArrayList<Integer> clone = (ArrayList<Integer>) rootNode.getArray().clone();
		
		
		finalState = clone;
		
		Collections.sort(finalState);
		
		//
		
		
		/////
		// 1o bhma
		searchFrontier.add(rootNode);
		// 2o bhma
		ArrayList<Node> closedSet = new ArrayList<Node>();
		// 3o bhma
		while (!searchFrontier.isEmpty()) {
		//for (int q = 0; q < 5; q++) {
			// 4o bhma
			Node temp=new Node();
			temp=compare(searchFrontier);
			
			if (temp.getArray().equals(finalState)) {
				Node monopati = new Node();
				ArrayList<Node> fathersArray = new ArrayList<Node>();
				monopati = temp.getFather();
				
				//
				if(temp.getFather()==null) {
					System.out.println("I am the same with father because i am father root");
					System.out.println("Solution");
					System.out.println("Cost: " + 0);                                                            //epeidh gia test 1 2 3 4 5   vgazei la9os  
					System.out.println("Expansions number: " + expansionsNumber);
					System.out.println(list);
					System.exit(0);
				}
				//
			
				while(!(monopati.getArray().equals(rootNode.getArray()))){
					fathersArray.add(monopati);
					monopati = monopati.getFather();
				}
				fathersArray.add(rootNode);
				Collections.reverse(fathersArray);
				fathersArray.add(temp);
				
				for (Node i: fathersArray) {
					
					System.out.println("Monopati" + i.getArray());
				}
			
				System.out.println(temp.getArray());
				System.out.println("Solution");
				System.out.println("Cost: " + temp.getCost());
				System.out.println("Expansions number: " + expansionsNumber);
				System.exit(0);
			}
			// 6o bhma
			
			closedSet.add(temp);
			// 7o bhma
			generateNodes(currentNodeChilds, temp, list);
			// 8o bhma
			
			
			ArrayList<Node> curr = new ArrayList<Node>();
			ArrayList<Node> close = new ArrayList<Node>();
			ArrayList<Node> metopo = new ArrayList<Node>();
			
			
			
			for(Node i:currentNodeChilds) {
				for(Node j:closedSet) {
					
					if (i.getArray().equals(j.getArray())) {
					
						if (i.getCost() < j.getCost()) {
							
							close.add(j);
						}
						else {
							
							curr.add(i);
							
						}
					}
				}
			
				
				for(Node j :searchFrontier) {
					if (i.equals(j)) {
						
						if (i.getCost() < j.getCost()) {
							
							metopo.add(j);
							
						}
						else {
						
							curr.add(i);
						}
					}
				}
			}
			
			for (Node a:close) {
				
				closedSet.remove(a);
			
			}
			
			for (Node e:curr) {
			
				currentNodeChilds.remove(e.getArray());
			
			}
			for (Node e:metopo) {
				
				searchFrontier.remove(e.getArray());
			}
			close.clear();
			curr.clear();
			metopo.clear();
			
			/*
			System.out.println("Ta paidia");
			for (int i = 0; i < currentNodeChilds.size(); i++) {
				System.out.println(currentNodeChilds.get(i).getArray());
			}*/
			
			
			// 9o bhma
			for (int i = 0; i < currentNodeChilds.size(); i++) {
				///
				if(algorithm == 1) {
					searchFrontier.add(currentNodeChilds.get(i));
				}
				else {
					A_astro_method(currentNodeChilds.get(i));
					
					if (break_flag==1) {
						
						break;
					}
					
					}
			}
				
			
			currentNodeChilds.clear();
			searchFrontier.remove(/*compare(searchFrontier)*/temp);
			
			System.out.println("metopo ------------");
			for (int i = 0; i < searchFrontier.size(); i++) {
				System.out.println(searchFrontier.get(i).getArray());
				
				
					
			}
			
		}
		
		///
		System.out.println("No Solution");
		//
	}
	
	private static void A_astro_method(Node currentNodeChilds ) {
		break_flag=0;
		
		if(currentNodeChilds.getArray().get(currentNodeChilds.getArray().size()-1-flag).equals(finalState.get(finalState.size()-1-flag))) {
			
			flag++;
			
			searchFrontier.clear();
			searchFrontier.add(currentNodeChilds);
			
			break_flag=1;
		}
		if(break_flag==0) {
			searchFrontier.add(currentNodeChilds);
		}
			
		
		
	}

	public static void generateNodes(ArrayList<Node> currentNodeChilds/*allNodes*/,Node rootNode,ArrayList<Integer> list) {
		
	
		
			expansionsNumber += rootNode.getArray().size() - 1;
			for (int k = 2; k <= rootNode.getArray().size()-flag; k++) {
				Node node = new Node(rootNode.getCost() + 1);
				node.setFather(rootNode);
				list = transitionOperator(rootNode.getArray(),k);
				
				for (int i = 0; i < list.size(); i++) {
					node.insert(list.get(i));
				}
				currentNodeChilds.add(node);
			}
		
			//list.clear();
	}
	
	

	public static Node compare(ArrayList<Node> searchFrontier) {
		int min = searchFrontier.get(0).getCost();
		int a = 0;
		for (int i = 0; i < searchFrontier.size(); i++) {
			if (searchFrontier.get(i).getCost() < min) {
				min = searchFrontier.get(i).getCost();
				a = i;
			}
		}
		return searchFrontier.get(a);
	}
	public static ArrayList<Integer> transitionOperator(ArrayList<Integer> condition, int k) {
		ArrayList<Integer> firstKElements = new ArrayList<Integer>();
		ArrayList<Integer> tempArray = new ArrayList<Integer>();
		int j = k-1;
		int b = k;
		int position = 0;
		ArrayList<Integer> restOfElements = new ArrayList<Integer>();
		ArrayList<Integer> finalList = new ArrayList<Integer>();
		finalList = (ArrayList<Integer>) condition.clone();
		
		if (k == condition.size()) {
			for (int i = 0; i < k; i++) {
				firstKElements.add(condition.get(i));
			}
		}
		else {
			for (int i = 0; i < k; i++) {
				firstKElements.add(condition.get(i));
			}
			for (int i = 0; i < condition.size() - k; i++) {
				restOfElements.add(condition.get(b++));
			}
		}
			
		// Reversing left Array
		for (int i = k-1; i >= 0; i--) {
			tempArray.add(firstKElements.get(i));
		}
			
		// Merge arrays
		for (int element : tempArray) {
			finalList.set(position++, element); 
		}
		for (int element : restOfElements) {
			finalList.set(position++, element);
		}
		
		//firstKElements.clear();
		//restOfElements.clear();
		//tempArray.clear();
		
		return finalList;
	}

}
class Node{
	
	private int cost;
	private ArrayList<Integer> array = new ArrayList<Integer>();
	private Node father;
	
	public Node(int cost) {
		this.cost = cost;
	}
	public void setFather(Node rootNode) {
		father = rootNode;
	}
	public Node() {
		// TODO Auto-generated constructor stub
	}
	public void insert(int num) {
		array.add(num);
	}

	public ArrayList<Integer> getArray() {
		return array;
	}

	/*public void setCost(int cost) {
		this.cost = cost;
	}*/

	public int getCost() {
		return cost;
	}
	public Node getFather() {
		return father;
	}
	
	
}
