package br.com.herochoice;

import java.io.IOException;


public class Chooser {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Helper.readRelations();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
