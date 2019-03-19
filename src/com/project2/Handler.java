package com.project2;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.*;
import java.io.*;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Handler {

  /**
   * This function sets the variable root to the first ellement in the
   * xml file and then calls the other methods in the Handler class
   *
   * @param document is used to pass the xml document from the main class
   *                 to the handler class to parse the document
   */
  public static void handleChannelTag(Document document) {
    Scanner reader = new Scanner(System.in);
    Element root = document.getDocumentElement();
    personaNumber(root);
    speakerActs(root);
    fragmentSearching(root, reader);

  }

  /**
   * This function returns a print statement to show how many "PERSONA"
   * there are in the play
   *
   * @param root gets the root of the document to access other elements
   *             *             in the xml file
   */
  public static void personaNumber(Element root) {

    //Finding amount of persona
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

    //findidng how much hamlet is in there
    System.out.println("Enter a speaker to see how many times they speak");
    Scanner reader = new Scanner(System.in);
    String person = reader.nextLine();

    NodeList personList = root.getElementsByTagName("SPEAKER");

    Element speakerElement;
    int count = 0;

    for (int i = 0; i < personList.getLength(); i++) {

      speakerElement = (Element) personList.item(i);
      if (person.equals(speakerElement.getTextContent()))
        count++;

    }
    System.out.println(person + " speaks: " + count + "times");
  }


  /**
   * The goal of this function is to prompt the user to enter a sentence fragment,
   * then the function parses the xml document looking for that fragment and
   * shows the time it took to find the fragment
   * It ends with a prompt to the user asking if they want to replace the sentence
   * fragment
   *
   * @param root gets the root of the document to access other elements
   *             *        in the xml file
   */
  public static void fragmentSearching(Element root, Scanner reader) {
    System.out.println("What fragment do you want to search for?");
    String sentanceFragment = reader.nextLine();
    if (sentanceFragment == "")
      sentanceFragment = "To be, or not to be";

    NodeList fragList = root.getElementsByTagName("LINE");
    Node[] lineNode = new Node[fragList.getLength()];
    Element lineElement;
    boolean isFound = false;
    int count = 0;

    long clockStart = System.nanoTime();
    for (int i = 0; i < fragList.getLength(); i++) {
      lineElement = (Element) fragList.item(i);

      if (lineElement.getTextContent().contains(sentanceFragment)) {
        lineNode[count] = fragList.item(i);
        count++;
        isFound = true;
      }
    }

    long clockEnd = System.nanoTime();
    if (!isFound) {
      System.out.println("Sorry, fragment not found. Search performed in " + ((clockEnd - clockStart) / 1000000000.0) + " seconds");
    }

    if (isFound == true) {
      for (int j = 0; j < count; j++) {
        System.out.println("The fragment has been found in the following sentence: "
                + lineNode[j].getTextContent() + " ");
      }
      System.out.println("Search performed in " + ((clockEnd - clockStart) / 1000000000.0) + " seconds");
      System.out.println("Do you want to replace it? (Y/N");
    }
  }

  public static void fragmentReplace(Element root, Scanner reader) {
    //char answer = reader.next();
    //if (answer == 'Y'){

    }


  }



