package hotciv.common;

import thirdparty.ThirdPartyFractalGenerator;

public class Main {

	public static void main(String[] args){
		int row = Integer.parseInt(args[0]);
		int column = Integer.parseInt(args[1]);
		ThirdPartyFractalGenerator fractal = new ThirdPartyFractalGenerator();

		char tileChar = fractal.getLandscapeAt(row, column);
		
		if ( tileChar == '.' ) { System.out.println("The tile at row " + row + " and column " + column + " is: Oceans"); }
		if ( tileChar == 'o' ) { System.out.println("The tile at row " + row + " and column " + column + " is: Plains"); }
		if ( tileChar == 'M' ) { System.out.println("The tile at row " + row + " and column " + column + " is: Mountains"); }
		if ( tileChar == 'f' ) { System.out.println("The tile at row " + row + " and column " + column + " is: Forest"); }
		if ( tileChar == 'h' ) { System.out.println("The tile at row " + row + " and column " + column + " is: Hills"); }
	}
}
