package ch.makery.address.util;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.model.PersonListWrapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.List;
import java.util.prefs.Preferences;

public class PersonDataStorage {

    /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry.
     *
     * @return The file or null if not found.
     */
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file in the OS registry.
     *
     * @param file the file or null to remove the path
     */
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
        } else {
            prefs.remove("filePath");
        }
    }

    /**
     * Loads person data from the specified file.
     *
     * @param file
     * @return A List of Persons.
     * @throws Exception (JAXBException, etc.)
     */
    public List<Person> loadPersonDataFromFile(File file) throws Exception {
        JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
        Unmarshaller um = context.createUnmarshaller();

        // Reading XML from the file and unmarshalling.
        PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);
        return wrapper.getPersons();
    }

    /**
     * Saves the current person data to the specified file.
     *
     * @param file
     * @param persons
     * @throws Exception (JAXBException, etc.)
     */
    public void savePersonDataToFile(File file, List<Person> persons) throws Exception {
        JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Wrapping our person data.
        PersonListWrapper wrapper = new PersonListWrapper();
        wrapper.setPersons(persons);

        // Marshalling and saving XML to the file.
        m.marshal(wrapper, file);
    }
}