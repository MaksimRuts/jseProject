package by.gsu.epamlab.parsers;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.factories.ResultFactory;
import exceptions.ParseException;
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

    private List<Result> results;
    private Iterator<Result> iterator = null;
    private final String FILEPATCH;

    private static enum FieldsKind {
        NAME, DATE, MARK, TEST, LOGIN, STUDENT;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    public XMLParser(ResultFactory factory, String filePatch) {
        super(factory);
        results = new ArrayList<Result>();
        iterator = results.iterator();
        this.FILEPATCH = filePatch;

        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            InnerXMLParser parser = new InnerXMLParser();
            reader.setContentHandler(parser);
            reader.parse(FILEPATCH);
            iterator = results.iterator();
        } catch (SAXException e) {
            throw new ParseException(e);
        } catch (IOException e) {
            throw new ParseException(FILE_NOT_FOUND, e);
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
    public Result nextResult() {
        if (iterator != null) {
            return iterator.next();
        }
        return null;
    }

    @Override
    public void close() {

    }

    class InnerXMLParser extends DefaultHandler {

        private String login;
        private String currentStartTag;

        public InnerXMLParser() {
            login = "";
            currentStartTag = "";
        }

        public List<Result> getResults() {
            return results;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            currentStartTag = localName;
            if (FieldsKind.TEST.toString().equals(currentStartTag)) {
                Result result = createInstance();

                try {
                    result.setLogin(login);
                    result.setTest(attributes.getValue(FieldsKind.NAME.ordinal()));
                    result.setDate(java.sql.Date.valueOf(attributes.getValue(FieldsKind.DATE.ordinal())));
                    result.setMark(attributes.getValue(FieldsKind.MARK.ordinal()));
                    results.add(result);
                } catch (IllegalArgumentException e) {
                    throw new ParseException(attributes.toString(), e);
                }
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
