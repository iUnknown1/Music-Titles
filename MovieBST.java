/** Class: CISC 3130



 * Section: MY9



 * EmplId: 23583219



 * Name: Bryan Brathwaite

 

 * Professor Katherine Chuang



*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MovieBST {
  private Movie first;
private Object genres;
 
  public void subSetRec(Set<String> s, Movie node, String start, String end) {
     
      if (node == null) {
return;
}
     
     
 /* in left subtree */
      if (start.compareTo(node.getTitle()) < 0) {
          subSetRec(s, node.getLeft(), start, end);
      }
     
      if (start.compareTo(node.getTitle()) <= 0 && end.compareTo(node.getTitle()) >=0) {
          s.add(node.getTitle());
}
 
 
/* in right subtree */
if (end.compareTo(node.getTitle()) > 0) {
  subSetRec(s, node.getRight(), start, end);
}
  }
 
  public Set<String> subSet(String start, String end) {
      // Create a new Set and fill it recursively
      Set<String> s = new HashSet<String>();
      subSetRec(s, this.first, start, end);
      return s;
  }
  // first movie
  public void setFirst(Movie first) {
      this.first = first;
  }
  public Movie getFirst() {
      return first;
  }

  public void addNode(String title, int releaseYear, int movieId, String[] genres) {
      // Fill the info for movie
      this.first = addNodeRec(this.first, title);
      this.first.setReleaseYear(releaseYear);
      this.first.setMovieId(movieId);
      this.first.setGenres(genres);
  }
 
  public Movie addNodeRec(Movie root, String title) {
	// if less go left, if greater go right, if equal , search hit
      if (root == null) {
root = new Movie(title);
return root;
}
      if (title.compareTo(root.getTitle()) < 0) {
root.setLeft(addNodeRec(root.getLeft(), title));
      }
else if (title.compareTo(root.getTitle()) > 0) {
root.setRight(addNodeRec(root.getRight(), title));
}
 

return root;
}
 // Traverses movies in orders
  public void traverseInOrder() {
      traverseInOrderRec(this.first);
  }
  public void traverseInOrderRec(Movie root) {
 
if (root.getLeft() != null) {
traverseInOrderRec(root.getLeft());
}
System.out.println(root.getTitle());
if (root.getRight() != null) {
traverseInOrderRec(root.getRight());
}
}
  public void loadDatabaseFromFile(String filename)
  {
  try
  {
  FileReader fr = new FileReader(filename);
    
  try
  {
  Scanner scan = new Scanner(fr);
    
  int lineNumber = 0;
    
  while (scan.hasNextLine())
  {
  lineNumber++;
  String line = scan.nextLine(); // Read one line of the text file into a string
    
  String[] parts = line.split(" "); // Split the line by comma into a String array
  String movieId = parts[0];
  String title = parts[1];
  String releaseYear = parts[2];
  String genres = parts[3];
  movieId = movieId.trim();
  int movieIdNumber= Integer.parseInt(movieId); // changes number from string to integer
    
  try
  {
  Movie movie = new Movie(movieId);
  addNode(title,releaseYear,movieId,genres);
  }
  catch (IllegalStateException e)
  {
  System.out.print("Error in movie information read from file.\n");
  }
  }
  }
  finally
  {
  fr.close();
  }
  }
  catch (FileNotFoundException e)
  {
  System.out.print("File not found\n");
  }
  catch (IOException e)
  {
  System.out.print("Unexpected I/O exception\n");
  }
  }
}