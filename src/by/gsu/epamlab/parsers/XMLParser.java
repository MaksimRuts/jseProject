package by.gsu.epamlab.parsers;

import by.gsu.epamlab.beans.AbstractResult;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XMLParser extends AbstractParser {

    private List<AbstractResult> results;
    private Iterator<AbstractResult> iterator = null;
    private final String FILEPATCH;

    private static enum FieldsKind {
        NAME, DATE, MARK, TEST, LOGIN, STUDENT;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    public <T extends AbstractResult> XMLParser(T result, String filePatch) {
        super(result);
        results = new ArrayList<AbstractResult>();
        iterator = results.iterator();
        this.FILEPATCH = filePatch;

        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            InnerXMLParser parser = new InnerXMLParser();
            reader.setContentHandler(parser);
            reader.parse(FILEPATCH);
            iterator = results.iterator();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("File didn't found");
        }

    }

    @Override
    public boolean hasNextResult() {
        if (iterator != null) {
            return iterator.hasNext();
        }
        return false;
    }

    @Override
    public AbstractResult getResult() {
        if (iterator != null) {
            return iterator.next();
        }
        return null;
    }

    class InnerXMLParser extends DefaultHandler {

        private String login;
        private String currentStartTag;

        public InnerXMLParser() {
            login = "";
            currentStartTag = "";
        }

        public List<AbstractResult> getResults() {
            return results;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            currentStartTag = localName;
            if (FieldsKind.TEST.toString().equals(currentStartTag)) {
                AbstractResult result = getInstance();

                result.setLogin(login);
                result.setTest(attributes.getValue(FieldsKind.NAME.ordinal()));
                result.setDate(java.sql.Date.valueOf(attributes.getValue(FieldsKind.DATE.ordinal())));
                result.setMark(attributes.getValue(FieldsKind.MARK.ordinal()));
                results.add(result);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (FieldsKind.STUDENT.toString().equals(localName)) {
                login = "";
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (FieldsKind.LOGIN.toString().equals(currentStartTag)) {
                login = String.valueOf(ch, start, length);
            }
            currentStartTag = "";
        }
    }
}
