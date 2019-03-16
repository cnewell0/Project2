package com.project2;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.*;
import java.io.*;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Handler {


  public static void handleChannelTag(Document document) {

    Element root = document.getDocumentElement();

    //Finding amount of persona
    NodeList nList = root.getElementsByTagName("PERSONA");
    int numPersona = nList.getLength();

    System.out.println("There is: " + numPersona + "persona");


    //findidng how much hamlet is in there
    System.out.println("Enter a speaker to see how many times they speak");
    Scanner reader = new Scanner(System.in);
    String person = reader.nextLine();

    NodeList personList = root.getElementsByTagName("HAMLET");

    Element speakerElement;
    int count = 0;

    for (int i = 0; i < personList.getLength(); i++) {

      speakerElement = (Element) personList.item(i);
      if (person.equals(speakerElement.getTextContent())) {
        count++;
      }
    }


  }
}
