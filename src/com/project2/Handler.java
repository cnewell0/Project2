package com.project2;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.imageio.metadata.IIOMetadataNode;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Handler {

  private static String sentenceFragment;
  private String filename;
  private Document document;

  /**
   * This function sets the variable root to the first element in the
   * xml file and then calls the other methods that deal with parsing
   *
   * @param document is used to pass the xml document from the main class
   *                 to the handler class to parse the document
   */
  public static void handleChannelTag(Document document) {
    Scanner reader = new Scanner(System.in);
    Element root = document.getDocumentElement();
    personaNumber(root);
    speakerActs(root);
    Node[] lineNode = fragmentSearching(root, reader);
    fragmentReplace(reader, lineNode);

  }

  /**
   * This function returns a print statement to show how many "PERSONA"
   * there are in the play
   *
   * @param root gets the root of the document to access other elements
   *             in the xml file
   */
  public static void personaNumber(Element root) {

    NodeList nList = root.getElementsByTagName("PERSONA");
    int numPersona = nList.getLength();

    System.out.println("There is: " + numPersona + " 'PERSONA' in the play!");
  }

  /**
   * This function takes user input of a speaker in the play and returns
   * how many times that the person speaks in the play, the default speaker
   * is set to Hamlet
   *
   * @param root gets the root of the document to access other elements
   *             in the xml file
   */
  public static void speakerActs(Element root) {

    System.out.println("Enter a speaker to see how many times they speak");
    Scanner reader = new Scanner(System.in);
    String person = reader.nextLine();
    if (person.equals(""))
      person = "hamlet";
    String uPerson = person.toUpperCase();

    NodeList personList = root.getElementsByTagName("SPEAKER");

    Element speakerElement;
    int count = 0;

    for (int i = 0; i < personList.getLength(); i++) {

      speakerElement = (Element) personList.item(i);
      if (uPerson.equals(speakerElement.getTextContent()))
        count++;

    }
    System.out.println(person + " speaks: " + count + "times\n");
  }


  /**
   * The goal of this function is to prompt the user to enter a sentence fragment,
   * then the function parses the xml document looking for that fragment and
   * shows the time it took to find the fragment
   * It ends with a prompt to the user asking if they want to replace the sentence
   * fragment
   *
   * @param root   gets the root of the document to access other elements
   *               in the xml file
   * @param reader is used to take input from user, derived from Scanner class
   * @return lineNode is returned to be used in the find and replace method
   */
  public static Node[] fragmentSearching(Element root, Scanner reader) {
    System.out.println("What fragment do you want to search for?");
    sentenceFragment = reader.nextLine();
    if (sentenceFragment.equals("")) ;
    sentenceFragment = "To be, or not to be";

    NodeList fragList = root.getElementsByTagName("LINE");
    Node[] lineNode = new Node[fragList.getLength()];
    Element lineElement;
    boolean isFound = false;
    int count = 0;

    long clockStart = System.nanoTime();
    for (int i = 0; i < fragList.getLength(); i++) {
      lineElement = (Element) fragList.item(i);

      if (lineElement.getTextContent().contains(sentenceFragment)) {
        lineNode[count] = fragList.item(i);
        count++;
        isFound = true;
      }
    }

    long clockEnd = System.nanoTime();
    if (!isFound) {
      System.out.println("Sorry, fragment not found. Search performed in " + ((clockEnd - clockStart) / 1000000000.0) + " seconds");
      System.exit(0);
    }

    if (isFound) {
      for (int j = 0; j < count; j++) {
        System.out.println("The fragment has been found in the following sentence: \n\n"
                + lineNode[j].getTextContent() + " \n");
      }
      System.out.println("Search performed in " + ((clockEnd - clockStart) / 1000000000.0) + " seconds");
      System.out.println("Do you want to replace it? (Y/N)");
      String answer = reader.next();
      if (answer.equals("N")) {
        System.exit(0);
      }

    }
    return lineNode;
  }

  /**
   * This function replaces the the sentence fragment that was found in the
   * previous function with a String that the user can enter
   *
   * @param reader   is used to take input from user, derived from Scanner class
   * @param lineNode is passed from the previous function to hold the line
   *                 that was taken from the xml file
   */
  public static void fragmentReplace(Scanner reader, Node[] lineNode) {

    System.out.println("Enter the number of the line you want to replace");
    int lineNumber = reader.nextInt();
    reader.nextLine();
    System.out.println("Enter the new fragment");
    String replacement = reader.nextLine();
    String newSentence = lineNode[lineNumber - 1].getTextContent().replace(sentenceFragment, replacement);

    lineNode[lineNumber - 1].setTextContent(newSentence);
    System.out.println("The sentence has been replaced as follows:\n\n" +
            lineNode[lineNumber - 1].getTextContent() + "\n\nDo you want to save changes? (Y/N)");
    String answer = reader.nextLine();
    if ((answer.equals("N")) || (answer.equals("n"))) {
      System.exit(0);
    }
  }

}

