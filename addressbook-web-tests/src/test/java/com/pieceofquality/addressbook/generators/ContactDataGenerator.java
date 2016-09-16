package com.pieceofquality.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pieceofquality.addressbook.appmanager.HelperBase;
import com.pieceofquality.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.WebDriver;

/**
 * Created by piece on 11.09.2016.
 */
public class ContactDataGenerator{
    @Parameter(names = "-c", description = "Address count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }
    private void run() throws IOException {
        List<ContactData> adresses = generateAddress(count);
        if (format.equals("csv")) {
            saveAsCsv(adresses, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(adresses, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(adresses, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }

    }

    private void saveAsJson(List<ContactData> adresses, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(adresses);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> adresses, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(adresses);
        try(Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCsv(List<ContactData> adresses, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData address : adresses) {
            writer.write(String.format(("%s;%s\n"), address.getFirstName(), address.getLastName()));
        }
        writer.close();
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format(("%s;%s\n"), contact.getFirstName(), contact.getLastName()));
        }
        writer.close();
    }

    private static List<ContactData> generateAddress(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("firstName %s", i))
                    .withLastName(String.format("lastName %s", i)));
        }
        return contacts;
    }
}
