import java.awt.Color;
import java.awt.Graphics;
import java.util.Stack;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TowersOfHanoi {

	static Stack<Integer> source = new Stack<Integer>();
	static Stack<Integer> temp = new Stack<Integer>();
	static Stack<Integer> dest = new Stack<Integer>();
	
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		JFrame frame = new JFrame();
		Visualizer v = new Visualizer();
		frame.setSize(600, 900);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.getContentPane().add(v);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		int input = sc.nextInt();
		for(int i=input; i>0; i--) {
			source.add(i);
		}
		System.out.println("source: "+source);
		System.out.println("temp: "+temp);
		System.out.println("dest: "+dest);
		System.out.println();
		int ind = 0;
		while(dest.size()!=input) {
			if(ind==3) {
				ind-=3;
			}
			if(input%2==0) {
				move(ind);
			}else {
				if(ind==0) {
					move(ind+1);
				}else if(ind==1) {
					move(ind-1);
				}else{
					move(ind);
				}
			}
			System.out.println("source: "+source);
			System.out.println("temp: "+temp);
			System.out.println("dest: "+dest);
			System.out.println(ind);
			System.out.println();
			ind++;
			TimeUnit.MILLISECONDS.sleep(100);
		}
	}
	
	public static void move(int a) {
		if(a==0) {
			int topA = Integer.MAX_VALUE, topB = Integer.MAX_VALUE;
			if(!source.isEmpty()) {
				topA = source.peek();
			}
			if(!temp.isEmpty()) {
				topB = temp.peek();
			}
			if(topA<topB) {
				temp.add(source.pop());
			}else{
				source.add(temp.pop());
			}
		}else if(a==1) {
			int topA = Integer.MAX_VALUE, topC = Integer.MAX_VALUE;
			if(!source.isEmpty()) {
				topA = source.peek();
			}
			if(!dest.isEmpty()) {
				topC = dest.peek();
			}
			if(topA<topC) {
				dest.add(source.pop());
			}else{
				source.add(dest.pop());
			}
		}else if(a==2) {
			int topC = Integer.MAX_VALUE, topB = Integer.MAX_VALUE;
			if(!dest.isEmpty()) {
				topC = dest.peek();
			}
			if(!temp.isEmpty()) {
				topB = temp.peek();
			}
			if(topC<topB) {
				temp.add(dest.pop());
			}else{
				dest.add(temp.pop());
			}
		}
	}
	
	public static class Visualizer extends JPanel {
		public static final int sourceX = 50, tempX = 250, destX = 400;
		public static final int bottomY = 600;
		public static final int height = 30;
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for(int i=0; i<source.size(); i++) {
				g.setColor(Color.BLACK);
				g.fillRect(sourceX, bottomY-50*i, source.get(i)*15, height);
			}
			for(int i=0; i<temp.size(); i++) {
				g.setColor(Color.BLUE);
				g.fillRect(tempX, bottomY-50*i, temp.get(i)*15, height);
			}
			for(int i=0; i<dest.size(); i++) {
				g.setColor(Color.GREEN);
				g.fillRect(destX, bottomY-50*i, dest.get(i)*15, height);
			}
			repaint();
		}
	}

}
