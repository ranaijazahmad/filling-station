package com.fstation.general.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.DifferenceConstants;
import org.custommonkey.xmlunit.DifferenceListener;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.examples.RecursiveElementNameAndTextQualifier;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class XMLUnitTest
{
	
	@Before
	public void setUp() throws Exception
	{
	}
	
	@After
	public void tearDown() throws Exception
	{
		
	}
	
	@Test
	public void testXmlFileComparison() throws Exception
	{
		String expected = readFileContents(new File("E:/expected.xml")); // changed the order of sum no-leaf nodes and the order of leaf nodes
		String actual = readFileContents(new File("E:/actual.xml"));
		
		//Create document object of ACTUAL response string
		Document actualDoc = createXmlDoc(actual);
		List<String> removeList = getCommenttedTags(expected);
		
		//Delete those tags from ACTUAL that are commented out in EXPECTED
		if(removeList != null){
			for(int index = 0; index < removeList.size(); index++){
				String tagName = removeList.get(index).replaceAll("[\\t\\n\\r]+", "").trim();
				Element oldElement = (Element) actualDoc.getElementsByTagName(tagName).item(0);
				oldElement.getParentNode().removeChild(oldElement);
			}	
		}
		
		//Get back the actual String from the Document
		String actualOutput = getStringXml(actualDoc);
		actualDoc = null;
		
		//XMLUnit Configuration
		doXmlUnitConfigs();				
		
		//Compare the EXPECTED and ACTUAL
		Diff diff = new Diff(expected, actualOutput);
		diff.overrideElementQualifier(new RecursiveElementNameAndTextQualifier());		
		diff.overrideDifferenceListener(new CustomizedDifferenceListener());
		
		System.out.println(diff.similar());
		System.out.println(diff.identical());
		
		Assert.assertTrue("Files are not similar!", diff.similar());		
	}
	
	public Document createXmlDoc(String xmlString) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setIgnoringComments(false);
        DocumentBuilder builder = factory.newDocumentBuilder();        
        Document document = builder.parse(new InputSource(new StringReader(xmlString)));
        return document;
	}
	
	public String getStringXml(Document document) throws Exception{
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(document), new StreamResult(writer));
		return writer.getBuffer().toString().replaceAll("\n|\r", "");
	}
	/**
	 * This method splits the XML String on "<!--" comment tags
	 * 
	 * @param text
	 * @return it returns the list of commented tags' name
	 */
	public List<String> getCommenttedTags(String text){
		ArrayList<String> removeList = null;			
		if (text.indexOf("<!--") != -1) {
			removeList = new ArrayList<String>();
			String[] textArray = text.split(java.util.regex.Pattern.quote("<!--"));
			if(textArray != null && textArray.length > 0){
				for(int index = 1; index < textArray.length; index++){
					
					int beginIndex = textArray[index].indexOf("<")+1;
					int endIndex = textArray[index].indexOf(">");
					removeList.add(textArray[index].substring(beginIndex, endIndex));
				}
			}
		}	
		return removeList;
	}
	
	private void doXmlUnitConfigs(){
		XMLUnit.setIgnoreComments(Boolean.TRUE);
		XMLUnit.setIgnoreAttributeOrder(Boolean.TRUE);
		XMLUnit.setIgnoreWhitespace(Boolean.TRUE);
		XMLUnit.setNormalizeWhitespace(Boolean.TRUE);
		XMLUnit.setIgnoreDiffBetweenTextAndCDATA(Boolean.TRUE);
	}
	
	public String readFileContents(File file)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
			try
			{
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				
				while (line != null)
				{
					sb.append(line);
					sb.append("\n");
					line = br.readLine();
				}
				return sb.toString();
			}
			finally
			{
				br.close();
			}
		}
		catch (IOException e)
		{
			
		}
		return "";
	}
	
}

class CustomizedDifferenceListener implements DifferenceListener
{	
	private static final int [] IGNORE_VALUES = new int [] { 
			DifferenceConstants.ATTR_SEQUENCE_ID, 
			DifferenceConstants.CDATA_VALUE_ID, 
	        DifferenceConstants.CHILD_NODELIST_SEQUENCE_ID
	        };
	
	private boolean isIgnoredDifference(Difference difference)
	{
		int differenceId = difference.getId();
		for ( int i = 0 ; i < IGNORE_VALUES.length ; ++i )
		{
			if ( differenceId == IGNORE_VALUES[i] ) { return true; }
		}
		System.out.println(difference.toString());
		return false;
	}
	
	public int differenceFound(Difference difference)
	{
		if ( isIgnoredDifference(difference) )
		{
			return RETURN_IGNORE_DIFFERENCE_NODES_SIMILAR;
		}
		else
		{
			return RETURN_ACCEPT_DIFFERENCE;
		}
	}
	
	public void skippedComparison(Node control, Node test)
	{
		
	}
}
