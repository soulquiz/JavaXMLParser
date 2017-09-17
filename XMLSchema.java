/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlschema;

import java.io.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
//import org.jdom2.*;
//import org.jdom2.input.SAXBuilder;
import org.w3c.dom.*;

/**
 *
 * @author soulq
 */
public class XMLSchema {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            File inputFile = new File("D:\\Google Drive\\Selected Topics in Computer Engineering II\\xml\\artist.xml"); 
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            Element rootElement = doc.getDocumentElement();
            NodeList nList = doc.getElementsByTagName("Artist");
            System.out.println("----------------------------");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element : " + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Name : " 
                        + eElement.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Country : "
                        + eElement.getElementsByTagName("country").item(0).getTextContent());
                    System.out.println("Company : "
                        + eElement.getElementsByTagName("company").item(0).getTextContent());
                    NodeList albumList = eElement.getElementsByTagName("album");
                    for (int temp1 = 0; temp1 < albumList.getLength(); temp1++){
                        Node albumNode = albumList.item(temp1);
                        Element eAlbum = (Element) albumNode;
                        System.out.println(albumNode.getNodeName() + " name : '" + eAlbum.getAttribute("name") + "' year : '" 
                                + eAlbum.getAttribute("year") + "'");
                        NodeList songList = eAlbum.getElementsByTagName("song");
                        for (int temp2 = 0; temp2 < songList.getLength(); temp2++){
                            Node songNode = songList.item(temp2);
                            Element eSong = (Element) songNode;
                            System.out.println("    " + songNode.getNodeName() + " name : '" + eSong.getAttribute("name") 
                                + "' id : '" + eSong.getAttribute("id")
                                + "' length : '" + eSong.getAttribute("length") + "' melody : '" 
                                + eSong.getAttribute("melody") + "' lyrics : '" + eSong.getAttribute("lyrics")
                                + "'");
                        }
                    }
                    NodeList awardList = eElement.getElementsByTagName("award");
                    for (int temp1 = 0; temp1 < awardList.getLength(); temp1++){
                        Node awardNode = awardList.item(temp1);
                        Element eAward = (Element) awardNode;
                        System.out.println(awardNode.getNodeName() + " name : '" + eAward.getAttribute("name") + "'");
                        System.out.println("    " + "description : '" +eAward.getElementsByTagName("description").item(0)
                                .getTextContent() + "'");
                    }                
                }
                System.out.println("------------------------------------------------------------");
            }
            
            // create new artist element
            Element artist = doc.createElement("Artist");
            rootElement.appendChild(artist);
            
            // name element
            Element artist_name = doc.createElement("name");
            artist.appendChild(artist_name);
            artist_name.appendChild(doc.createTextNode("Jirakorn"));
            
            // country element
            Element country = doc.createElement("country");
            artist.appendChild(country);
            country.appendChild(doc.createTextNode("England"));
            
            // company element
            Element company = doc.createElement("company");
            artist.appendChild(company);
            company.appendChild(doc.createTextNode("Direction"));
            
            // album element
            Element album = doc.createElement("album");
            Attr album_name = doc.createAttribute("name");
            album_name.setValue("Black Corn");
            album.setAttributeNode(album_name);
            Attr album_year = doc.createAttribute("year");
            album_year.setValue("2017");
            album.setAttributeNode(album_year);
            artist.appendChild(album);
            
            // song element
            Element song = doc.createElement("song");
            Attr song_id = doc.createAttribute("id");
            song_id.setValue("1");
            song.setAttributeNode(song_id); 
            
            Attr song_length = doc.createAttribute("length");
            song_length.setValue("60");
            song.setAttributeNode(song_length);
            
            Attr song_lyrics = doc.createAttribute("lyrics");
            song_lyrics.setValue("Beer");
            song.setAttributeNode(song_lyrics);
            
            Attr song_melody = doc.createAttribute("melody");
            song_melody.setValue("Daigo");
            song.setAttributeNode(song_melody);
            
            Attr song_name = doc.createAttribute("name");
            song_name.setValue("Wood");
            song.setAttributeNode(song_name);
            album.appendChild(song);
            
            // award element
            Element award = doc.createElement("award");
            artist.appendChild(award);
            Attr award_name = doc.createAttribute("name");
            award_name.setValue("Best Melody");
            award.setAttributeNode(award_name);
            
            // award description element
            Element award_description = doc.createElement("description");
            award.appendChild(award_description);
            award_description.appendChild(doc.createTextNode("Best Melody in 2017"));
            
            
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("D:\\Google Drive\\Selected Topics in Computer "
                    + "Engineering II\\xml\\new_artist.xml"));
            transformer.transform(source, result);
            
            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
