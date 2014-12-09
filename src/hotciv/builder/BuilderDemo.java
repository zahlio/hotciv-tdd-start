package hotciv.builder;

/** Demonstration of Builder Pattern.

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University

   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
public class BuilderDemo {
	public static void main(String[] args) {
		System.out.println( "======= Demonstration of Builder =======");
		WordProcessor wp = new WordProcessor();

		// This code act as the client role that
		// creates the concrete builders and instruct
		// the director to construct objects.
		AsciiBuilder asciiBuilder;
		asciiBuilder = new AsciiBuilder();
		wp.construct(asciiBuilder);
		System.out.println( "--- The ASCII Builder output ---");
		System.out.println( asciiBuilder.getResult() );

		HTMLBuilder htmlBuilder;
		htmlBuilder = new HTMLBuilder();
		wp.construct(htmlBuilder);
		System.out.println( "--- The HTML Builder ---");
		System.out.println( htmlBuilder.getResult() );

		XMLBuilder xmlBuilder;
		xmlBuilder = new XMLBuilder();
		wp.construct(xmlBuilder);
		System.out.println( "--- The XML Builder ---");
		System.out.println( xmlBuilder.getResult() );

		CountBuilder countBuilder;
		countBuilder = new CountBuilder();
		wp.construct(countBuilder);
		System.out.println( "--- Counting types ---");
		System.out.println( "Sections   : "+countBuilder.getSectionCount() );
		System.out.println( "Subcections: "+countBuilder.getSubSectionCount() );
		System.out.println( "Paragraphs : "+countBuilder.getParagraphCount() );
		System.out.print("\n");
		
		WordCounter wordCounter;
		wordCounter = new WordCounter();
		wp.construct(wordCounter);
		System.out.println( "--- Word counts in elements ---");
		System.out.println( "Sections   : "+wordCounter.getSectionCount() );
		System.out.println( "Subcections: "+wordCounter.getSubSectionCount() );
		System.out.println( "Paragraphs : "+wordCounter.getParagraphCount() );
		System.out.println( "--- Total word count ---");
		System.out.println( "Total 	: "+wordCounter.getTotalCount());
		System.out.print("\n");
	}
}

/** This class plays the director role of the builder pattern */
class WordProcessor {
	/** The document
    1. The Builder Pattern
    ===================
      1.1 Intent
        Separate the construction of a complex object 
        from its representation so that the same construction 
        process can create different representations.
      1.2 Problem
        (The problem goes here)

    is coded as named strings to avoid defining a large
    datastructure.
	 */
	private String section = "The Builder Pattern";
	private String subSection1 = "Intent";
	private String paragraph1 = 
			"Separate the construction of a complex object\n"+
					"from its representation so that the same construction\n"+
					"process can create different representations.";
	private String subSection2 = "Problem";
	private String paragraph2 = "(The problem goes here)";

	public void construct(Builder builder) {
		/* a real constructor would iterate over a
		 * data structure, here I have hardcoded the
		 * document to keep the code small */
		builder.buildSection(section);
		builder.buildSubsection(subSection1);
		builder.buildParagraph(paragraph1);
		builder.buildSubsection(subSection2);
		builder.buildParagraph(paragraph2);
	}
}                         

/** This is the Builder role, the interface that
 * defines the parts that can be built */
interface Builder {
	public void buildSection(String text);
	public void buildSubsection(String text);
	public void buildParagraph(String text);
}

/** A concrete builder implementing a ASCII format */
class AsciiBuilder implements Builder {
	private String result;
	int sectionCounter, subSectionCounter;
	public AsciiBuilder() {
		result = new String();
		sectionCounter = subSectionCounter = 0;
	}
	public void buildSection(String text) {
		sectionCounter++;
		result += ""+sectionCounter+". "+text+"\n";
		result += "=======================\n";
	}
	public void buildSubsection(String text) {
		subSectionCounter++;
		result += ""+sectionCounter+"."+subSectionCounter+" "+text+"\n";
	}
	public void buildParagraph(String text) {
		result += text + "\n"; 
	}
	public String getResult() {
		return result;
	}
}

/** A concrete builder implementing a HTML format */
class HTMLBuilder implements Builder {
	private String result;
	public HTMLBuilder() {
		result = new String();
	}
	public void buildSection(String text) {
		result += "<H1>"+text+"</H1>\n";
	}
	public void buildSubsection(String text) {
		result += "<H2>"+text+"</H2>\n";
	}
	public void buildParagraph(String text) {
		result += "<P>\n"+text + "\n</P>\n"; 
	}
	public String getResult() {
		return result;
	}
}

//TODO: HOW DA FUCK AM I GONNA BUILD THIS? ALSO NEED TO ANT IT
class XMLBuilder implements Builder {
	private String result;

	public XMLBuilder() {
		result = new String();
	}

	public void buildSection(String text) {
		result += "<section name='" + text + "'/>\n";

	}

	public void buildSubsection(String text) {
		result += "<subsection name='" + text + "'/>\n";

	}

	public void buildParagraph(String text) {
		result += "<paragraph>\n" + text + "\n" + "</paragraph>\n";
	}
	
	public String getResult(){
		return result;
	}
}

/** A concrete builder that simply counts parts */
class CountBuilder implements Builder {
	private int section, subsection, paragraph;
	public CountBuilder() {
		section = subsection = paragraph = 0;
	}

	public void buildSection(String text) { section++; }
	public void buildSubsection(String text) { subsection++; }
	public void buildParagraph(String text) { paragraph++; }

	public int getSectionCount() { return section; }
	public int getSubSectionCount() { return subsection; }
	public int getParagraphCount() { return paragraph; }
}

class WordCounter implements Builder {
	private int section, subsection, paragraph;
	public WordCounter() {
		section = subsection = paragraph = 0;
	}

	public void buildSection(String text) {
		String[] words = text.split(" ");
		for(int i=0; i<words.length; i++){
			section++;
		}
	}
	public void buildSubsection(String text) {
		String[] words = text.split(" ");
		for(int i=0; i<words.length; i++){
			subsection++;
		}
	}
	public void buildParagraph(String text) { 
		String[] words = text.split(" ");
		for(int i=0; i<words.length; i++){
			paragraph++;
		}
	}

	public int getSectionCount() { return section; }
	public int getSubSectionCount() { return subsection; }
	public int getParagraphCount() { return paragraph; }
	public int getTotalCount() { return section + subsection + paragraph; }
}
