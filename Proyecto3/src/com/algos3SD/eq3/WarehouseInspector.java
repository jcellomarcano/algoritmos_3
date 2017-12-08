import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import junit.framework.TestCase;

public class WarehouseInspector  extends TestCase {
	/**Defines a cube. Each face attribute stores the code identifying what the face is made of / what color it is.*/
	class Cube {
		public final int front, back, left, right, top, bttm;
		
		public Cube(int front, int back, int left, int right, int top, int bttm){
			this.front = front;
			this.back = back;
			this.left = left;
			this.right = right;
			this.top = top;
			this.bttm = bttm;
		}
		
		/**Returns the material on the given side of the face specified (e.g. <code>getSide("front")</code>
		 * returns <code>this.front</code>). Abreviations (Such as "fr" for "front") are supported&mdash;but must
		 * abreviate only the words "front", "back", "left", "right", "top" and "bottom" (i.e. synonyms such as
		 * "upper" are not allowed), and must be distinct enough (i.e. the abreviation "b" will produce an error
		 * since it could represent either "back" or "bottom").
		 * 
		 * @throws IllegalArgumentException If it could not resolve the name of the face given.
		 */
		public int getSide(String face) throws IllegalArgumentException{
			face = face.trim().toLowerCase();
			String error = "Face "+face+" not recognized. Please use 'front', 'back', 'left', 'right', 'top', 'bottom' only.";
			try{
				switch(face.charAt(0)){
					case 'f':	//front
						return this.front;
					case 'b':
							switch(face.charAt(1)){
								case 'a':
								case 'c':
								case 'k':
									return this.back;
								case 'o':
								case 't':
								case 'm':
									return this.bttm;
								default:
									throw new IllegalArgumentException(error);
							}
					case 'l':	//left
						return this.left;
					case 'r':	//right
						return this.right;
					case 't':	//top
						return this.top;
					default:
						throw new IllegalArgumentException(error);
				}
			}catch(IndexOutOfBoundsException e){
				throw new IllegalArgumentException(error);
			}
		}
		
		/**Returns the material on the other side of the face specified (e.g. <code>getOtherSide("front")</code>
		 * returns <code>this.back</code>). Abreviations (Such as "fr" for "front") are supported&mdash;but must
		 * abreviate only the words "front", "back", "left", "right", "top" and "bottom" (i.e. synonyms such as
		 * "upper" are not allowed), and must be distinct enough (i.e. the abreviation "b" will produce an error
		 * since it could represent either "back" or "bottom").
		 * 
		 * @throws IllegalArgumentException If it could not resolve the name of the face given.
		 */
		public int getOtherSide(String face) throws IllegalArgumentException{
			face = face.trim().toLowerCase();
			String error = "Face "+face+" not recognized. Please use 'front', 'back', 'left', 'right', 'top', 'bottom' only.";
			try{
				switch(face.charAt(0)){
					case 'f':	//front
						return this.back;
					case 'b':
							switch(face.charAt(1)){
								case 'a':
								case 'c':
								case 'k':
									return this.front;
								case 'o':
								case 't':
								case 'm':
									return this.top;
								default:
									throw new IllegalArgumentException(error);
							}
					case 'l':	//left
						return this.right;
					case 'r':	//right
						return this.left;
					case 't':	//top
						return this.bttm;
					default:
						throw new IllegalArgumentException(error);
				}
			}catch(IndexOutOfBoundsException e){
				throw new IllegalArgumentException(error);
			}
		}
	}
	
	/**Loads file into array, one line per array cell.*/
	private String[] loadFile(String filename)throws IOException{
		BufferedReader f = new BufferedReader(new FileReader(filename));
		
		List<String> out = new Vector<String>();
		
		String line;
		do{
			line = f.readLine();
			if(line != null)
				out.add(line);
			
		}while(line != null);
		
		f.close();
		
		return out.toArray(new String[0]);
	}
	
	/**runs program with given file and checks if output is valid
	 * 
	 * @throws IllegalArgumentException If either file is not in the right format
	 * @throws IOException if either file is not accessible
	 */
	private void checkFileOutput(String inputFile, String expectedOutputFile) throws IllegalArgumentException, IOException{
		String[] input = loadFile(inputFile);
		String[] results = AbstractMainTests.executeMain("WALLW", new String[]{inputFile});
		String[] expected = loadFile(expectedOutputFile);
		
		int casos = 1;
		
		//read input file (only one that can tell us how many cases)
		for(int i=0, x=0, r=0; i<input.length; ){
			if(!input[i].trim().isEmpty()){
				try{
					int cubos = Integer.parseInt(input[i]);
					if(cubos==0) //End of File
						break;
					
					if(!expected[x].trim().isEmpty()){
						if(!results[r].trim().isEmpty()){
							
							//Check Case header
							String numCasos = expected[x].trim().replaceAll("\\s", "");
							if(!numCasos.equals("Case#"+casos))
								throw new IllegalArgumentException("El archivo \""+expectedOutputFile
										+"\" no esta en el formato correcto. Asegurese de no haberlo modificado",
										new AssertionError("\""+numCasos+"\".equals(\"Case#"+casos+"\")")
									); 
							super.assertTrue("Falta numero de caso "+casos+" (se encontro \""+results[r]
									+"\" en su lugar)", numCasos.equals( results[r].trim().replaceAll("\\s", "") )
								);
							
							//Check tower height
							x++;
							r++;
							int tallest = Integer.parseInt(expected[x]);
							super.assertTrue("La torre para el caso "+casos+" no es la mas alta",
									Integer.toString(tallest).equals(results[r].trim())
								);
							
							//Check tower order
							r++;
							String[] tower = Arrays.copyOfRange(results, r, r+tallest);
							x++;
							i++;
							super.assertTrue("La torre del caso "+casos+" no se sostiene",
									Arrays.equals(tower, Arrays.copyOfRange(expected, x, x+tallest))
									|| validate(loadCubes(Arrays.copyOfRange(input, i, i+cubos)), tower)
								);
							
							i+=cubos;
							x+=tallest;
							r+=tallest;
							System.out.println(inputFile+" "+numCasos+" passed inspection");
							casos++;
						}else{
							//if results[r] IS empty, it's a blank line. Skip
							r++;
						}
					}else{
						//if expected[x] IS empty, it's a blank line. Skip
						x++;
					}
				}catch(NumberFormatException e){
					throw new IllegalArgumentException("El archivo \""+inputFile+"\" o \""+expectedOutputFile
							+"\" no esta en el formato correcto. Asegurese de no haberlo modificado", e); 
				}catch(IllegalArgumentException e){
					throw new IllegalArgumentException("La salida que ha impreso el programa, o el archivo \""+inputFile
							+"\" no esta en el formato correcto. Asegurese de no haberlo modificado \""+inputFile+"\"", e);
				}
			}else{
				//if input[i] IS empty, it's a blank line. Skip
				i++;
			}
		}
	}
	
	private boolean validate(List<Cube> pool, String[] tower) throws IllegalArgumentException{
		try{
			String[] prevSpec = tower[0].trim().split("\\s");
			Cube prevCube = pool.get(Integer.parseInt(prevSpec[0]));
			for(int i=1; i<tower.length; i++){
				String[] currSpec = tower[i].trim().split("\\s");
				try{
					Cube currCube = pool.get(Integer.parseInt(currSpec[0]));
					try{
						if(prevCube.getOtherSide(prevSpec[1]) != currCube.getSide(currSpec[1])){
							System.err.println("El "+(i-1)+"-esimo cubo de la torre (cubo #"+prevSpec[0]
									+") tiene la cara "+prevSpec[1]+" hacia arriba (material "
									+prevCube.getOtherSide(prevSpec[1])+" hacia abajo) y el "
									+i+"-esimo cubo de la torre (cubo #"+currSpec[0]
									+") tiene la cara "+prevSpec[1]+" hacia arriba (material "
									+currCube.getSide(currSpec[1])+"). Inspection failed.");
							return false;
						}
						prevSpec = currSpec;
						prevCube = currCube;
					}catch(IndexOutOfBoundsException e){
						throw new IllegalArgumentException("El "+(i-1)+"- o el "+i+"-esimo cubo de la torre (\""
								+Arrays.deepToString(prevSpec)+"\" y \""+Arrays.deepToString(prevSpec)
								+"\" respectivamente) no esta en el formato correcto", e);
					}
				}catch(IndexOutOfBoundsException e){
					throw new IllegalArgumentException("El "+i+"-esimo cubo de la torre "+Arrays.deepToString(currSpec)
							+" se refiera a un cubo que no existe", e);
				}
			}
			return true;
		}catch(IndexOutOfBoundsException e){
			throw new IllegalArgumentException("El cubo superior de la torre no esta en el formato correcto", e);
		}
	}

	List<Cube> loadCubes(String[] lines) throws NumberFormatException, IllegalArgumentException{
		List<Cube> out = new ArrayList<Cube>(lines.length+1);
		out.add(new Cube(101,101,101,101,101,101));//add dummy cube so list indices match cube ID
		for(int i=0; i<lines.length; i++){
			assert(out.size()==i+1);
			String[] caras = lines[i].split("\\s");
			try{
				out.add(new Cube(Integer.parseInt(caras[0]), Integer.parseInt(caras[1]),
						Integer.parseInt(caras[2]), Integer.parseInt(caras[3]),
						Integer.parseInt(caras[4]), Integer.parseInt(caras[5])
					));
				assert(out.get(i+1).front==Integer.parseInt(caras[0]) && out.get(i+1).back==Integer.parseInt(caras[1])
						&& out.get(i+1).left==Integer.parseInt(caras[2]) && out.get(i+1).right==Integer.parseInt(caras[3])
						&& out.get(i+1).top==Integer.parseInt(caras[4]) && out.get(i+1).bttm==Integer.parseInt(caras[5])  
					);
			}catch(IndexOutOfBoundsException e){
				throw new IllegalArgumentException("El cubo "+(i+1)+" ("+lines[i]+") no tiene 6 caras");
			}
		}
		assert(lines.length+1 == out.size());
		return out;
	}

	public void testEjemplo0(){
		try{
			checkFileOutput("Ejemplo 0.txt", "Salida 0.txt");
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void testEjemplo3() throws IOException{
		checkFileOutput("Ejemplo 3.txt", "Salida 3.txt");
	}
	public void testEjemplo2() throws IOException{
		checkFileOutput("Ejemplo 2.txt", "Salida 2.txt");
	}
}
